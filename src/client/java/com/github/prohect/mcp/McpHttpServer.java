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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;

/**
 * Lightweight HTTP API server (default {@code 127.0.0.1:25575}, falls back to the next free port up to +9 when occupied — the
 * chosen port is logged).
 * <p>
 * Every game-interacting endpoint returns the same <b>envelope</b> assembled by {@link StateTracker}: {@code {"client_tick":N,
 * "state":{...}, "chat":[...], "mod":[...], "sound":[...], "recipe":[...]}} — changed-state diff (full for {@code /state}) plus
 * freshly drained message channels, so callers never need a separate poll for feedback. {@code /readCFG} is the only
 * non-envelope endpoint (raw file text). All game-thread access goes through {@link Minecraft#execute(Runnable)} with a timeout
 * to keep HTTP threads from blocking forever.
 * <p>
 * Started from {@link BindAliasPlusClient#onInitializeClient()}.
 */
public final class McpHttpServer {

    private static final int DEFAULT_PORT = 25575;
    private static final int MAX_PORT_ATTEMPTS = 10;
    private static final int TIMEOUT_SECONDS = 5;
    /** Max {@code nap} value — 1200 client ticks = 60 s at the nominal 20 client ticks per second. */
    private static final long MAX_NAP_TICKS = 1200;
    /** Grace beyond a nap's expected wall time (ticks × 50 ms) before the request is failed as not-ticking. */
    private static final long NAP_TIMEOUT_MARGIN_MS = 15_000;
    private static HttpServer server;
    private static int port = DEFAULT_PORT;

    private McpHttpServer() {}

    // ---- lifecycle ----

