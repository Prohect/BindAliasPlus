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
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
    /** Max {@code deferredTick} value — 1200 client ticks = 60 s at the nominal 20 client ticks per second. */
    private static final long MAX_SNAP_TICKS = 1200;
    /** Grace beyond a snap's expected wall time (ticks × 50 ms) before the request is failed as not-ticking. */
    private static final long SNAP_TIMEOUT_MARGIN_MS = 15_000;
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

    /** A single capture-point specification: tick offset + whether to include a screenshot. */
    private record SnapSpec(long deferredTick, boolean screenShot) {}

    /**
     * Parse the optional {@code snap} parameter — comma-separated {@code deferredTick:screenShot} pairs (e.g.
     * {@code snap=1:1,2:0,3:1}). The action runs immediately; state is captured at each offset. A {@code deferredTick} of 0
     * means capture immediately (no defer). The {@code screenShot} flag (0/1) controls whether a base64 PNG screenshot is
     * included in that envelope.
     *
     * @return sorted (by tick), unique specs; empty when absent. Sentinel {@code deferredTick = -1} on parse error.
     */
    private static List<SnapSpec> parseSnaps(Map<String, String> q) {
        String snapParam = q.get("snap");
        if (snapParam == null || snapParam.isBlank())
            return List.of();
        List<SnapSpec> specs = new ArrayList<>();
        for (String part : snapParam.split(",")) {
            String trimmed = part.trim();
            if (trimmed.isEmpty())
                continue;
            String[] pair = trimmed.split(":", 2);
            long tick;
            boolean ss = false;
            try {
                tick = Long.parseLong(pair[0].trim());
                if (tick < 0 || tick > MAX_SNAP_TICKS)
                    return List.of(new SnapSpec(-1, false)); // sentinel
                if (pair.length > 1) {
                    int flag = Integer.parseInt(pair[1].trim());
                    ss = flag == 1;
                }
            } catch (NumberFormatException e) {
                return List.of(new SnapSpec(-1, false)); // sentinel
            }
            // deduplicate by tick (first occurrence wins for screenShot)
            boolean dup = false;
            for (SnapSpec s : specs) {
                if (s.deferredTick == tick) {
                    dup = true;
                    break;
                }
            }
            if (!dup)
                specs.add(new SnapSpec(tick, ss));
        }
        specs.sort(Comparator.comparingLong(SnapSpec::deferredTick));
        return specs;
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

    /** Send a 500 error and return null (for use in return statements). */
    private static String sendError(HttpExchange exchange, String msg) throws IOException {
        sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(msg) + "}");
        return null;
    }

    @FunctionalInterface
    private interface CheckedSupplier<T> {
        T get() throws Exception;
    }

    // ---- snap (client_tick deferred captures) ----

    /** Pending snap tasks — ticked by {@code MinecraftClientMixin} on every client tick. */
    private static final List<SnapTask> SNAP_TASKS = new CopyOnWriteArrayList<>();

    private static final class SnapTask {
        /** Client ticks remaining (access under {@code synchronized (this)}). */
        long ticksLeft;
        /** Set by the HTTP thread when the request timed out — the capture must never happen then. */
        boolean cancelled;
        /** True when this snap engaged fast-forward — its removal must be balanced by {@link #fastForwardEnd()}. */
        boolean fastForward;
        /** Capture the full state snapshot instead of the diff when the snap expires. */
        boolean verbose;
        /** Whether to drain message channels when this snap expires — only the last snap in a multi-snap set drains. */
        boolean drainChannels = true;
        /** Base64 PNG screenshot to include in this envelope, or null. */
        String screenShotB64;
        /** Index into the shared results array (used by multi-snap). */
        int resultIndex;
        /** Shared multi-snap context, or null for single-snap (legacy path). */
        MultiSnapContext multiCtx;
        /** Future for single-snap responses (null when multiCtx is set). */
        final CompletableFuture<String> future;

        SnapTask(long ticksLeft, CompletableFuture<String> future) {
            this.ticksLeft = ticksLeft;
            this.future = future;
            this.resultIndex = -1;
        }

        SnapTask(long ticksLeft, MultiSnapContext multiCtx, int resultIndex) {
            this.ticksLeft = ticksLeft;
            this.future = null;
            this.multiCtx = multiCtx;
            this.resultIndex = resultIndex;
        }
    }

    /** Shared state for a multi-snap request: N tasks → N results → one aggregated response. */
    private static final class MultiSnapContext {
        final String[] results;
        final AtomicInteger remaining;
        final CompletableFuture<String[]> combinedFuture;
        volatile boolean cancelled;

        MultiSnapContext(int count, CompletableFuture<String[]> combinedFuture) {
            this.results = new String[count];
            this.remaining = new AtomicInteger(count);
            this.combinedFuture = combinedFuture;
        }
    }

    // ---- snap fast-forward (slow-world bench acceleration) ----

    /** Snaps at least this long fast-forward the integrated server for their duration. */
    private static final long SNAP_FF_MIN_TICKS = 10;
    /**
     * Fast-forward rate. 20 tps is the client tick cap ({@code Minecraft.getTickTargetMillis} = max(50 ms, msPerTick)), so
     * client and integrated server stay in lockstep; going higher would let the server outrun client ticks.
     */
    private static final float SNAP_FF_RATE = 20.0F;
    /** Guards {@link #ffActiveSnaps} and {@link #ffPreviousRate} (HTTP threads + client tick thread). */
    private static final Object FF_LOCK = new Object();
    private static int ffActiveSnaps;
    private static float ffPreviousRate;

    /**
     * Engage fast-forward for a snap about to be scheduled; the pre-snap rate is recorded on the first overlapping snap and
     * restored when the last one ends. Called on the main thread.
     *
     * @return true when fast-forward was engaged — the caller must then flag the SnapTask so its removal (expiry or cancel) is
     *         balanced by {@link #fastForwardEnd()}
     */
    private static boolean fastForwardBegin() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null)
            return false; // remote server — no tick-rate control
        final boolean accelerate;
        synchronized (FF_LOCK) {
            if (ffActiveSnaps == 0)
                ffPreviousRate = server.tickRateManager().tickrate();
            ffActiveSnaps++;
            accelerate = ffPreviousRate < SNAP_FF_RATE;
        }
        try {
            if (accelerate)
                server.execute(() -> server.tickRateManager().setTickRate(SNAP_FF_RATE));
        } catch (RuntimeException e) { // server stopping — roll back so the counter never gets stuck
            synchronized (FF_LOCK) {
                ffActiveSnaps--;
            }
            return false;
        }
        return true;
    }

    /** Balance a {@link #fastForwardBegin()} — restores the pre-snap rate once the last overlapping fast-forward snap ends. */
    private static void fastForwardEnd() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        synchronized (FF_LOCK) {
            if (--ffActiveSnaps > 0)
                return;
        }
        if (server == null)
            return;
        server.execute(() -> {
            TickRateManager trm = server.tickRateManager();
            if (trm.tickrate() == SNAP_FF_RATE)
                trm.setTickRate(ffPreviousRate);
        });
    }

    /**
     * Count down pending snap tasks; on expiry capture the envelope fresh. Called from {@code MinecraftClientMixin} after the
     * WaitAlias queue, so a {@code wait\N} task expiring on the same client_tick is already reflected.
     */
    public static void tickSnapTasks() {
        for (SnapTask task : SNAP_TASKS) {
            synchronized (task) {
                if (task.cancelled) {
                    SNAP_TASKS.remove(task);
                    if (task.fastForward)
                        fastForwardEnd();
                    continue;
                }
                if (--task.ticksLeft > 0)
                    continue;
                SNAP_TASKS.remove(task);
                if (task.fastForward)
                    fastForwardEnd();

                String begun = StateTracker.begin(task.verbose);
                String envelope = task.drainChannels ? StateTracker.finish(begun) : StateTracker.finishNoDrain(begun);
                if (task.screenShotB64 != null) {
                    envelope = mergeExtra(envelope, "\"screenShot\":" + GameStateCollector.jsonEscape(task.screenShotB64));
                }

                if (task.multiCtx != null) {
                    MultiSnapContext ctx = task.multiCtx;
                    synchronized (ctx) {
                        if (!ctx.cancelled) {
                            ctx.results[task.resultIndex] = envelope;
                            if (ctx.remaining.decrementAndGet() == 0)
                                ctx.combinedFuture.complete(ctx.results);
                        }
                    }
                } else if (task.future != null) {
                    task.future.complete(envelope);
                }
            }
        }
    }

    /**
     * Take a screenshot and return the base64-encoded PNG, or null on failure. Uses the mixin capture pipeline
     * ({@link ScreenshotCapture} + {@code NativeImageMixin}) for in-memory capture. Blocks the calling thread.
     */
    private static String captureScreenshotB64() {
        CompletableFuture<byte[]> future = new CompletableFuture<>();
        ScreenshotCapture.nextPngFuture = future;
        try {
            onMainThread(() -> {
                Minecraft mc = Minecraft.getInstance();
                net.minecraft.client.Screenshot.grab(mc.gameDirectory, null, mc.gameRenderer.mainRenderTarget(), 1, msg -> {
                });
                return null;
            });
            byte[] data = future.get(3, TimeUnit.SECONDS);
            if (data == null)
                return null;
            return Base64.getEncoder().encodeToString(data);
        } catch (Exception e) {
            ScreenshotCapture.nextPngFuture = null;
            return null;
        }
    }

    /**
     * Take screenshots upfront for each SnapSpec that has {@code screenShot=true}. Returns a parallel array of base64 strings
     * (null where no screenshot was requested). Blocks the calling thread.
     */
    private static String[] captureScreenshots(List<SnapSpec> specs) {
        String[] shots = new String[specs.size()];
        for (int i = 0; i < specs.size(); i++) {
            if (specs.get(i).screenShot)
                shots[i] = captureScreenshotB64();
        }
        return shots;
    }

    // ---- endpoint plumbing ----

    /**
     * Run an action and optionally capture state at deferred tick offsets with per-point screenshots.
     * <p>
     * {@code specs} are sorted by deferredTick. Entries with {@code deferredTick == 0} are handled immediately (alongside the
     * action); entries with {@code deferredTick > 0} use the snap-task mechanism.
     *
     * @param specs capture-point specs (may contain both immediate and deferred)
     * @param screenshots pre-captured base64 strings for each spec (null where no screenshot)
     * @param action the game-thread action to run once before captures
     * @param extra action-produced JSON members merged into the <em>last</em> envelope
     * @return single envelope object (one spec) or JSON array (multiple specs), or null on error
     */
    private static String runWithSnapSpecs(HttpExchange exchange, List<SnapSpec> specs, String[] screenshots, boolean verbose,
            CheckedSupplier<String> action) throws IOException {

        // Separate immediate (deferredTick==0) from deferred (deferredTick>0)
        List<Integer> immediateIdx = new ArrayList<>();
        List<Integer> deferredIdx = new ArrayList<>();
        for (int i = 0; i < specs.size(); i++) {
            if (specs.get(i).deferredTick == 0)
                immediateIdx.add(i);
            else
                deferredIdx.add(i);
        }

        int total = specs.size();
        String[] results = new String[total];

        // 1. Run action + capture immediate envelopes
        if (!immediateIdx.isEmpty() || deferredIdx.isEmpty()) {
            try {
                onMainThread(() -> {
                    String extraStr = action.get();
                    // When deferred snaps follow, immediate captures are intermediate: diff-only, no channel drain.
                    boolean immVerbose = deferredIdx.isEmpty() && verbose;
                    String begun = StateTracker.begin(immVerbose);
                    // For the immediate captures, reuse the same state snapshot
                    String immediateEnvelope =
                            deferredIdx.isEmpty() ? StateTracker.finish(begun) : StateTracker.finishNoDrain(begun);
                    // Apply extra to the last immediate envelope
                    String envelopeWithExtra = mergeExtra(immediateEnvelope, extraStr);
                    for (int idx : immediateIdx) {
                        String env =
                                (idx == immediateIdx.get(immediateIdx.size() - 1) && deferredIdx.isEmpty()) ? envelopeWithExtra
                                        : immediateEnvelope;
                        if (screenshots[idx] != null) {
                            env = mergeExtra(env, "\"screenShot\":" + GameStateCollector.jsonEscape(screenshots[idx]));
                        }
                        results[idx] = env;
                    }
                    return null;
                });
            } catch (Exception e) {
                return sendError(exchange, e.getMessage());
            }
        } else {
            // No immediate captures — just run the action
            try {
                onMainThread(() -> {
                    action.get();
                    return null;
                });
            } catch (Exception e) {
                return sendError(exchange, e.getMessage());
            }
        }

        if (deferredIdx.isEmpty()) {
            // Only immediate results
            return assembleResponse(results, total);
        }

        // 2. Create snap tasks for deferred entries
        List<SnapSpec> deferredSpecs = deferredIdx.stream().map(specs::get).toList();
        int dCount = deferredSpecs.size();
        boolean doFastForward = deferredSpecs.get(dCount - 1).deferredTick >= SNAP_FF_MIN_TICKS;

        if (dCount == 1) {
            // Single deferred snap
            CompletableFuture<String> future = new CompletableFuture<>();
            SnapTask task = new SnapTask(deferredSpecs.get(0).deferredTick, future);
            task.verbose = verbose;
            int srcIdx = deferredIdx.get(0);
            task.screenShotB64 = screenshots[srcIdx];

            try {
                onMainThread(() -> {
                    if (doFastForward)
                        task.fastForward = fastForwardBegin();
                    SNAP_TASKS.add(task);
                    return null;
                });
            } catch (Exception e) {
                return sendError(exchange, e.getMessage());
            }

            String envelope;
            try {
                long timeoutMs = deferredSpecs.get(0).deferredTick * 50 + SNAP_TIMEOUT_MARGIN_MS;
                envelope = future.get(timeoutMs, TimeUnit.MILLISECONDS);
            } catch (java.util.concurrent.TimeoutException e) {
                synchronized (task) {
                    task.cancelled = true;
                }
                envelope = future.getNow(null);
                if (envelope == null)
                    return sendError(exchange, "snap timed out — game not ticking?");
            } catch (Exception e) {
                synchronized (task) {
                    task.cancelled = true;
                }
                return sendError(exchange, e.getMessage());
            }
            results[deferredIdx.get(0)] = envelope;
        } else {
            // Multi deferred snap
            CompletableFuture<String[]> combinedFuture = new CompletableFuture<>();
            MultiSnapContext ctx = new MultiSnapContext(dCount, combinedFuture);
            SnapTask[] tasks = new SnapTask[dCount];

            for (int i = 0; i < dCount; i++) {
                tasks[i] = new SnapTask(deferredSpecs.get(i).deferredTick, ctx, i);
                // Only the last deferred snap gets verbose + drains channels; intermediate snaps are diff-only.
                tasks[i].verbose = (i == dCount - 1) && verbose;
                tasks[i].drainChannels = (i == dCount - 1);
                tasks[i].screenShotB64 = screenshots[deferredIdx.get(i)];
            }
            if (doFastForward)
                tasks[dCount - 1].fastForward = true;

            try {
                onMainThread(() -> {
                    if (doFastForward)
                        tasks[dCount - 1].fastForward = fastForwardBegin();
                    for (SnapTask t : tasks)
                        SNAP_TASKS.add(t);
                    return null;
                });
            } catch (Exception e) {
                return sendError(exchange, e.getMessage());
            }

            String[] deferredEnvelopes;
            try {
                long maxSnap = deferredSpecs.get(dCount - 1).deferredTick;
                long timeoutMs = maxSnap * 50 + SNAP_TIMEOUT_MARGIN_MS;
                deferredEnvelopes = combinedFuture.get(timeoutMs, TimeUnit.MILLISECONDS);
            } catch (java.util.concurrent.TimeoutException e) {
                synchronized (ctx) {
                    ctx.cancelled = true;
                }
                for (SnapTask t : tasks) {
                    synchronized (t) {
                        t.cancelled = true;
                    }
                }
                return sendError(exchange, "snap timed out — game not ticking?");
            } catch (Exception e) {
                synchronized (ctx) {
                    ctx.cancelled = true;
                }
                for (SnapTask t : tasks) {
                    synchronized (t) {
                        t.cancelled = true;
                    }
                }
                return sendError(exchange, e.getMessage());
            }

            for (int i = 0; i < dCount; i++)
                results[deferredIdx.get(i)] = deferredEnvelopes[i];
        }

        return assembleResponse(results, total);
    }

    /** Build the final JSON response: single object for one result, array for multiple. */
    private static String assembleResponse(String[] results, int total) {
        // Find non-null results
        List<String> nonNull = new ArrayList<>();
        for (String r : results) {
            if (r != null)
                nonNull.add(r);
        }
        if (nonNull.isEmpty())
            return "{}";
        if (nonNull.size() == 1)
            return nonNull.get(0);
        StringBuilder sb = new StringBuilder(2048 * nonNull.size());
        sb.append('[');
        for (int i = 0; i < nonNull.size(); i++) {
            if (i > 0)
                sb.append(',');
            sb.append(nonNull.get(i));
        }
        return sb.append(']').toString();
    }

    // ---- endpoints ----

    /**
     * POST /runAlias?def=…[&verbose=1][&snap=tick:flag,…] — execute a chain of aliases. {@code snap} is a comma-separated list
     * of {@code deferredTick:screenShot} pairs (e.g. {@code 1:1,2:0,3:1}). The chain runs immediately; state is captured at
     * each offset. {@code screenShot}=1 includes a base64 PNG.
     */
    static void handleRunAlias(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String def = q.get("def");

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

        List<SnapSpec> specs = parseSnaps(q);
        if (!specs.isEmpty() && specs.get(0).deferredTick == -1) {
            sendJson(exchange, 400, "{\"error\":\"invalid 'snap' — comma-separated deferredTick:screenShot in [0,"
                    + MAX_SNAP_TICKS + "] expected\"}");
            return;
        }
        boolean verbose = parseVerbose(q);

        final String definition = def;
        try {
            String[] screenshots = captureScreenshots(specs);
            String result = runWithSnapSpecs(exchange, specs, screenshots, verbose, () -> {
                new UserAlias(definition).run("");
                return "";
            });
            if (result == null)
                return; // error already sent
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /** POST /defineAlias?name=…&def=…[&verbose=1][&snap=tick:flag,…] */
    static void handleDefineAlias(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String name = q.get("name");
        String def = q.get("def");

        if (name == null || def == null) {
            sendJson(exchange, 400, "{\"error\":\"missing 'name' or 'def' parameter\"}");
            return;
        }

        List<SnapSpec> specs = parseSnaps(q);
        if (!specs.isEmpty() && specs.get(0).deferredTick == -1) {
            sendJson(exchange, 400, "{\"error\":\"invalid 'snap' — comma-separated deferredTick:screenShot in [0,"
                    + MAX_SNAP_TICKS + "] expected\"}");
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
            String[] screenshots = captureScreenshots(specs);
            String result = runWithSnapSpecs(exchange, specs, screenshots, verbose, () -> {
                Minecraft.getInstance().player.connection.sendCommand(command);
                return "";
            });
            if (result == null)
                return;
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /** GET /readCFG — return the per-save agent cfg file content (non-envelope). */
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

    /** POST /writeCFG — overwrite the per-save agent cfg file and reload it. */
    static void handleWriteCFG(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String content = q.get("content");

        if (content == null) {
            String body;
            try (InputStream is = exchange.getRequestBody()) {
                body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
            if (body != null)
                content = extractJsonStringField(body, "content");
        }

        if (content == null) {
            sendJson(exchange, 400, "{\"error\":\"missing 'content'\"}");
            return;
        }

        List<SnapSpec> specs = parseSnaps(q);
        if (!specs.isEmpty() && specs.get(0).deferredTick == -1) {
            sendJson(exchange, 400, "{\"error\":\"invalid 'snap' — comma-separated deferredTick:screenShot in [0,"
                    + MAX_SNAP_TICKS + "] expected\"}");
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
            String[] screenshots = captureScreenshots(specs);
            String result = runWithSnapSpecs(exchange, specs, screenshots, verbose, () -> {
                BindAliasClient.loadAgentCFG();
                return "";
            });
            if (result == null)
                return;
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /** GET /readNotes?file=NAME */
    static void handleReadNotes(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String file = q.get("file");
        if (file == null || file.isBlank()) {
            sendJson(exchange, 400, "{\"error\":\"missing 'file' parameter\"}");
            return;
        }
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
                content = Files.exists(filePath) ? Files.readString(filePath) : "";
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

    /** POST /writeNotes?file=NAME&content=TEXT */
    static void handleWriteNotes(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String file = q.get("file");
        String content = q.get("content");

        if (file == null || file.isBlank()) {
            sendJson(exchange, 400, "{\"error\":\"missing 'file' parameter\"}");
            return;
        }
        if (file.contains("..") || file.contains("/") || file.contains("\\")) {
            sendJson(exchange, 400, "{\"error\":\"invalid 'file' — must be a plain filename (no path separators or '..')\"}");
            return;
        }

        if (content == null) {
            String body;
            try (InputStream is = exchange.getRequestBody()) {
                body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
            if (body != null)
                content = extractJsonStringField(body, "content");
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

    /** GET /listRecipes[?q=a,b,c][&verbose=1][&snap=tick:flag,…] */
    static void handleListRecipes(HttpExchange exchange) throws IOException {
        Map<String, String> q = parseQuery(exchange.getRequestURI().getQuery());
        String queryParam = q.get("q");

        List<SnapSpec> specs = parseSnaps(q);
        if (!specs.isEmpty() && specs.get(0).deferredTick == -1) {
            sendJson(exchange, 400, "{\"error\":\"invalid 'snap' — comma-separated deferredTick:screenShot in [0,"
                    + MAX_SNAP_TICKS + "] expected\"}");
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

            String[] screenshots = captureScreenshots(specs);
            String result = runWithSnapSpecs(exchange, specs, screenshots, verbose, buildExtra);
            if (result == null)
                return;
            sendJson(exchange, 200, result);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":" + GameStateCollector.jsonEscape(e.getMessage()) + "}");
        }
    }

    /**
     * Extract a string field's value from a flat JSON object body, decoding escapes in a single left-to-right pass.
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
        return null;
    }
}
