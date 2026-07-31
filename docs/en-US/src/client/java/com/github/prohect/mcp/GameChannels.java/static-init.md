# static-init (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Remarks

Executed once when the `GameChannels` class is loaded. Initializes the `CHANNELS` map with four entries in insertion order:

1. `CHAT` — non-coalescing (`false`)
2. `MOD` — non-coalescing (`false`)
3. `SOUND` — coalescing (`true`)
4. `RECIPE` — non-coalescing (`false`)

Each entry is a new `Channel` instance with an empty `entries` map, a zeroed `cursor`, and a zeroed `lastSent`. The insertion order is preserved by `LinkedHashMap` and dictates the iteration order of `drain()`, ensuring consistent JSON output ordering in the MCP envelope.

