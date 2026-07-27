package com.github.prohect.mcp;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.UserAlias;
import com.github.prohect.util.McScreenHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public final class McpHttpServer {
    private static final int PORT = 25575;
    private static final int TIMEOUT_SECONDS = 5;
    private static HttpServer server;

    private McpHttpServer() {}

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

    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
            BindAliasPlusClient.LOGGER.info("{}[MCP] HTTP server stopped", BindAliasPlusClient.tickPrefix());
        }
    }

    private static <T> T onMainThread(CheckedSupplier<T> task) throws Exception {
        CompletableFuture<T> f = new CompletableFuture<>();
        MinecraftClient.getInstance().execute(() -> {
            try {
                f.complete(task.get());
            } catch (Exception e) {
                f.completeExceptionally(e);
            }
        });
        return f.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    private static Map<String, String> parseQuery(String q) {
        Map<String, String> m = new HashMap<>();
        if (q == null || q.isBlank())
            return m;
        for (String p : q.split("&")) {
            int i = p.indexOf('=');
            if (i > 0)
                m.put(decodePercent(p.substring(0, i)), decodePercent(p.substring(i + 1)));
        }
        return m;
    }

    private static String decodePercent(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '%' && i + 2 < s.length()) {
                try {
                    int hi = Character.digit(s.charAt(i + 1), 16), lo = Character.digit(s.charAt(i + 2), 16);
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

    private static void sendJson(HttpExchange ex, int code, String json) throws IOException {
        byte[] b = json.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        ex.sendResponseHeaders(code, b.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(b);
        }
    }

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

    static void handleState(HttpExchange ex) throws IOException {
        try {
            String json = onMainThread(() -> {
                MinecraftClient mc = MinecraftClient.getInstance();
                StringBuilder sb = new StringBuilder("{");
                var sc = McScreenHelper.getCurrentScreen(mc);
                sb.append("\"screen\":").append(sc == null ? "null" : jsonEscape(sc.getClass().getName()));

                // tick (since world join; same as log tick stamp)
                sb.append(",\"tick\":").append(BindAliasPlusClient.joinTick < 0 ? -1
                        : (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick));

                ClientPlayerEntity p = mc.player;
                if (p != null) {
                    sb.append(",\"dimension\":").append(jsonEscape(p.getWorld().getRegistryKey().getValue().toString()));
                    String worldName = null;
                    try {
                        if (mc.getServer() != null)
                            worldName = mc.getServer().getSaveProperties().getLevelName();
                        else if (mc.getCurrentServerEntry() != null)
                            worldName = mc.getCurrentServerEntry().name;
                    } catch (Exception ignored) {
                    }
                    sb.append(",\"worldName\":").append(worldName == null ? "null" : jsonEscape(worldName));
                    sb.append(",\"x\":").append(p.getX());
                    sb.append(",\"y\":").append(p.getY());
                    sb.append(",\"z\":").append(p.getZ());
                    sb.append(",\"yaw\":").append(p.getYaw());
                    sb.append(",\"pitch\":").append(p.getPitch());
                    sb.append(",\"health\":").append(p.getHealth());
                    sb.append(",\"maxHealth\":").append(p.getMaxHealth());
                    ItemStack held = p.getMainHandStack();
                    if (held != null && !held.isEmpty()) {
                        sb.append(",\"heldItem\":").append(jsonEscape(Registries.ITEM.getKey(held.getItem())
                                .map(k -> k.getValue().toString()).orElse(held.getItem().toString())));
                        sb.append(",\"heldItemCount\":").append(held.getCount());
                    } else {
                        sb.append(",\"heldItem\":null");
                        sb.append(",\"heldItemCount\":0");
                    }
                    sb.append(",\"hotbarSlot\":").append(p.getInventory().getSelectedSlot() + 1);
                    if (sc instanceof HandledScreen<?> cs)
                        sb.append(",\"container\":").append(buildContainerJson(cs.getScreenHandler()));
                }
                sb.append('}');
                return sb.toString();
            });
            sendJson(ex, 200, json);
        } catch (Exception e) {
            sendJson(ex, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * Compact JSON view of an open container menu.
     * <ul>
     * <li>{@code inventory_items}: occupied slots as {@code [{index, item, count}]}. Index is a swapSlot argument — 1–41 for
     * player inventory, {@code "cN"} for container slots.</li>
     * <li>{@code empty_inv}: empty player-inventory slots compressed to ranges (e.g. {@code "1-9 10-36"}).</li>
     * <li>{@code container_grid}: array of row strings. Consecutive container cells are space-separated inside one
     * {@code |group|} — {@code cNN:*} (occupied), {@code cNN:O} (empty); a no-slot cell is five spaces. Everything past the
     * last container cell of a row is blank padding, and all rows but the last end with {@code \n}, e.g.
     * {@code "|c01:* c02:O|     |c03:*|\n"}.</li>
     * </ul>
     */
    private static String buildContainerJson(ScreenHandler menu) {
        StringBuilder out = new StringBuilder("{");

        StringBuilder items = new StringBuilder();
        List<Integer> emptyInv = new ArrayList<>();
        List<int[]> gridSlots = new ArrayList<>(); // {c, x, y, occupied(0|1)}

        for (int i = 0; i < menu.slots.size(); i++) {
            Slot slot = menu.slots.get(i);
            int c = i + 1;
            ItemStack stack = slot.getStack();
            boolean isPlayerInv = slot.inventory instanceof PlayerInventory;

            if (!stack.isEmpty()) {
                if (items.length() > 0)
                    items.append(',');
                items.append("{\"index\":");
                if (isPlayerInv)
                    items.append(slot.getIndex() + 1);
                else
                    items.append(jsonEscape("c" + c));
                items.append(",\"item\":")
                        .append(jsonEscape(Registries.ITEM.getKey(stack.getItem()).map(k -> k.getValue().toString())
                                .orElse(stack.getItem().toString())))
                        .append(",\"count\":").append(stack.getCount()).append('}');
            }

            if (isPlayerInv) {
                if (stack.isEmpty())
                    emptyInv.add(slot.getIndex() + 1);
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
                Arrays.fill(grid[r], "     ");

            for (int[] s : gridSlots) {
                int col = (s[1] - minX) / 18;
                int row = (s[2] - minY) / 18;
                char state = s[3] == 0 ? 'o' : '*';
                grid[row][col] = "c" + String.format("%02d", s[0]) + ':' + state;
            }

            out.append(",\"container_grid\":[");
            int width = cols * 6 + 1;
            for (int r = 0; r < rows; r++) {
                if (r > 0)
                    out.append(',');
                // group consecutive container cells inside one |group|; drop everything past the last container cell
                int last = -1;
                for (int c = cols - 1; c >= 0; c--)
                    if (!grid[r][c].startsWith(" ")) {
                        last = c;
                        break;
                    }
                StringBuilder row = new StringBuilder();
                if (last >= 0) {
                    row.append('|');
                    for (int c = 0; c <= last; c++) {
                        if (c > 0)
                            row.append(grid[r][c - 1].startsWith(" ") || grid[r][c].startsWith(" ") ? '|' : ' ');
                        row.append(grid[r][c]);
                    }
                    row.append('|');
                }
                while (row.length() < width)
                    row.append(' ');
                if (r < rows - 1)
                    row.append('\n');
                out.append(jsonEscape(row.toString()));
            }
            out.append(']');
        }

        out.append('}');
        return out.toString();
    }

    static synchronized void handleScreenshot(HttpExchange ex) throws IOException {
        try {
            if (!onMainThread(() -> MinecraftClient.getInstance().player != null)) {
                sendJson(ex, 400, "{\"error\":\"not in game\"}");
                return;
            }
            CompletableFuture<byte[]> f = new CompletableFuture<>();
            ScreenshotCapture.nextPngFuture = f;
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
            byte[] data;
            try {
                data = f.get(3, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                sendJson(ex, 500, "{\"error\":\"screenshot timed out\"}");
                return;
            } catch (Exception e) {
                sendJson(ex, 500, "{\"error\":\"screenshot failed: " + jsonEscape(e.getMessage()) + "\"}");
                return;
            }
            if (data == null) {
                sendJson(ex, 500, "{\"error\":\"screenshot encode failed\"}");
                return;
            }
            String path = ScreenshotCapture.lastPath, name = ScreenshotCapture.lastName,
                    b64 = Base64.getEncoder().encodeToString(data);
            StringBuilder json = new StringBuilder(512);
            json.append("{\"path\":").append(jsonEscape(path)).append(",\"name\":").append(jsonEscape(name)).append(",\"base64\":")
                    .append(jsonEscape(b64));
            double[] pos = posHolder[0];
            if (pos != null) {
                json.append(String.format(",\"x\":%.2f,\"y\":%.2f,\"z\":%.2f,\"yaw\":%.2f,\"pitch\":%.2f", pos[0], pos[1],
                        pos[2], pos[3], pos[4]));
            }
            // tick (since world join; same as log tick stamp)
            json.append(",\"tick\":").append(
                    BindAliasPlusClient.joinTick < 0 ? -1 : (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick));
            json.append('}');
            sendJson(ex, 200, json.toString());
        } catch (Exception e) {
            sendJson(ex, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    static void handleRunAlias(HttpExchange ex) throws IOException {
        Map<String, String> q = parseQuery(ex.getRequestURI().getQuery());
        String def = q.get("def");
        if (def == null || def.isBlank()) {
            String n = q.get("name"), a = q.getOrDefault("args", "");
            if (n != null && !n.isBlank()) {
                def = n;
                if (!a.isEmpty())
                    def += Alias.divider4AliasArgs + a;
            }
        }
        if (def == null || def.isBlank()) {
            sendJson(ex, 400, "{\"error\":\"missing 'def' parameter\"}");
            return;
        }
        final String d = def;
        try {
            String result = onMainThread(() -> {
                // snapshot: captured BEFORE alias execution executes,
                // consistently reflecting state at the moment the call was made
                long tickSinceJoin = BindAliasPlusClient.joinTick < 0 ? -1
                        : (BindAliasPlusClient.currentTick - BindAliasPlusClient.joinTick);
                StringBuilder sb = new StringBuilder("{\"tick\":").append(tickSinceJoin);
                LocalPlayer p = Minecraft.getInstance().player;
                if (p != null) {
                    sb.append(",\"x\":").append(p.getX());
                    sb.append(",\"y\":").append(p.getY());
                    sb.append(",\"z\":").append(p.getZ());
                    sb.append(",\"yaw\":").append(p.getYRot());
                    sb.append(",\"pitch\":").append(p.getXRot());
                }
                sb.append('}');
                String json = sb.toString();
                // dispatch alias chain after snapshot is cached
                // (chain may contain wait\N — deferred effects not in this snapshot)
                new UserAlias(d).run("");
                return json;
            });
            sendJson(ex, 200, result);
        } catch (Exception e) {
            sendJson(ex, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    static void handleDefineAlias(HttpExchange ex) throws IOException {
        Map<String, String> q = parseQuery(ex.getRequestURI().getQuery());
        String name = q.get("name"), def = q.get("def");
        if (name == null || def == null) {
            sendJson(ex, 400, "{\"error\":\"missing 'name' or 'def' parameter\"}");
            return;
        }
        try {
            ChatCapture.begin();
            onMainThread(() -> {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null)
                    mc.player.networkHandler.sendChatCommand("alias " + name + " " + def);
                return null;
            });
            String fb = ChatCapture.end();
            if (fb == null || fb.isEmpty()) {
                sendJson(ex, 500, "{\"error\":\"no feedback from command\"}");
                return;
            }
            sendJson(ex, 200,
                    fb.startsWith("Alias ") ? "{\"ok\":true,\"feedback\":" + jsonEscape(fb) + "}" : "{\"error\":" + jsonEscape(fb) + "}");
        } catch (Exception e) {
            ChatCapture.end();
            sendJson(ex, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    static void handleReadCFG(HttpExchange ex) throws IOException {
        try {
            sendJson(ex, 200, "{\"content\":" + jsonEscape(Files.readString(BindAliasPlusClient.cfgPath)) + "}");
        } catch (Exception e) {
            sendJson(ex, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }

    /** GET /logDiff — return new game-log messages since the last call. */
    static void handleLogDiff(HttpExchange ex) throws IOException {
        String messages = ChatCapture.diff();
        int count = messages.isEmpty() ? 0 : messages.split("\n", -1).length;
        sendJson(ex, 200, "{\"messages\":" + jsonEscape(messages) + ",\"count\":" + count + "}");
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
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendJson(exchange, 405, "{\"error\":\"POST required\"}");
            return;
        }
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
            final String c = content;
            onMainThread(() -> {
                Files.writeString(BindAliasPlusClient.cfgPath, c);
                BindAliasPlusClient.INSTANCE.loadCFG();
                return null;
            });
            sendJson(exchange, 200, "{\"ok\":true}");
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + jsonEscape(e.getMessage()) + "}");
        }
    }
}
