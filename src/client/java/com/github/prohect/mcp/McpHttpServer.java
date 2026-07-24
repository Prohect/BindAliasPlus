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
        if (server != null) return;
        try {
            server = HttpServer.create(new InetSocketAddress("127.0.0.1", PORT), 0);
            server.createContext("/state", McpHttpServer::handleState);
            server.createContext("/screenshot", McpHttpServer::handleScreenshot);
            server.createContext("/runAlias", McpHttpServer::handleRunAlias);
            server.createContext("/defineAlias", McpHttpServer::handleDefineAlias);
            server.createContext("/readCFG", McpHttpServer::handleReadCFG);
            server.createContext("/writeCFG", McpHttpServer::handleWriteCFG);
            server.setExecutor(Executors.newCachedThreadPool(r -> { Thread t = new Thread(r, "BindAliasPlus-MCP"); t.setDaemon(true); return t; }));
            server.start();
            Runtime.getRuntime().addShutdownHook(new Thread(McpHttpServer::stop, "BindAliasPlus-MCP-Shutdown"));
            BindAliasPlusClient.LOGGER.info("{}[MCP] HTTP server started on 127.0.0.1:{}", BindAliasPlusClient.tickPrefix(), PORT);
        } catch (Exception e) { BindAliasPlusClient.LOGGER.error("{}[MCP] Failed to start HTTP server", BindAliasPlusClient.tickPrefix(), e); }
    }

    public static void stop() {
        if (server != null) { server.stop(0); server = null; BindAliasPlusClient.LOGGER.info("{}[MCP] HTTP server stopped", BindAliasPlusClient.tickPrefix()); }
    }

    private static <T> T onMainThread(CheckedSupplier<T> task) throws Exception {
        CompletableFuture<T> f = new CompletableFuture<>();
        MinecraftClient.getInstance().execute(() -> { try { f.complete(task.get()); } catch (Exception e) { f.completeExceptionally(e); } });
        return f.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    private static Map<String,String> parseQuery(String q) {
        Map<String,String> m = new HashMap<>();
        if (q == null || q.isBlank()) return m;
        for (String p : q.split("&")) { int i = p.indexOf('='); if (i > 0) m.put(decode(p.substring(0,i)), decode(p.substring(i+1))); }
        return m;
    }

    private static String decode(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '%' && i+2 < s.length()) {
                try { int hi = Character.digit(s.charAt(i+1),16), lo = Character.digit(s.charAt(i+2),16); if (hi>=0&&lo>=0) { sb.append((char)((hi<<4)|lo)); i+=2; continue; } } catch (Exception ignored) {}
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static void sendJson(HttpExchange ex, int code, String json) throws IOException {
        byte[] b = json.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        ex.sendResponseHeaders(code, b.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(b); }
    }

    private static String j(String s) {
        if (s == null) return "null";
        StringBuilder sb = new StringBuilder("\"");
        for (char c : s.toCharArray()) {
            switch (c) { case '"': sb.append("\\\""); break; case '\\': sb.append("\\\\"); break; case '\n': sb.append("\\n"); break; case '\r': sb.append("\\r"); break; case '\t': sb.append("\\t"); break; default: sb.append(c); }
        }
        return sb.append('"').toString();
    }

    @FunctionalInterface private interface CheckedSupplier<T> { T get() throws Exception; }

    static void handleState(HttpExchange ex) throws IOException {
        try {
            String json = onMainThread(() -> {
                MinecraftClient mc = MinecraftClient.getInstance();
                StringBuilder sb = new StringBuilder("{");
                var sc = McScreenHelper.getCurrentScreen(mc);
                sb.append("\"screen\":").append(sc == null ? "null" : j(sc.getClass().getName()));
                ClientPlayerEntity p = mc.player;
                if (p != null) {
                    sb.append(",\"dimension\":").append(j(p.getWorld().getRegistryKey().getValue().toString()));
                    String worldName = null;
                    try { if (mc.getServer() != null) worldName = mc.getServer().getSaveProperties().getLevelName(); else if (mc.getCurrentServerEntry() != null) worldName = mc.getCurrentServerEntry().name; } catch (Exception ignored) {}
                    sb.append(",\"worldName\":").append(worldName == null ? "null" : j(worldName));
                    sb.append(",\"x\":").append(p.getX()); sb.append(",\"y\":").append(p.getY()); sb.append(",\"z\":").append(p.getZ());
                    sb.append(",\"yaw\":").append(p.getYaw()); sb.append(",\"pitch\":").append(p.getPitch());
                    sb.append(",\"health\":").append(p.getHealth()); sb.append(",\"maxHealth\":").append(p.getMaxHealth());
                    ItemStack held = p.getMainHandStack();
                    if (held != null && !held.isEmpty()) {
                        sb.append(",\"heldItem\":").append(j(Registries.ITEM.getKey(held.getItem()).map(k->k.getValue().toString()).orElse(held.getItem().toString())));
                        sb.append(",\"heldItemCount\":").append(held.getCount());
                    } else { sb.append(",\"heldItem\":null"); sb.append(",\"heldItemCount\":0"); }
                    sb.append(",\"hotbarSlot\":").append(p.getInventory().getSelectedSlot() + 1);
                    if (sc instanceof HandledScreen<?> cs) sb.append(",\"container\":").append(buildContainerJson(cs.getScreenHandler()));
                }
                sb.append('}');
                return sb.toString();
            });
            sendJson(ex, 200, json);
        } catch (Exception e) { sendJson(ex, 500, "{\"error\":" + j(e.getMessage()) + "}"); }
    }

    private static final int CONTAINER_JSON_MAX = 6000;

    private static String buildContainerJson(ScreenHandler menu) {
        StringBuilder out = new StringBuilder("{\"menu\":").append(j(menu.getClass().getName()));
        StringBuilder items = new StringBuilder();
        java.util.List<Integer> emptyInvSlots = new java.util.ArrayList<>();
        java.util.List<int[]> nonInv = new java.util.ArrayList<>();
        for (int i = 0; i < menu.slots.size(); i++) {
            Slot slot = menu.slots.get(i); int c = i + 1; ItemStack stack = slot.getStack();
            boolean playerInv = slot.inventory instanceof PlayerInventory;
            if (!stack.isEmpty()) {
                if (items.length() > 0) items.append(',');
                items.append("{\"index\":");
                if (playerInv) items.append(slot.getIndex() + 1); else items.append(j("c" + c));
                items.append(",\"item\":").append(j(Registries.ITEM.getKey(stack.getItem()).map(k->k.getValue().toString()).orElse(stack.getItem().toString())));
                items.append(",\"count\":").append(stack.getCount()); items.append('}');
            }
            if (playerInv) { if (stack.isEmpty()) emptyInvSlots.add(slot.getIndex() + 1); }
            else nonInv.add(new int[] { c, slot.x, slot.y, stack.isEmpty() ? 0 : 1 });
        }
        java.util.Collections.sort(emptyInvSlots);
        StringBuilder emptyInv = new StringBuilder();
        for (int i = 0; i < emptyInvSlots.size(); i++) {
            int start = emptyInvSlots.get(i), end = start;
            while (i+1 < emptyInvSlots.size() && emptyInvSlots.get(i+1) == end+1) end = emptyInvSlots.get(++i);
            if (emptyInv.length() > 0) emptyInv.append(' ');
            emptyInv.append(start); if (end > start) emptyInv.append('-').append(end);
        }
        if (!nonInv.isEmpty()) {
            int minX=Integer.MAX_VALUE,minY=Integer.MAX_VALUE,maxX=Integer.MIN_VALUE,maxY=Integer.MIN_VALUE;
            for (int[] s : nonInv) { minX=Math.min(minX,s[1]); minY=Math.min(minY,s[2]); maxX=Math.max(maxX,s[1]); maxY=Math.max(maxY,s[2]); }
            int cell=18, cols=(maxX-minX)/cell+1, rows=(maxY-minY)/cell+1;
            char[][] map = new char[rows][cols]; int[][] cells = new int[rows][cols];
            for (int r=0;r<rows;r++) { java.util.Arrays.fill(map[r],' '); java.util.Arrays.fill(cells[r],-1); }
            for (int[] s : nonInv) { int col=(s[1]-minX)/cell, row=(s[2]-minY)/cell; map[row][col]=s[3]==0?'#':'$'; cells[row][col]=s[0]; }
            out.append(",\"grid\":{\"xy\":").append(j("x"+minX+"-"+maxX+" y"+minY+"-"+maxY)).append(",\"map\":[");
            for (int r=0;r<rows;r++) { if(r>0)out.append(','); out.append(j(new String(map[r]))); }
            out.append("],\"cells\":[");
            for (int r=0;r<rows;r++) { if(r>0)out.append(','); StringBuilder row=new StringBuilder(); for(int col=0;col<cols;col++){if(col>0)row.append(',');if(cells[r][col]>=0)row.append(cells[r][col]);else row.append(' ');} out.append(j(row.toString())); }
            out.append("]}");
        }
        out.append(",\"items\":[").append(items).append(']');
        out.append(",\"emptyInv\":").append(j(emptyInv.toString())).append('}');
        if (out.length() > CONTAINER_JSON_MAX) return "{\"menu\":"+j(menu.getClass().getName())+",\"error\":\"too large; use screenshot instead\"}";
        return out.toString();
    }

    static synchronized void handleScreenshot(HttpExchange ex) throws IOException {
        try {
            if (!onMainThread(() -> MinecraftClient.getInstance().player != null)) { sendJson(ex,400,"{\"error\":\"not in game\"}"); return; }
            CompletableFuture<byte[]> f = new CompletableFuture<>();
            ScreenshotCapture.nextPngFuture = f;
            onMainThread(() -> { MinecraftClient mc = MinecraftClient.getInstance(); net.minecraft.client.util.ScreenshotRecorder.saveScreenshot(mc.runDirectory,mc.getFramebuffer(),msg->{}); return null; });
            byte[] data;
            try { data = f.get(3, TimeUnit.SECONDS); } catch (TimeoutException e) { sendJson(ex,500,"{\"error\":\"screenshot timed out\"}"); return; }
            catch (Exception e) { sendJson(ex,500,"{\"error\":\"screenshot failed: "+j(e.getMessage())+"\"}"); return; }
            if (data == null) { sendJson(ex,500,"{\"error\":\"screenshot encode failed\"}"); return; }
            String path = ScreenshotCapture.lastPath, name = ScreenshotCapture.lastName, b64 = Base64.getEncoder().encodeToString(data);
            sendJson(ex,200,"{\"path\":"+j(path)+",\"name\":"+j(name)+",\"base64\":"+j(b64)+"}");
        } catch (Exception e) { sendJson(ex,500,"{\"error\":"+j(e.getMessage())+"}"); }
    }

    static void handleRunAlias(HttpExchange ex) throws IOException {
        Map<String,String> q = parseQuery(ex.getRequestURI().getQuery());
        String def = q.get("def");
        if (def == null || def.isBlank()) { String n = q.get("name"), a = q.getOrDefault("args",""); if (n!=null&&!n.isBlank()) { def=n; if(!a.isEmpty()) def+=Alias.divider4AliasArgs+a; } }
        if (def == null || def.isBlank()) { sendJson(ex,400,"{\"error\":\"missing 'def' parameter\"}"); return; }
        final String d = def;
        try { sendJson(ex,200,onMainThread(() -> { new UserAlias(d).run(""); return "{\"ok\":true}"; })); }
        catch (Exception e) { sendJson(ex,500,"{\"error\":"+j(e.getMessage())+"}"); }
    }

    static void handleDefineAlias(HttpExchange ex) throws IOException {
        Map<String,String> q = parseQuery(ex.getRequestURI().getQuery());
        String name = q.get("name"), def = q.get("def");
        if (name == null || def == null) { sendJson(ex,400,"{\"error\":\"missing 'name' or 'def' parameter\"}"); return; }
        try {
            ChatCapture.begin();
            onMainThread(() -> { MinecraftClient mc = MinecraftClient.getInstance(); if(mc.player!=null) mc.player.networkHandler.sendChatCommand("alias "+name+" "+def); return null; });
            String fb = ChatCapture.end();
            if (fb == null || fb.isEmpty()) { sendJson(ex,500,"{\"error\":\"no feedback from command\"}"); return; }
            sendJson(ex,200,fb.startsWith("Alias ")? "{\"ok\":true,\"feedback\":"+j(fb)+"}" : "{\"error\":"+j(fb)+"}");
        } catch (Exception e) { ChatCapture.end(); sendJson(ex,500,"{\"error\":"+j(e.getMessage())+"}"); }
    }

    static void handleReadCFG(HttpExchange ex) throws IOException {
        try { sendJson(ex,200,"{\"content\":"+j(Files.readString(BindAliasPlusClient.cfgPath))+"}"); }
        catch (Exception e) { sendJson(ex,500,"{\"error\":"+j(e.getMessage())+"}"); }
    }

    static void handleWriteCFG(HttpExchange ex) throws IOException {
        if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) { sendJson(ex,405,"{\"error\":\"POST required\"}"); return; }
        try (InputStream is = ex.getRequestBody()) {
            Map<String,String> q = parseQuery(new String(is.readAllBytes(), StandardCharsets.UTF_8));
            String c = q.get("content"); if (c == null) { sendJson(ex,400,"{\"error\":\"missing 'content'\"}"); return; }
            onMainThread(() -> { Files.writeString(BindAliasPlusClient.cfgPath,c); BindAliasPlusClient.INSTANCE.loadCFG(); return null; });
            sendJson(ex,200,"{\"ok\":true}");
        } catch (Exception e) { sendJson(ex,500,"{\"error\":"+j(e.getMessage())+"}"); }
    }
}