    /** Start the HTTP server on the first free port from {@value #DEFAULT_PORT} upwards. Safe to call multiple times. */
    public static void start() {
        if (server != null)
            return;
        for (int attempt = 0; attempt < MAX_PORT_ATTEMPTS; attempt++) {
            int candidate = DEFAULT_PORT + attempt;
            try {
                server = HttpServer.create(new InetSocketAddress("127.0.0.1", candidate), 0);
                port = candidate;
                break;
            } catch (IOException e) {
                BindAliasPlusClient.LOGGER.warn("{}[MCP] Port {} unavailable, trying next", BindAliasPlusClient.tickPrefix(),
                        candidate);
            }
        }
        if (server == null) {
            BindAliasPlusClient.LOGGER.error("{}[MCP] Failed to start HTTP server: no free port in {}-{}",
                    BindAliasPlusClient.tickPrefix(), DEFAULT_PORT, DEFAULT_PORT + MAX_PORT_ATTEMPTS - 1);
            return;
        }
        server.createContext("/state", McpHttpServer::handleState);
        server.createContext("/screenshot", McpHttpServer::handleScreenshot);
        server.createContext("/runAlias", McpHttpServer::handleRunAlias);
        server.createContext("/defineAlias", McpHttpServer::handleDefineAlias);
        server.createContext("/readCFG", McpHttpServer::handleReadCFG);
        server.createContext("/writeCFG", McpHttpServer::handleWriteCFG);
        server.createContext("/listRecipes", McpHttpServer::handleListRecipes);
        server.setExecutor(Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "BindAliasPlus-MCP");
            t.setDaemon(true);
            return t;
        }));
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(McpHttpServer::stop, "BindAliasPlus-MCP-Shutdown"));
        BindAliasPlusClient.LOGGER.info("{}[MCP] HTTP server started on 127.0.0.1:{}", BindAliasPlusClient.tickPrefix(), port);
    }

    /** Stop the HTTP server with a 0-second grace period. */
    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
            BindAliasPlusClient.LOGGER.info("{}[MCP] HTTP server stopped", BindAliasPlusClient.tickPrefix());
        }
    }

    /** The port the server actually bound (≤ {@value #DEFAULT_PORT} + 9). */
    public static int port() {
        return port;
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

    @FunctionalInterface
    private interface CheckedSupplier<T> {
        T get() throws Exception;
    }

    // ---- nap (client_tick deferred responses) ----

    /** Pending nap responses — ticked by {@code MinecraftClientMixin} on every client tick. */
    private static final List<NapTask> NAP_TASKS = new CopyOnWriteArrayList<>();

    private static final class NapTask {
        /** Client ticks remaining (access under {@code synchronized (this)}). */
        long ticksLeft;
        /** Set by the HTTP thread when the request timed out — the capture must never happen then. */
        boolean cancelled;
        final CompletableFuture<String> future;

        NapTask(long ticksLeft, CompletableFuture<String> future) {
            this.ticksLeft = ticksLeft;
            this.future = future;
        }
    }

    /**
     * Count down pending nap responses; on expiry capture the envelope fresh (newest state diff + channels drained, so
     * everything produced during the nap is delivered). Called from {@code MinecraftClientMixin} after the WaitAlias queue, so
     * a {@code wait\N} task expiring on the same client_tick is already reflected. The {@code synchronized} makes
     * cancel-vs-capture atomic: a cancelled nap never drains channels into a response nobody reads.
     */
    public static void tickNapTasks() {
        for (NapTask task : NAP_TASKS) {
            synchronized (task) {
                if (task.cancelled) {
                    NAP_TASKS.remove(task);
                    continue;
                }
                if (--task.ticksLeft > 0)
                    continue;
                NAP_TASKS.remove(task);
                task.future.complete(StateTracker.finish(StateTracker.begin(false)));
            }
        }
    }

    // ---- endpoints ----

    /** GET /state — full state snapshot + drained channels. */
    static void handleState(HttpExchange exchange) throws IOException {
        try {
            String json = onMainThread(() -> StateTracker.finish(StateTracker.begin(true)));
            sendJson(exchange, 200, json);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /** GET /screenshot — in-memory PNG (base64) merged with the standard envelope. */
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
            CompletableFuture<byte[]> future = new CompletableFuture<>();
            ScreenshotCapture.nextPngFuture = future;

            String begun = onMainThread(() -> {
                String env = StateTracker.begin(false);
                Minecraft mc = Minecraft.getInstance();
                net.minecraft.client.Screenshot.grab(mc.gameDirectory, null, mc.gameRenderer.mainRenderTarget(), 1, msg -> {
                });
                return env;
            });

            // Wait on the HTTP thread (not the game thread) for the
            // I/O-thread PNG encode to finish.
            byte[] data;
            try {
                data = future.get(3, TimeUnit.SECONDS);
            } catch (java.util.concurrent.TimeoutException e) {
                sendJson(exchange, 500, "{\"error\":\"screenshot timed out\"}");
                return;
            } catch (Exception e) {
                sendJson(exchange, 500,
                        "{\"error\":\"screenshot failed: " + GameStateCollector.jsonEscape(e.getMessage()) + "\"}");
                return;
            }

            if (data == null) {
                sendJson(exchange, 500, "{\"error\":\"screenshot encode failed\"}");
                return;
            }

            String b64 = java.util.Base64.getEncoder().encodeToString(data);
            String envelope = StateTracker.finish(begun);
            // merge: {"base64":"...", <envelope members>}
            String json = "{\"base64\":" + GameStateCollector.jsonEscape(b64) + "," + envelope.substring(1);
            sendJson(exchange, 200, json);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * POST /runAlias?def=…[&nap=N] — execute a chain of aliases (space-separated, \ for args). The state diff is captured
     * BEFORE execution; message channels are drained AFTER the immediate part of the chain ran, so {@code log\} and chat
     * feedback inside the chain is delivered with this response. Deferred effects (after {@code wait\N}) show up in later
     * responses.
     * <p>
     * {@code nap=N} (client_tick, the same unit as {@code wait\N}; 0-{@value #MAX_NAP_TICKS}) defers the whole response: the
     * chain still runs immediately, but the envelope is captured only after N client_tick elapsed — newest state diff plus
     * every message produced during the nap.
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

        long napTicks = 0;
        String napParam = q.get("nap");
        if (napParam != null && !napParam.isBlank()) {
            try {
                napTicks = Long.parseLong(napParam.trim());
            } catch (NumberFormatException e) {
                napTicks = -1;
            }
            if (napTicks < 0 || napTicks > MAX_NAP_TICKS) {
                sendJson(exchange, 400,
                        "{\"error\":\"invalid 'nap' — integer client_tick in [0," + MAX_NAP_TICKS + "] expected\"}");
                return;
            }
        }

        final String definition = def;
        final long nap = napTicks;
        final NapTask napTask = nap == 0 ? null : new NapTask(nap, new CompletableFuture<>());
        try {
            String result;
            if (napTask == null) {
                result = onMainThread(() -> {
                    String begun = StateTracker.begin(false);
                    new UserAlias(definition).run("");
                    return StateTracker.finish(begun);
                });
            } else {
                NapTask task = napTask;
                onMainThread(() -> {
                    new UserAlias(definition).run("");
                    NAP_TASKS.add(task);
                    return null;
                });
                try {
                    // a client_tick is 50 ms at nominal speed; the margin absorbs lag/pause hiccups
                    result = task.future.get(nap * 50 + NAP_TIMEOUT_MARGIN_MS, TimeUnit.MILLISECONDS);
                } catch (java.util.concurrent.TimeoutException e) {
                    // Game stopped ticking — cancel so a late tick never captures (and drains channels)
                    // into a response nobody reads; a capture that already won the race is still used.
                    synchronized (task) {
                        task.cancelled = true;
                    }
                    result = task.future.getNow(null);
                    if (result == null) {
                        sendJson(exchange, 500, "{\"error\":\"nap timed out — game not ticking?\"}");
                        return;
                    }
                }
            }
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            // a nap task whose scheduling failed (main thread stalled) must never fire late either
            if (napTask != null) {
                synchronized (napTask) {
                    napTask.cancelled = true;
                }
            }
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * POST /defineAlias?name=…&def=… — define an alias via the real {@code /alias} client command. The command's feedback line
     * ({@code "Alias x = ..."} / {@code "Can't replace builtinAlias x"}) is delivered in the response's {@code chat} channel.
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
            String result = onMainThread(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null)
                    return null;
                String begun = StateTracker.begin(false);
                mc.player.connection.sendCommand("alias " + name + " " + def);
                return StateTracker.finish(begun);
            });
            if (result == null) {
                sendJson(exchange, 400, "{\"error\":\"not in world\"}");
                return;
            }
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /** GET /readCFG — return the raw config file content (the only non-envelope endpoint). */
    static void handleReadCFG(HttpExchange exchange) throws IOException {
        try {
            String content = Files.readString(BindAliasPlusClient.cfgPath);
            sendJson(exchange, 200, "{\"content\":" + GameStateCollector.jsonEscape(content) + "}");
        } catch (IOException e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape("failed to read: " + e.getMessage()) + "}");
        }
    }

    /** POST /writeCFG — overwrite the config file and reload it (reload log lines arrive via the mod channel). */
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
            String result = onMainThread(() -> {
                String begun = StateTracker.begin(false);
                BindAliasPlusClient.INSTANCE.loadCFG();
                return StateTracker.finish(begun);
            });
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * GET /listRecipes[?q=a,b,c] — list recipes unlocked in the recipe book. Only works while an
     * {@link AbstractRecipeBookScreen} is open. Without {@code q}: recipes learned since the previous call (diff). With
     * {@code q} (comma-separated item ids or name substrings): every query is answered independently — {@code recipes} holds
     * the matches, {@code recipe_errors} the per-query failures (one bad query never eats valid results).
     */
    static void handleListRecipes(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String queryParam = q.get("q");
        try {
            String result = onMainThread(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null)
                    return null;
                if (!(McScreenHelper.getCurrentScreen(mc) instanceof AbstractRecipeBookScreen))
                    return "";
                String begun = StateTracker.begin(false);
                List<RecipeBookHelper.RecipeInfo> recipes;
                List<String> errors = new ArrayList<>();
                if (queryParam == null || queryParam.isBlank()) {
                    recipes = RecipeBookHelper.onlyNew(RecipeBookHelper.unlocked(mc));
                } else {
                    recipes = new ArrayList<>();
                    List<RecipeBookHelper.RecipeInfo> all = RecipeBookHelper.unlocked(mc);
                    for (String query : queryParam.split(",")) {
                        String trimmed = query.trim();
                        if (trimmed.isEmpty())
                            continue;
                        List<RecipeBookHelper.RecipeInfo> matches =
                                all.stream().filter(r -> RecipeBookHelper.matches(r, trimmed)).toList();
                        if (matches.isEmpty()) {
                            errors.add("'" + trimmed + "': no matching unlocked recipe");
                        } else {
                            for (RecipeBookHelper.RecipeInfo r : matches)
                                if (!recipes.contains(r))
                                    recipes.add(r);
                        }
                    }
                }
                String envelope = StateTracker.finish(begun);
                // pre-size for envelope + recipes (~80 chars/recipe) + optional errors
                int estCap = envelope.length() + recipes.size() * 80 + errors.size() * 60;
                StringBuilder sb = new StringBuilder(estCap);
                sb.append(envelope, 0, envelope.length() - 1);
                sb.append(",\"recipes\":").append(RecipeBookHelper.recipesJson(recipes));
                if (!errors.isEmpty()) {
                    sb.append(",\"recipe_errors\":[");
                    for (int i = 0; i < errors.size(); i++) {
                        if (i > 0)
                            sb.append(',');
                        sb.append(GameStateCollector.jsonEscape(errors.get(i)));
                    }
                    sb.append(']');
                }
                return sb.append('}').toString();
            });
            if (result == null) {
                sendJson(exchange, 400, "{\"error\":\"not in world\"}");
                return;
            }
            if (result.isEmpty()) {
                sendJson(exchange, 400, "{\"error\":\"no recipe book screen open\"}");
                return;
            }
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
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
}
