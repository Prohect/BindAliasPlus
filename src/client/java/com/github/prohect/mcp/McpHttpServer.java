package com.github.prohect.mcp;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.builtinAlias.RunAliasAlias;
import com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias;
import com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias;
import com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias;
import com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias;
import com.github.prohect.util.McScreenHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

/**
 * Lightweight HTTP API server bound to {@code 127.0.0.1:25575}.
 * <p>
 * Exposes mod state and alias dispatch so an external MCP bridge can
 * control Minecraft through the mod's alias system.  All game-thread
 * access goes through {@link Minecraft#execute(Runnable)} with a 5 s
 * timeout to prevent the HTTP handler from blocking forever.
 * <p>
 * Started from {@link BindAliasPlusClient#onInitializeClient()}.
 */
public final class McpHttpServer {

    private static final int PORT = 25575;
    private static final int TIMEOUT_SECONDS = 5;
    private static HttpServer server;

    private McpHttpServer() {}

    // ---- lifecycle ----

    /** Start the HTTP server on the default port.  Safe to call multiple times. */
    public static void start() {
        if (server != null) return;
        try {
            server = HttpServer.create(new InetSocketAddress("127.0.0.1", PORT), 0);
            server.createContext("/state", McpHttpServer::handleState);
            server.createContext("/screenshot", McpHttpServer::handleScreenshot);
            server.createContext("/runAlias", McpHttpServer::handleRunAlias);
            server.createContext("/defineAlias", McpHttpServer::handleDefineAlias);
            server.createContext("/readCFG", McpHttpServer::handleReadCFG);
            server.createContext("/writeCFG", McpHttpServer::handleWriteCFG);
            server.setExecutor(Executors.newCachedThreadPool(r -> {
                Thread t = new Thread(r, "BindAliasPlus-MCP");
                t.setDaemon(true);
                return t;
            }));
            server.start();
            Runtime.getRuntime().addShutdownHook(
                new Thread(McpHttpServer::stop, "BindAliasPlus-MCP-Shutdown")
            );
            BindAliasPlusClient.LOGGER.info(
                "{}[MCP] HTTP server started on 127.0.0.1:{}",
                BindAliasPlusClient.tickPrefix(),
                PORT
            );
        } catch (Exception e) {
            BindAliasPlusClient.LOGGER.error(
                "{}[MCP] Failed to start HTTP server",
                BindAliasPlusClient.tickPrefix(),
                e
            );
        }
    }

