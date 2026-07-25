package com.github.prohect.mcp;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.UserAlias;
import com.github.prohect.util.McScreenHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.ItemStack;

/**
 * Lightweight HTTP API server bound to {@code 127.0.0.1:25575}.
 * <p>
 * Exposes mod state and alias dispatch so an external MCP bridge can control Minecraft through the mod's alias system. All
 * game-thread access goes through {@link Minecraft#execute(Runnable)} with a 5 s timeout to prevent the HTTP handler from
 * blocking forever.
 * <p>
 * Started from {@link BindAliasPlusClient#onInitializeClient()}.
 */
public final class McpHttpServer {

    private static final int PORT = 25575;
    private static final int TIMEOUT_SECONDS = 5;
    private static HttpServer server;

    private McpHttpServer() {}

    // ---- lifecycle ----

    /** Start the HTTP server on the default port. Safe to call multiple times. */
    public static void start() {
        if (server != null)
            return;
        try {
            server = HttpServer.create(new InetSocketAddress("127.0.0.1", PORT), 0);
            server.createContext("/state", McpHttpServer::handleState);
            server.createContext("/screenshot", McpHttpServer::handleScreenshot);
            server.createContext("/runAlias", McpHttpServer::handleRunAlias);
            server.createContext("/defineAlias", McpHttpServer::handleDefineAlias);
            server.createContext("/readCFG", McpHttpServer::handleReadCFG);
            server.createContext("/writeCFG", McpHttpServer::handleWriteCFG);
            server.createContext("/logDiff", McpHttpServer::handleLogDiff);
            server.setExecutor(Executors.newCachedThreadPool(r -> {
                Thread t = new Thread(r, "BindAliasPlus-MCP");
                t.setDaemon(true);
                return t;
            }));
            server.start();
            Runtime.getRuntime().addShutdownHook(new Thread(McpHttpServer::stop, "BindAliasPlus-MCP-Shutdown"));
            BindAliasPlusClient.LOGGER.info("{}[MCP] HTTP server started on 127.0.0.1:{}", BindAliasPlusClient.tickPrefix(),
                    PORT);
        } catch (Exception e) {
            BindAliasPlusClient.LOGGER.error("{}[MCP] Failed to start HTTP server", BindAliasPlusClient.tickPrefix(), e);
        }
    }

