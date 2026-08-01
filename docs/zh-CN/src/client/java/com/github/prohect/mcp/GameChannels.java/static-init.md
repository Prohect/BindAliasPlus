# static-init（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 备注

在 `GameChannels` 类被加载时执行一次。按插入顺序用四个条目初始化 `CHANNELS` map：

1. `CHAT` — 不合并（`false`）
2. `MOD` — 不合并（`false`）
3. `SOUND` — 合并（`true`）
4. `RECIPE` — 不合并（`false`）

每个条目是一个新的 `Channel` 实例，带空 `entries` map、归零的 `cursor` 与归零的 `lastSent`。插入顺序由 `LinkedHashMap` 保持，并决定 `drain()` 的迭代顺序，从而保证 MCP envelope 中 JSON 输出顺序一致。