    /** Stop the HTTP server with a 0-second grace period. */
    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
            BindAliasPlusClient.LOGGER.info(
                "{}[MCP] HTTP server stopped",
                BindAliasPlusClient.tickPrefix()
            );
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
        if (query == null || query.isBlank()) return map;
        for (String pair : query.split("&")) {
            int idx = pair.indexOf('=');
            if (idx > 0) {
                map.put(
                    decodePercent(pair.substring(0, idx)),
                    decodePercent(pair.substring(idx + 1))
                );
            }
        }
        return map;
    }

    /**
     * Percent-decode without converting '+' to space.
     * URLDecoder.decode treats '+' as space (application/x-www-form-urlencoded),
     * which breaks alias names like "+forward" when the bridge sends them unencoded.
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
                } catch (Exception ignored) {}
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /** Send a JSON string as the HTTP response. */
    private static void sendJson(HttpExchange exchange, int code, String json)
        throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set(
            "Content-Type",
            "application/json; charset=utf-8"
        );
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    /** Minimal JSON string escaping (no external dependency). */
    private static String jsonEscape(String s) {
        if (s == null) return "null";
        StringBuilder sb = new StringBuilder("\"");
        for (char c : s.toCharArray()) {
            switch (c) {
                case '"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                default: sb.append(c);
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
                sb.append(
                    screen == null
                        ? "null"
                        : jsonEscape(screen.getClass().getName())
                );

                LocalPlayer p = mc.player;
                if (p != null) {
                    // dimension
                    sb.append(",\"dimension\":");
                    sb.append(
                        jsonEscape(
                            p.level().dimension().toString()
                        )
                    );

                    // world / server name
                    String worldName = null;
                    try {
                        if (mc.getSingleplayerServer() != null) {
                            worldName = mc
                                .getSingleplayerServer()
                                .getWorldData()
                                .getLevelName();
                        } else if (mc.getCurrentServer() != null) {
                            worldName = mc.getCurrentServer().name;
                        }
                    } catch (Exception ignored) {
                        // best-effort; ignore if mappings differ
                    }
                    sb.append(",\"worldName\":");
                    sb.append(
                        worldName == null ? "null" : jsonEscape(worldName)
                    );

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
                        sb.append(
                            jsonEscape(
                                BuiltInRegistries.ITEM
                                    .getKey(held.getItem())
                                    .toString()
                            )
                        );
                        sb.append(",\"heldItemCount\":").append(held.getCount());
                    } else {
                        sb.append(",\"heldItem\":null");
                        sb.append(",\"heldItemCount\":0");
                    }

                    // hotbar slot (1-indexed)
                    sb.append(",\"hotbarSlot\":")
                        .append(p.getInventory().getSelectedSlot() + 1);
                }

                sb.append('}');
                return sb.toString();
            });
            sendJson(exchange, 200, json);
        } catch (Exception e) {
            sendJson(
                exchange,
                500,
                "{\"error\":" + jsonEscape(e.getMessage()) + "}"
            );
        }
    }

    /** GET /screenshot — trigger a screenshot and return it base64-encoded. */
    static void handleScreenshot(HttpExchange exchange) throws IOException {
        try {
            String json = onMainThread(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null) {
                    return "{\"error\":\"not in game\"}";
                }

                Path dir = mc.gameDirectory.toPath().resolve("screenshots");
                try { Files.createDirectories(dir); } catch (IOException ignored) {}

                // Record time before triggering to identify the new screenshot
                long triggerTime = System.currentTimeMillis();

                // trigger native screenshot directly (no-op callback suppresses chat msg)
                net.minecraft.client.Screenshot.grab(
                    mc.gameDirectory,
                    null,
                    mc.gameRenderer.mainRenderTarget(),
                    1,
                    msg -> {}
                );

                // Wait for the file to be written, then find by modification time
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
                Path newFile = null;
                try (var stream = Files.list(dir)) {
                    newFile = stream
                        .filter(p -> p.getFileName().toString().endsWith(".png"))
                        .filter(p -> {
                            try {
                                return Files.getLastModifiedTime(p).toMillis() >= triggerTime;
                            } catch (IOException e) { return false; }
                        })
                        .findFirst()
                        .orElse(null);
                } catch (IOException ignored) {}

                // Fallback: pick the most recently modified screenshot
                if (newFile == null) {
                    try (var stream = Files.list(dir)) {
                        newFile = stream
                            .filter(p -> p.getFileName().toString().endsWith(".png"))
                            .max((a, b) -> {
                                try { return Files.getLastModifiedTime(a).compareTo(Files.getLastModifiedTime(b)); }
                                catch (IOException e) { return 0; }
                            })
                            .orElse(null);
                    } catch (IOException ignored) {}
                }
                if (newFile == null) {
                    return "{\"error\":\"screenshot not found after 3 s\"}";
                }

                try {
                    byte[] data = Files.readAllBytes(newFile);
                    String b64 = Base64.getEncoder().encodeToString(data);
                    return (
                        "{\"path\":" +
                        jsonEscape(newFile.toString()) +
                        ",\"name\":" +
                        jsonEscape(newFile.getFileName().toString()) +
                        ",\"content\":" +
                        jsonEscape(b64) +
                        ",\"base64\":" +
                        jsonEscape(b64) +
                        "}"
                    );
                } catch (IOException e) {
                    return (
                        "{\"error\":" +
                        jsonEscape("failed to read: " + e.getMessage()) +
                        "}"
                    );
                }
            });
            sendJson(exchange, 200, json);
        } catch (Exception e) {
            sendJson(
                exchange,
                500,
                "{\"error\":" + jsonEscape(e.getMessage()) + "}"
            );
        }
    }

    /** POST /runAlias?name=…&args=… — execute a registered alias. */
    static void handleRunAlias(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(
            exchange.getRequestURI().getQuery()
        );
        String name = q.get("name");
        String args = q.getOrDefault("args", "");

        if (name == null || name.isBlank()) {
            sendJson(
                exchange,
                400,
                "{\"error\":\"missing 'name' parameter\"}"
            );
            return;
        }

        try {
            String result = onMainThread(() -> {
                // Pre-check alias existence — RunAliasAlias silently logs unknown aliases
                if (Alias.aliasesWithoutArgs.get(name) == null
                    && Alias.aliasesWithoutArgs_notSuggested.get(name) == null
                    && Alias.aliasesWithArgs.get(name) == null
                    && Alias.aliasesWithArgs_notSuggested.get(name) == null) {
                    return "{\"error\":\"unknown alias: " + name + "\"}";
                }

                String full = name;
                if (!args.isEmpty()) {
                    full += Alias.divider4AliasArgs + args;
                }
                new RunAliasAlias().run(full);
                return "{\"ok\":true}";
            });
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(
                exchange,
                500,
                "{\"error\":" + jsonEscape(e.getMessage()) + "}"
            );
        }
    }

    /** POST /defineAlias?name=…&def=… — create an alias via sendCommand. */
    static void handleDefineAlias(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(
            exchange.getRequestURI().getQuery()
        );
        String name = q.get("name");
        String def = q.get("def");

        if (name == null || def == null) {
            sendJson(
                exchange,
                400,
                "{\"error\":\"missing 'name' or 'def' parameter\"}"
            );
            return;
        }

        try {
            String result = onMainThread(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null) return "{\"error\":\"not in game\"}";
                mc.player.connection.sendCommand(
                    "alias " + name + " " + def
                );
                return "{\"ok\":true}";
            });
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(
                exchange,
                500,
                "{\"error\":" + jsonEscape(e.getMessage()) + "}"
            );
        }
    }

    /** GET /readCFG — return the raw config file content. */
    static void handleReadCFG(HttpExchange exchange) throws IOException {
        try {
            String content = Files.readString(BindAliasPlusClient.cfgPath);
            sendJson(
                exchange,
                200,
                "{\"content\":" + jsonEscape(content) + "}"
            );
        } catch (IOException e) {
            sendJson(
                exchange,
                500,
                "{\"error\":" +
                jsonEscape("failed to read: " + e.getMessage()) +
                "}"
            );
        }
    }

    /** POST /writeCFG — overwrite the config file and reload. */
    static void handleWriteCFG(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(
            exchange.getRequestURI().getQuery()
        );
        String content = q.get("content");

        // if content not in query, try JSON body
        if (content == null) {
            String body;
            try (InputStream is = exchange.getRequestBody()) {
                body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
            if (body != null && body.contains("\"content\"")) {
                int start = body.indexOf("\"content\"");
                int colon = body.indexOf(':', start);
                int valStart = body.indexOf('"', colon + 1);
                int valEnd = body.indexOf('"', valStart + 1);
                if (valStart > 0 && valEnd > valStart) {
                    content = body.substring(valStart + 1, valEnd);
                    content = content
                        .replace("\\n", "\n")
                        .replace("\\r", "\r")
                        .replace("\\t", "\t")
                        .replace("\\\\", "\\")
                        .replace("\\\"", "\"");
                }
            }
        }

        if (content == null) {
            sendJson(
                exchange,
                400,
                "{\"error\":\"missing 'content'\"}"
            );
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
            sendJson(
                exchange,
                500,
                "{\"error\":" + jsonEscape(e.getMessage()) + "}"
            );
        }
    }
}