    /** Stop the HTTP server with a 0-second grace period. */
    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
            BindAliasPlusClient.LOGGER.info("{}[MCP] HTTP server stopped", BindAliasPlusClient.tickPrefix());
        }
    }

    // ---- helpers ----

    /** Run a task on the Minecraft main thread and block for the result. */
    private static <T> T onMainThread(CheckedSupplier<T> task) throws Exception {
        CompletableFuture<T> future = new CompletableFuture<>();
        MinecraftClient.getInstance().execute(() -> {
            try {
                future.complete(task.get());
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });
        return future.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    /** Parse URL query string into a key-value map. */
    private static Map<String, String> parseQuery(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null || query.isBlank())
            return map;
        for (String pair : query.split("&")) {
            int idx = pair.indexOf('=');
            if (idx > 0) {
                map.put(decodePercent(pair.substring(0, idx)), decodePercent(pair.substring(idx + 1)));
            }
        }
        return map;
    }

    /**
     * Decode percent-encoded characters (%XX). No '+' → space conversion — the bridge uses encodeURIComponent which emits
     * spaces as %20, so no special-case logic is needed.
     */
    private static String decodePercent(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '%' && i + 2 < s.length()) {
                try {
                    int hi = Character.digit(s.charAt(i + 1), 16);
                    int lo = Character.digit(s.charAt(i + 2), 16);
                    if (hi >= 0 && lo >= 0) {
                        sb.append((char) ((hi << 4) | lo));
                        i += 2;
                        continue;
                    }
                } catch (Exception ignored) {
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /** Send a JSON string as the HTTP response. */
    private static void sendJson(HttpExchange exchange, int code, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    /** Minimal JSON string escaping (no external dependency). */
    private static String jsonEscape(String s) {
        if (s == null)
            return "null";
        StringBuilder sb = new StringBuilder("\"");
        for (char c : s.toCharArray()) {
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.append('"').toString();
    }

    @FunctionalInterface
    private interface CheckedSupplier<T> {
        T get() throws Exception;
    }

    // ---- endpoints ----

    /** GET /state — snapshot of current game state. */
    static void handleState(HttpExchange exchange) throws IOException {
        try {
            String json = onMainThread(() -> {
                MinecraftClient mc = MinecraftClient.getInstance();
                StringBuilder sb = new StringBuilder("{");

                // screen
                var screen = McScreenHelper.getCurrentScreen(mc);
                sb.append("\"screen\":");
                sb.append(screen == null ? "null" : jsonEscape(screen.getClass().getName()));

                // tick (since world join; same as log tick stamp)
                sb.append(",\"tick\":").append(BindAliasPlusClient.joinTick < 0 ? -1
                        : (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick));

                ClientPlayerEntity p = mc.player;
                if (p != null) {
                    // dimension
                    sb.append(",\"dimension\":");
                    sb.append(jsonEscape(p.getEntityWorld().getRegistryKey().getValue().toString()));

                    // world / server name
                    String worldName = null;
                    try {
                        if (mc.getServer() != null) {
                            worldName = mc.getServer().getSaveProperties().getLevelName();
                        } else if (mc.getCurrentServerEntry() != null) {
                            worldName = mc.getCurrentServerEntry().name;
                        }
                    } catch (Exception ignored) {
                        // best-effort; ignore if mappings differ
                    }
                    sb.append(",\"worldName\":");
                    sb.append(worldName == null ? "null" : jsonEscape(worldName));

                    // position
                    sb.append(",\"x\":").append(p.getX());
                    sb.append(",\"y\":").append(p.getY());
                    sb.append(",\"z\":").append(p.getZ());
                    sb.append(",\"yaw\":").append(p.getYaw());
                    sb.append(",\"pitch\":").append(p.getPitch());

                    // health
                    sb.append(",\"health\":").append(p.getHealth());
                    sb.append(",\"maxHealth\":").append(p.getMaxHealth());

                    // held item
                    ItemStack held = p.getMainHandStack();
                    if (held != null && !held.isEmpty()) {
                        sb.append(",\"heldItem\":");
                        sb.append(jsonEscape(Registries.ITEM.getKey(held.getItem()).map(k -> k.getValue().toString())
                                .orElse(held.getItem().toString())));
                        sb.append(",\"heldItemCount\":").append(held.getCount());
                    } else {
                        sb.append(",\"heldItem\":null");
                        sb.append(",\"heldItemCount\":0");
                    }

                    // hotbar slot (1-indexed)
                    sb.append(",\"hotbarSlot\":").append(p.getInventory().getSelectedSlot() + 1);

                    // open container menu slots (read-only; c matches swapSlot's cN addressing)
                    if (screen instanceof HandledScreen<?> containerScreen) {
                        sb.append(",\"container\":").append(buildContainerJson(containerScreen.getScreenHandler()));
                    }
                }

                sb.append('}');
                return sb.toString();
            });
            sendJson(exchange, 200, json);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * Max length of the compressed container section; beyond this we refuse to attach it and recommend a screenshot instead.
     */
    private static final int CONTAINER_JSON_MAX = 6000;

    /**
     * Compressed, read-only view of an open container menu.
     * <ul>
     * <li>occupied slots stay JSON: {@code {index, item, count}} where {@code index} is literally a swapSlot argument - a
     * number (1-41) for player inventory slots, a {@code "cN"} string for container slots</li>
     * <li>empty player-inventory slots compress to ranges in the same 1-41 numbering: {@code "1-9 10-36"}</li>
     * <li>non-inventory slots (chest grid, crafting grid, anvil, ...) compress to an ASCII map ('#' empty, '$' occupied, ' ' no
     * slot) with the x/y range and per-cell c-indices alongside</li>
     * </ul>
     */
    private static String buildContainerJson(ScreenHandler menu) {
        StringBuilder out = new StringBuilder("{\"menu\":").append(jsonEscape(menu.getClass().getName()));

        StringBuilder items = new StringBuilder();
        java.util.List<Integer> emptyInvSlots = new java.util.ArrayList<>();
        java.util.List<int[]> nonInv = new java.util.ArrayList<>(); // {c, x, y, occupied}

        for (int i = 0; i < menu.slots.size(); i++) {
            Slot slot = menu.slots.get(i);
            int c = i + 1;
            ItemStack stack = slot.getStack();
            boolean playerInv = slot.inventory instanceof PlayerInventory;
            if (!stack.isEmpty()) {
                if (items.length() > 0)
                    items.append(',');
                items.append("{\"index\":");
                if (playerInv)
                    items.append(slot.getIndex() + 1);
                else
                    items.append(jsonEscape("c" + c));
                items.append(",\"item\":").append(jsonEscape(Registries.ITEM.getKey(stack.getItem())
                        .map(k -> k.getValue().toString()).orElse(stack.getItem().toString()))).append(",\"count\":")
                        .append(stack.getCount());
                items.append('}');
            }
            if (playerInv) {
                if (stack.isEmpty())
                    emptyInvSlots.add(slot.getIndex() + 1);
            } else {
                nonInv.add(new int[] {c, slot.x, slot.y, stack.isEmpty() ? 0 : 1});
            }
        }
        java.util.Collections.sort(emptyInvSlots);
        StringBuilder emptyInv = new StringBuilder();
        for (int i = 0; i < emptyInvSlots.size(); i++) {
            int start = emptyInvSlots.get(i);
            int end = start;
            while (i + 1 < emptyInvSlots.size() && emptyInvSlots.get(i + 1) == end + 1) {
                end = emptyInvSlots.get(++i);
            }
            if (emptyInv.length() > 0)
                emptyInv.append(' ');
            emptyInv.append(start);
            if (end > start)
                emptyInv.append('-').append(end);
        }

        if (!nonInv.isEmpty()) {
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
            for (int[] s : nonInv) {
                minX = Math.min(minX, s[1]);
                minY = Math.min(minY, s[2]);
                maxX = Math.max(maxX, s[1]);
                maxY = Math.max(maxY, s[2]);
            }
            int cell = 18; // standard slot pitch in container GUIs
            int cols = (maxX - minX) / cell + 1;
            int rows = (maxY - minY) / cell + 1;
            char[][] map = new char[rows][cols];
            int[][] cells = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                java.util.Arrays.fill(map[r], ' ');
                java.util.Arrays.fill(cells[r], -1);
            }
            for (int[] s : nonInv) {
                int col = (s[1] - minX) / cell;
                int row = (s[2] - minY) / cell;
                map[row][col] = s[3] == 0 ? '#' : '$';
                cells[row][col] = s[0];
            }
            out.append(",\"grid\":{\"xy\":").append(jsonEscape("x" + minX + "-" + maxX + " y" + minY + "-" + maxY))
                    .append(",\"map\":[");
            for (int r = 0; r < rows; r++) {
                if (r > 0)
                    out.append(',');
                out.append(jsonEscape(new String(map[r])));
            }
            out.append("],\"cells\":[");
            for (int r = 0; r < rows; r++) {
                if (r > 0)
                    out.append(',');
                StringBuilder row = new StringBuilder();
                for (int col = 0; col < cols; col++) {
                    if (col > 0)
                        row.append(',');
                    if (cells[r][col] >= 0)
                        row.append(cells[r][col]);
                    else
                        row.append(' ');
                }
                out.append(jsonEscape(row.toString()));
            }
            out.append("]}");
        }

        out.append(",\"items\":[").append(items).append(']');
        out.append(",\"emptyInv\":").append(jsonEscape(emptyInv.toString()));
        out.append('}');

        if (out.length() > CONTAINER_JSON_MAX) {
            return ("{\"menu\":" + jsonEscape(menu.getClass().getName()) + ",\"error\":\"too large; use screenshot instead\"}");
        }
        return out.toString();
    }

    /** GET /screenshot — trigger a screenshot and return it base64-encoded. */
    static synchronized void handleScreenshot(HttpExchange exchange) throws IOException {
        try {
            // Pre-check: must be in game
            Boolean inGame = onMainThread(() -> MinecraftClient.getInstance().player != null);
            if (!inGame) {
                sendJson(exchange, 400, "{\"error\":\"not in game\"}");
                return;
            }

            // Arm the mixin capture and trigger the native screenshot.
            // NativeImageMixin intercepts writeToFile(Path) and completes
            // the future with in-memory PNG bytes -- no sleep or FS scan.
            // Also capture player position in the same main-thread roundtrip.
            CompletableFuture<byte[]> future = new CompletableFuture<>();
            ScreenshotCapture.nextPngFuture = future;
            final double[][] posHolder = new double[1][];

            onMainThread(() -> {
                MinecraftClient mc = MinecraftClient.getInstance();
                var p = mc.player;
                if (p != null) {
                    posHolder[0] = new double[] {p.getX(), p.getY(), p.getZ(), p.getYaw(), p.getPitch()};
                }
                net.minecraft.client.util.ScreenshotRecorder.saveScreenshot(mc.runDirectory, mc.getFramebuffer(), msg -> {
                });
                return null;
            });

            // Wait on the HTTP thread (not the game thread) for the
            // I/O-thread PNG encode to finish.
            byte[] data;
            try {
                data = future.get(3, java.util.concurrent.TimeUnit.SECONDS);
            } catch (java.util.concurrent.TimeoutException e) {
                sendJson(exchange, 500, "{\"error\":\"screenshot timed out\"}");
                return;
            } catch (Exception e) {
                sendJson(exchange, 500, "{\"error\":\"screenshot failed: " + jsonEscape(e.getMessage()) + "\"}");
                return;
            }

            if (data == null) {
                sendJson(exchange, 500, "{\"error\":\"screenshot encode failed\"}");
                return;
            }

            String path = ScreenshotCapture.lastPath;
            String name = ScreenshotCapture.lastName;
            String b64 = Base64.getEncoder().encodeToString(data);
            StringBuilder json = new StringBuilder(512);
            json.append("{\"path\":").append(jsonEscape(path)).append(",\"name\":").append(jsonEscape(name))
                    .append(",\"base64\":").append(jsonEscape(b64));
            double[] pos = posHolder[0];
            if (pos != null) {
                json.append(String.format(",\"x\":%.2f,\"y\":%.2f,\"z\":%.2f,\"yaw\":%.2f,\"pitch\":%.2f", pos[0], pos[1],
                        pos[2], pos[3], pos[4]));
            }
            // tick (since world join; same as log tick stamp)
            json.append(",\"tick\":").append(
                    BindAliasPlusClient.joinTick < 0 ? -1 : (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick));
            json.append('}');
            sendJson(exchange, 200, json.toString());
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * POST /runAlias?def=… — execute a chain of aliases (space-separated, \ for args).
     */
    static void handleRunAlias(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String def = q.get("def");

        // Legacy support: also accept name+args
        if (def == null || def.isBlank()) {
            String name = q.get("name");
            String args = q.getOrDefault("args", "");
            if (name != null && !name.isBlank()) {
                def = name;
                if (!args.isEmpty())
                    def += Alias.divider4AliasArgs + args;
            }
        }

        if (def == null || def.isBlank()) {
            sendJson(exchange, 400, "{\"error\":\"missing 'def' parameter\"}");
            return;
        }

        final String definition = def;
        try {
            String result = onMainThread(() -> {
                // Use UserAlias for full chaining support
                // (space-separated aliases, \ for args)
                new UserAlias(definition).run("");
                return "{\"ok\":true}";
            });
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * POST /defineAlias?name=…&def=… — define an alias via the real command pipeline and capture feedback.
     */
    static void handleDefineAlias(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String name = q.get("name");
        String def = q.get("def");

        if (name == null || def == null) {
            sendJson(exchange, 400, "{\"error\":\"missing 'name' or 'def' parameter\"}");
            return;
        }

        try {
            // Enable capture before sending the command so the mixin
            // collects the sendFeedback output from the /alias handler.
            ChatCapture.begin();

            onMainThread(() -> {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null) {
                    mc.player.networkHandler.sendChatCommand("alias " + name + " " + def);
                }
                return null;
            });

            // Capture happens synchronously on the main thread — the
            // command handler fires sendFeedback before returning.
            String feedback = ChatCapture.end();

            if (feedback == null || feedback.isEmpty()) {
                sendJson(exchange, 500, "{\"error\":\"no feedback from command\"}");
                return;
            }

            if (feedback.startsWith("Alias ")) {
                sendJson(exchange, 200, "{\"ok\":true,\"feedback\":" + jsonEscape(feedback) + "}");
            } else {
                sendJson(exchange, 200, "{\"error\":" + jsonEscape(feedback) + "}");
            }
        } catch (Exception e) {
            ChatCapture.end(); // ensure capture is stopped on error
            sendJson(exchange, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    /** GET /readCFG — return the raw config file content. */
    static void handleReadCFG(HttpExchange exchange) throws IOException {
        try {
            String content = Files.readString(BindAliasPlusClient.cfgPath);
            sendJson(exchange, 200, "{\"content\":" + jsonEscape(content) + "}");
        } catch (IOException e) {
            sendJson(exchange, 500, "{\"error\":" + jsonEscape("failed to read: " + e.getMessage()) + "}");
        }
    }

    /** GET /logDiff — return new game-log messages since the last call. */
    static void handleLogDiff(HttpExchange exchange) throws IOException {
        String messages = ChatCapture.diff();
        int count = messages.isEmpty() ? 0 : messages.split("\n", -1).length;
        sendJson(exchange, 200, "{\"messages\":" + jsonEscape(messages) + ",\"count\":" + count + "}");
    }

    /**
     * Extract a string field's value from a flat JSON object body, decoding escapes in a single left-to-right pass. A naive
     * indexOf('"') search for the closing quote stops early at escaped quotes (\"), truncating cfg content that contains quoted
     * alias args (e.g. say\"...\").
     *
     * @return the decoded string, or null if the field is absent or malformed
     */
    private static String extractJsonStringField(String body, String fieldName) {
        int start = body.indexOf("\"" + fieldName + "\"");
        if (start < 0)
            return null;
        int colon = body.indexOf(':', start);
        if (colon < 0)
            return null;
        int i = colon + 1;
        while (i < body.length() && Character.isWhitespace(body.charAt(i)))
            i++;
        if (i >= body.length() || body.charAt(i) != '"')
            return null;
        i++;
        StringBuilder sb = new StringBuilder();
        while (i < body.length()) {
            char c = body.charAt(i++);
            if (c == '"')
                return sb.toString();
            if (c == '\\' && i < body.length()) {
                char esc = body.charAt(i++);
                switch (esc) {
                    case 'n' -> sb.append('\n');
                    case 'r' -> sb.append('\r');
                    case 't' -> sb.append('\t');
                    case 'b' -> sb.append('\b');
                    case 'f' -> sb.append('\f');
                    case '/', '"', '\\' -> sb.append(esc);
                    case 'u' -> {
                        if (i + 4 <= body.length()) {
                            try {
                                sb.append((char) Integer.parseInt(body.substring(i, i + 4), 16));
                                i += 4;
                            } catch (NumberFormatException e) {
                                sb.append("\\u");
                            }
                        } else
                            return null;
                    }
                    default -> sb.append(esc);
                }
            } else
                sb.append(c);
        }
        return null; // unterminated string
    }

    /** POST /writeCFG — overwrite the config file and reload. */
    static void handleWriteCFG(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String content = q.get("content");

        // if content not in query, try JSON body
        if (content == null) {
            String body;
            try (InputStream is = exchange.getRequestBody()) {
                body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
            if (body != null) {
                content = extractJsonStringField(body, "content");
            }
        }

        if (content == null) {
            sendJson(exchange, 400, "{\"error\":\"missing 'content'\"}");
            return;
        }

        try {
            Files.writeString(BindAliasPlusClient.cfgPath, content);
            onMainThread(() -> {
                BindAliasPlusClient.INSTANCE.loadCFG();
                return null;
            });
            sendJson(exchange, 200, "{\"ok\":true}");
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }
}
