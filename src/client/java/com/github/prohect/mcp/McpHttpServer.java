package com.github.prohect.mcp;

import com.github.prohect.BindAliasClient;
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
import java.nio.file.Path;
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
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.TickRateManager;

/**
 * Lightweight HTTP API server (default {@code 127.0.0.1:25575}, falls back to the next free port up to +9 when occupied — the
 * chosen port is logged).
 * <p>
 * Every game-interacting endpoint returns the same <b>envelope</b> assembled by {@link StateTracker}: {@code {"client_tick":N,
 * "state":{...}, "chat":[...], "mod":[...], "sound":[...], "unlocked_recipe":[...]}} — changed-state diff (full for
 * {@code /state}) plus freshly drained message channels, so callers never need a separate poll for feedback. {@code /readCFG}
 * is the only non-envelope endpoint (raw file text). All game-thread access goes through {@link Minecraft#execute(Runnable)}
 * with a timeout to keep HTTP threads from blocking forever.
 * <p>
 * Started from {@link BindAliasClient#onInitializeClient()}.
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
                BindAliasClient.LOGGER.warn("{}[MCP] Port {} unavailable, trying next", BindAliasClient.tickPrefix(),
                        candidate);
            }
        }
        if (server == null) {
            BindAliasClient.LOGGER.error("{}[MCP] Failed to start HTTP server: no free port in {}-{}",
                    BindAliasClient.tickPrefix(), DEFAULT_PORT, DEFAULT_PORT + MAX_PORT_ATTEMPTS - 1);
            return;
        }
        server.createContext("/screenshot", McpHttpServer::handleScreenshot);
        server.createContext("/runAlias", McpHttpServer::handleRunAlias);
        server.createContext("/defineAlias", McpHttpServer::handleDefineAlias);
        server.createContext("/readCFG", McpHttpServer::handleReadCFG);
        server.createContext("/writeCFG", McpHttpServer::handleWriteCFG);
        server.createContext("/readNotes", McpHttpServer::handleReadNotes);
        server.createContext("/writeNotes", McpHttpServer::handleWriteNotes);
        server.createContext("/listRecipes", McpHttpServer::handleListRecipes);
        server.setExecutor(Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "BindAlias-MCP");
            t.setDaemon(true);
            return t;
        }));
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(McpHttpServer::stop, "BindAlias-MCP-Shutdown"));
        BindAliasClient.LOGGER.info("{}[MCP] HTTP server started on 127.0.0.1:{}", BindAliasClient.tickPrefix(), port);
    }

    /** Stop the HTTP server with a 0-second grace period. */
    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
            BindAliasClient.LOGGER.info("{}[MCP] HTTP server stopped", BindAliasClient.tickPrefix());
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

    /** Parse the optional {@code verbose} flag (absent → diff; "true"/"1" → full state snapshot). */
    private static boolean parseVerbose(Map<String, String> q) {
        String v = q.get("verbose");
        return v != null && (v.equals("true") || v.equals("1"));
    }

    /**
     * Parse the optional {@code nap} parameter.
     *
     * @return 0 when absent, the tick count when valid, -1 when malformed or outside [0, {@value #MAX_NAP_TICKS}]
     */
    private static long parseNap(Map<String, String> q) {
        String napParam = q.get("nap");
        if (napParam == null || napParam.isBlank())
            return 0;
        final long n;
        try {
            n = Long.parseLong(napParam.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
        return n >= 0 && n <= MAX_NAP_TICKS ? n : -1;
    }

    /** Merge extra members ({@code "\"recipes\":[...]"}) into an envelope just before its closing brace. */
    private static String mergeExtra(String envelope, String extra) {
        if (extra == null || extra.isEmpty())
            return envelope;
        return envelope.substring(0, envelope.length() - 1) + ',' + extra + '}';
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
        /** True when this nap engaged fast-forward — its removal must be balanced by {@link #fastForwardEnd()}. */
        boolean fastForward;
        /** Capture the full state snapshot instead of the diff when the nap expires. */
        boolean verbose;
        final CompletableFuture<String> future;

        NapTask(long ticksLeft, CompletableFuture<String> future) {
            this.ticksLeft = ticksLeft;
            this.future = future;
        }
    }

    // ---- nap fast-forward (slow-world bench acceleration) ----

    /** Naps at least this long fast-forward the integrated server for their duration. */
    private static final long NAP_FF_MIN_TICKS = 10;
    /**
     * Fast-forward rate. 20 tps is the client tick cap ({@code Minecraft.getTickTargetMillis} = max(50 ms, msPerTick)), so
     * client and integrated server stay in lockstep; going higher would let the server outrun client ticks.
     */
    private static final float NAP_FF_RATE = 20.0F;
    /** Guards {@link #ffActiveNaps} and {@link #ffPreviousRate} (HTTP threads + client tick thread). */
    private static final Object FF_LOCK = new Object();
    private static int ffActiveNaps;
    private static float ffPreviousRate;

    /**
     * Engage fast-forward for a nap about to be scheduled; the pre-nap rate is recorded on the first overlapping nap and
     * restored when the last one ends. Called on the main thread.
     *
     * @return true when fast-forward was engaged — the caller must then flag the NapTask so its removal (expiry or cancel) is
     *         balanced by {@link #fastForwardEnd()}
     */
    private static boolean fastForwardBegin() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null)
            return false; // remote server — no tick-rate control
        final boolean accelerate;
        synchronized (FF_LOCK) {
            if (ffActiveNaps == 0)
                ffPreviousRate = server.tickRateManager().tickrate();
            ffActiveNaps++;
            // no-op when the world already runs at or above the ff rate (the client caps at 20 tps anyway)
            accelerate = ffPreviousRate < NAP_FF_RATE;
        }
        try {
            if (accelerate)
                server.execute(() -> server.tickRateManager().setTickRate(NAP_FF_RATE));
        } catch (RuntimeException e) { // server stopping — roll back so the counter never gets stuck
            synchronized (FF_LOCK) {
                ffActiveNaps--;
            }
            return false;
        }
        return true;
    }

    /** Balance a {@link #fastForwardBegin()} — restores the pre-nap rate once the last overlapping fast-forward nap ends. */
    private static void fastForwardEnd() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        synchronized (FF_LOCK) {
            if (--ffActiveNaps > 0)
                return;
        }
        if (server == null)
            return; // world left mid-nap — the rate dies with the server anyway
        server.execute(() -> {
            TickRateManager trm = server.tickRateManager();
            // skip the restore when someone else changed the rate during the nap — their value wins
            if (trm.tickrate() == NAP_FF_RATE)
                trm.setTickRate(ffPreviousRate);
        });
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
                    if (task.fastForward)
                        fastForwardEnd();
                    continue;
                }
                if (--task.ticksLeft > 0)
                    continue;
                NAP_TASKS.remove(task);
                if (task.fastForward)
                    fastForwardEnd();
                task.future.complete(StateTracker.finish(StateTracker.begin(task.verbose)));
            }
        }
    }

    // ---- endpoints ----

    /**
     * Shared nap path of the envelope endpoints: run {@code action} on the main thread immediately (its return value is merged
     * into the final envelope as extra members), then defer the envelope capture by {@code nap} client_tick and block the HTTP
     * thread for it. Fast-forward engages for {@code nap >= NAP_FF_MIN_TICKS}. On scheduling failure or timeout the task is
     * cancelled (no late capture draining channels into a response nobody reads), a 500 is sent, and null is returned.
     */
    private static String runWithNap(HttpExchange exchange, long nap, boolean verbose, CheckedSupplier<String> action)
            throws IOException {
        CompletableFuture<String> future = new CompletableFuture<>();
        NapTask task = new NapTask(nap, future);
        task.verbose = verbose;
        String[] extra = new String[1];
        try {
            onMainThread(() -> {
                extra[0] = action.get();
                if (nap >= NAP_FF_MIN_TICKS)
                    task.fastForward = fastForwardBegin();
                NAP_TASKS.add(task);
                return null;
            });
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
            return null;
        }
        String envelope;
        try {
            // a client_tick is 50 ms at nominal speed; the margin absorbs lag/pause hiccups
            envelope = future.get(nap * 50 + NAP_TIMEOUT_MARGIN_MS, TimeUnit.MILLISECONDS);
        } catch (java.util.concurrent.TimeoutException e) {
            synchronized (task) {
                task.cancelled = true;
            }
            envelope = future.getNow(null);
            if (envelope == null) {
                sendJson(exchange, 500, "{\"error\":\"nap timed out — game not ticking?\"}");
                return null;
            }
        } catch (Exception e) {
            synchronized (task) {
                task.cancelled = true;
            }
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
            return null;
        }
        return mergeExtra(envelope, extra[0]);
    }

    /** GET /screenshot[?verbose=1][&nap=N] — in-memory PNG (base64) merged with the standard envelope. */
    static synchronized void handleScreenshot(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        long nap = parseNap(q);
        if (nap < 0) {
            sendJson(exchange, 400,
                    "{\"error\":\"invalid 'nap' — integer client_tick in [0," + MAX_NAP_TICKS + "] expected\"}");
            return;
        }
        boolean verbose = parseVerbose(q);
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

            final long napTicks = nap;
            String begun = onMainThread(() -> {
                String env = napTicks == 0 ? StateTracker.begin(verbose) : null;
                Minecraft mc = Minecraft.getInstance();
                net.minecraft.client.Screenshot.grab(mc.gameDirectory, null, mc.getMainRenderTarget(), 1, msg -> {
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

            String envelope;
            if (napTicks == 0) {
                envelope = StateTracker.finish(begun);
            } else {
                envelope = runWithNap(exchange, napTicks, verbose, () -> "");
                if (envelope == null)
                    return; // error already sent
            }
            String b64 = java.util.Base64.getEncoder().encodeToString(data);
            sendJson(exchange, 200, mergeExtra(envelope, "\"base64\":" + GameStateCollector.jsonEscape(b64)));
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * POST /runAlias?def=…[&verbose=1][&nap=N] — execute a chain of aliases (space-separated, \ for args). Without {@code nap},
     * the state diff is captured BEFORE execution and message channels are drained AFTER the immediate part of the chain ran,
     * so {@code log\} and chat feedback inside the chain is delivered with this response. Deferred effects (after
     * {@code wait\N}) show up in later responses.
     * <p>
     * {@code nap=N} (client_tick, the same unit as {@code wait\N}; 0-{@value #MAX_NAP_TICKS}) defers the whole response: the
     * chain still runs immediately, but the envelope is captured only after N client_tick elapsed — newest state diff plus
     * every message produced during the nap. {@code verbose} makes the captured state the full snapshot.
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

        long nap = parseNap(q);
        if (nap < 0) {
            sendJson(exchange, 400,
                    "{\"error\":\"invalid 'nap' — integer client_tick in [0," + MAX_NAP_TICKS + "] expected\"}");
            return;
        }
        boolean verbose = parseVerbose(q);

        final String definition = def;
        try {
            String result;
            if (nap == 0) {
                result = onMainThread(() -> {
                    String begun = StateTracker.begin(verbose);
                    new UserAlias(definition).run("");
                    return StateTracker.finish(begun);
                });
            } else {
                result = runWithNap(exchange, nap, verbose, () -> {
                    new UserAlias(definition).run("");
                    return "";
                });
                if (result == null)
                    return; // error already sent
            }
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * POST /defineAlias?name=…&def=…[&verbose=1][&nap=N] — define an alias via the real {@code /alias} client command. The
     * command's feedback line ({@code "Alias x = ..."} / {@code "Can't replace builtinAlias x"}) is delivered in the response's
     * {@code chat} channel.
     */
    static void handleDefineAlias(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String name = q.get("name");
        String def = q.get("def");

        if (name == null || def == null) {
            sendJson(exchange, 400, "{\"error\":\"missing 'name' or 'def' parameter\"}");
            return;
        }

        long nap = parseNap(q);
        if (nap < 0) {
            sendJson(exchange, 400,
                    "{\"error\":\"invalid 'nap' — integer client_tick in [0," + MAX_NAP_TICKS + "] expected\"}");
            return;
        }
        boolean verbose = parseVerbose(q);

        final String command = "alias " + name + " " + def;
        try {
            Boolean inWorld = onMainThread(() -> Minecraft.getInstance().player != null);
            if (!inWorld) {
                sendJson(exchange, 400, "{\"error\":\"not in world\"}");
                return;
            }
            String result;
            if (nap == 0) {
                result = onMainThread(() -> {
                    Minecraft mc = Minecraft.getInstance();
                    String begun = StateTracker.begin(verbose);
                    mc.player.connection.sendCommand(command);
                    return StateTracker.finish(begun);
                });
            } else {
                result = runWithNap(exchange, nap, verbose, () -> {
                    Minecraft.getInstance().player.connection.sendCommand(command);
                    return "";
                });
                if (result == null)
                    return; // error already sent
            }
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /** GET /readCFG — return the per-save agent cfg file content (non-envelope). Requires in a singleplayer world. */
    static void handleReadCFG(HttpExchange exchange) throws IOException {
        try {
            Path path = onMainThread(BindAliasClient::agentCfgPath);
            if (path == null) {
                sendJson(exchange, 400, "{\"error\":\"agent cfg is only available in a singleplayer world\"}");
                return;
            }
            String content;
            try {
                content = Files.readString(path);
            } catch (IOException e) {
                sendJson(exchange, 500,
                        "{\"error\":" + GameStateCollector.jsonEscape("failed to read: " + e.getMessage()) + "}");
                return;
            }
            sendJson(exchange, 200, "{\"content\":" + GameStateCollector.jsonEscape(content) + "}");
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /** POST /writeCFG — overwrite the per-save agent cfg file and reload it. Requires in a singleplayer world. */
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

        long nap = parseNap(q);
        if (nap < 0) {
            sendJson(exchange, 400,
                    "{\"error\":\"invalid 'nap' — integer client_tick in [0," + MAX_NAP_TICKS + "] expected\"}");
            return;
        }
        boolean verbose = parseVerbose(q);

        final String finalContent = content;
        try {
            Path path = onMainThread(BindAliasClient::agentCfgPath);
            if (path == null) {
                sendJson(exchange, 400, "{\"error\":\"agent cfg is only available in a singleplayer world\"}");
                return;
            }
            try {
                Path parent = path.getParent();
                if (parent != null)
                    Files.createDirectories(parent);
                Files.writeString(path, finalContent);
            } catch (IOException e) {
                sendJson(exchange, 500,
                        "{\"error\":" + GameStateCollector.jsonEscape("failed to write: " + e.getMessage()) + "}");
                return;
            }
            String result;
            if (nap == 0) {
                result = onMainThread(() -> {
                    String begun = StateTracker.begin(verbose);
                    BindAliasClient.loadAgentCFG();
                    return StateTracker.finish(begun);
                });
            } else {
                result = runWithNap(exchange, nap, verbose, () -> {
                    BindAliasClient.loadAgentCFG();
                    return "";
                });
                if (result == null)
                    return; // error already sent
            }
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * GET /readNotes?file=NAME — read the entire content of a file inside the per-save agent directory
     * ({@code <game_root>/saves/<save>/bind-alias/<file>}). Requires in a singleplayer world.
     */
    static void handleReadNotes(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String file = q.get("file");
        if (file == null || file.isBlank()) {
            sendJson(exchange, 400, "{\"error\":\"missing 'file' parameter\"}");
            return;
        }
        // sanitize: reject paths that try to escape the agent directory
        if (file.contains("..") || file.contains("/") || file.contains("\\")) {
            sendJson(exchange, 400, "{\"error\":\"invalid 'file' — must be a plain filename (no path separators or '..')\"}");
            return;
        }
        try {
            Path dir = onMainThread(BindAliasClient::agentDir);
            if (dir == null) {
                sendJson(exchange, 400, "{\"error\":\"notes are only available in a singleplayer world\"}");
                return;
            }
            Path filePath = dir.resolve(file);
            String content;
            try {
                if (!Files.exists(filePath))
                    content = "";
                else
                    content = Files.readString(filePath);
            } catch (IOException e) {
                sendJson(exchange, 500,
                        "{\"error\":" + GameStateCollector.jsonEscape("failed to read: " + e.getMessage()) + "}");
                return;
            }
            sendJson(exchange, 200, "{\"content\":" + GameStateCollector.jsonEscape(content) + "}");
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * POST /writeNotes?file=NAME&content=TEXT — overwrite a file inside the per-save agent directory
     * ({@code <game_root>/saves/<save>/bind-alias/<file>}) with the given content. Requires in a singleplayer world. If content
     * is not in the query string, it is read from the JSON body field {@code "content"}.
     */
    static void handleWriteNotes(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String file = q.get("file");
        String content = q.get("content");

        if (file == null || file.isBlank()) {
            sendJson(exchange, 400, "{\"error\":\"missing 'file' parameter\"}");
            return;
        }
        // sanitize: reject paths that try to escape the agent directory
        if (file.contains("..") || file.contains("/") || file.contains("\\")) {
            sendJson(exchange, 400, "{\"error\":\"invalid 'file' — must be a plain filename (no path separators or '..')\"}");
            return;
        }

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

        final String finalContent = content;
        final String finalFile = file;
        try {
            Path dir = onMainThread(BindAliasClient::agentDir);
            if (dir == null) {
                sendJson(exchange, 400, "{\"error\":\"notes are only available in a singleplayer world\"}");
                return;
            }
            Path filePath = dir.resolve(finalFile);
            try {
                Files.createDirectories(dir);
                Files.writeString(filePath, finalContent);
            } catch (IOException e) {
                sendJson(exchange, 500,
                        "{\"error\":" + GameStateCollector.jsonEscape("failed to write: " + e.getMessage()) + "}");
                return;
            }
            sendJson(exchange, 200, "{\"ok\":true}");
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * GET /listRecipes[?q=a,b,c][&verbose=1][&nap=N] — list recipes unlocked in the recipe book. Only works while an
     * {@link AbstractRecipeBookScreen} is open. Without {@code q}: recipes learned since the previous call (diff). With
     * {@code q} (comma-separated item ids or name substrings): every query is answered independently — {@code recipes} holds
     * the matches ({@code name}/{@code item}/{@code craftable}/{@code placeable}), {@code recipe_errors} the per-query failures
     * (one bad query never eats valid results).
     */
    static void handleListRecipes(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String queryParam = q.get("q");
        long nap = parseNap(q);
        if (nap < 0) {
            sendJson(exchange, 400,
                    "{\"error\":\"invalid 'nap' — integer client_tick in [0," + MAX_NAP_TICKS + "] expected\"}");
            return;
        }
        boolean verbose = parseVerbose(q);
        try {
            String precheck = onMainThread(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null)
                    return "not in world";
                if (!(McScreenHelper.getCurrentScreen(mc) instanceof AbstractRecipeBookScreen))
                    return "no recipe book screen open";
                return null;
            });
            if (precheck != null) {
                sendJson(exchange, 400, "{\"error\":" + GameStateCollector.jsonEscape(precheck) + "}");
                return;
            }

            CheckedSupplier<String> buildExtra = () -> {
                Minecraft mc = Minecraft.getInstance();
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
                // pre-size for ~80 chars/recipe + optional errors
                StringBuilder sb = new StringBuilder(recipes.size() * 80 + errors.size() * 60 + 16);
                sb.append("\"recipes\":").append(RecipeBookHelper.recipesJson(recipes));
                if (!errors.isEmpty()) {
                    sb.append(",\"recipe_errors\":[");
                    for (int i = 0; i < errors.size(); i++) {
                        if (i > 0)
                            sb.append(',');
                        sb.append(GameStateCollector.jsonEscape(errors.get(i)));
                    }
                    sb.append(']');
                }
                return sb.toString();
            };

            String result;
            if (nap == 0) {
                result = onMainThread(() -> {
                    String begun = StateTracker.begin(verbose);
                    String extra = buildExtra.get();
                    return mergeExtra(StateTracker.finish(begun), extra);
                });
            } else {
                result = runWithNap(exchange, nap, verbose, buildExtra);
                if (result == null)
                    return; // error already sent
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
