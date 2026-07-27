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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

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
        Minecraft.getInstance().execute(() -> {
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
                Minecraft mc = Minecraft.getInstance();
                StringBuilder sb = new StringBuilder("{");

                // screen
                var screen = McScreenHelper.getCurrentScreen(mc);
                sb.append("\"screen\":");
                sb.append(screen == null ? "null" : jsonEscape(screen.getClass().getName()));

                // tick (since world join; same as log tick stamp)
                sb.append(",\"tick\":").append(BindAliasPlusClient.joinTick < 0 ? -1
                        : (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick));

                LocalPlayer p = mc.player;
                if (p != null) {
                    // dimension
                    sb.append(",\"dimension\":");
                    sb.append(jsonEscape(p.level().dimension().toString()));

                    // world / server name
                    String worldName = null;
                    try {
                        if (mc.getSingleplayerServer() != null) {
                            worldName = mc.getSingleplayerServer().getWorldData().getLevelName();
                        } else if (mc.getCurrentServer() != null) {
                            worldName = mc.getCurrentServer().name;
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
                    sb.append(",\"yaw\":").append(p.getYRot());
                    sb.append(",\"pitch\":").append(p.getXRot());

                    // health
                    sb.append(",\"health\":").append(p.getHealth());
                    sb.append(",\"maxHealth\":").append(p.getMaxHealth());

                    // held item
                    ItemStack held = p.getMainHandItem();
                    if (held != null && !held.isEmpty()) {
                        sb.append(",\"heldItem\":");
                        sb.append(jsonEscape(BuiltInRegistries.ITEM.getKey(held.getItem()).toString()));
                        sb.append(",\"heldItemCount\":").append(held.getCount());
                    } else {
                        sb.append(",\"heldItem\":null");
                        sb.append(",\"heldItemCount\":0");
                    }

                    // hotbar slot (1-indexed)
                    sb.append(",\"hotbarSlot\":").append(p.getInventory().getSelectedSlot() + 1);

                    // open container menu slots (read-only; c matches swapSlot's cN addressing)
                    if (screen instanceof AbstractContainerScreen<?> containerScreen) {
                        sb.append(",\"container\":").append(buildContainerJson(containerScreen.getMenu()));
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
     * Compact JSON view of an open container menu.
     * <ul>
     * <li>{@code inventory_items}: occupied slots as {@code [{index, item, count}]}. Index is a swapSlot argument — 1–41 for
     * player inventory, {@code "cN"} for container slots.</li>
     * <li>{@code empty_inv}: empty player-inventory slots compressed to ranges (e.g. {@code "1-9 10-36"}).</li>
     * <li>{@code container_grid}: 2D array of cell strings — {@code "cNN:*"} (occupied), {@code "cNN:O"} (empty),
     * {@code "     "} (no slot).</li>
     * </ul>
     */
    private static String buildContainerJson(AbstractContainerMenu menu) {
        StringBuilder out = new StringBuilder("{");

        StringBuilder items = new StringBuilder();
        List<Integer> emptyInv = new ArrayList<>();
        List<int[]> gridSlots = new ArrayList<>(); // {c, x, y, occupied(0|1)}

        for (int i = 0; i < menu.slots.size(); i++) {
            Slot slot = menu.slots.get(i);
            int c = i + 1;
            ItemStack stack = slot.getItem();
            boolean isPlayerInv = slot.container instanceof Inventory;

            if (!stack.isEmpty()) {
                if (items.length() > 0)
                    items.append(',');
                items.append("{\"index\":");
                if (isPlayerInv)
                    items.append(slot.getContainerSlot() + 1);
                else
                    items.append(jsonEscape("c" + c));
                items.append(",\"item\":").append(jsonEscape(BuiltInRegistries.ITEM.getKey(stack.getItem()).toString()))
                        .append(",\"count\":").append(stack.getCount()).append('}');
            }

            if (isPlayerInv) {
                if (stack.isEmpty())
                    emptyInv.add(slot.getContainerSlot() + 1);
            } else {
                gridSlots.add(new int[] {c, slot.x, slot.y, stack.isEmpty() ? 0 : 1});
            }
        }

        // compress empty inventory slots to ranges
        Collections.sort(emptyInv);
        StringBuilder emptyRanges = new StringBuilder();
        for (int i = 0; i < emptyInv.size(); i++) {
            int start = emptyInv.get(i);
            int end = start;
            while (i + 1 < emptyInv.size() && emptyInv.get(i + 1) == end + 1)
                end = emptyInv.get(++i);
            if (emptyRanges.length() > 0)
                emptyRanges.append(' ');
            emptyRanges.append(start);
            if (end > start)
                emptyRanges.append('-').append(end);
        }

        out.append("\"inventory_items\":[").append(items).append(']');
        out.append(",\"empty_inv\":").append(jsonEscape(emptyRanges.toString()));

        // build container grid
        if (!gridSlots.isEmpty()) {
            int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
            int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
            for (int[] s : gridSlots) {
                minX = Math.min(minX, s[1]);
                maxX = Math.max(maxX, s[1]);
                minY = Math.min(minY, s[2]);
                maxY = Math.max(maxY, s[2]);
            }

            int cols = (maxX - minX) / 18 + 1;
            int rows = (maxY - minY) / 18 + 1;
            String[][] grid = new String[rows][cols];
            for (int r = 0; r < rows; r++)
                Arrays.fill(grid[r], "\"     \"");

            for (int[] s : gridSlots) {
                int col = (s[1] - minX) / 18;
                int row = (s[2] - minY) / 18;
                char state = s[3] == 0 ? 'O' : '*';
                grid[row][col] = "\"c" + String.format("%02d", s[0]) + ':' + state + '"';
            }

            out.append(",\"container_grid\":[");
            for (int r = 0; r < rows; r++) {
                if (r > 0)
                    out.append(',');
                out.append("\"|");
                for (int c = 0; c < cols; c++) {
                    if (c > 0)
                        out.append(',');
                    out.append(grid[r][c]);
                }
                out.append('|');
            }
            out.append("]\"");
        }

        out.append('}');
        return out.toString();
    }

    /** GET /screenshot — trigger a screenshot and return it base64-encoded. */
    static synchronized void handleScreenshot(HttpExchange exchange) throws IOException {
        try {
            // Pre-check: must be in game
            Boolean inGame = onMainThread(() -> Minecraft.getInstance().player != null);
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
                Minecraft mc = Minecraft.getInstance();
                var p = mc.player;
                if (p != null) {
                    posHolder[0] = new double[] {p.getX(), p.getY(), p.getZ(), p.getYRot(), p.getXRot()};
                }
                net.minecraft.client.Screenshot.grab(mc.gameDirectory, null, mc.getMainRenderTarget(), 1, msg -> {
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
                // tick since world join at the exact moment the alias was executed
                long tickSinceJoin = BindAliasPlusClient.joinTick < 0 ? -1
                        : (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick);
                return "{\"tick\":" + tickSinceJoin + "}";
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
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    mc.player.connection.sendCommand("alias " + name + " " + def);
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
