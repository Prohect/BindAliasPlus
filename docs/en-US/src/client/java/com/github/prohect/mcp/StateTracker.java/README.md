# StateTracker

Tracks the last state snapshot and assembles the MCP response envelope. In a two-phase pattern (`begin` → `finish`), it snapshots game state before an operation, then drains message channels after.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `last` | `Map<String, String>` (static, private) | The previous state snapshot for diffing |
| `lastContainer` | `GameStateCollector.ContainerSnapshot` (static, private) | The previous container snapshot for slot-level diffing |
| `lastHotbarItems` | `Map<String, String>` (static, private) | The previous hotbar item map for per-slot diffing |
| `lastHotbarEmpty` | `String` (static, private) | The previous hotbar empty-range string |
| `baselineJoinTick` | `long` (static, private) | World-change detection tick |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [reset](reset.md) | `static synchronized void reset()` | Forgets the baseline (call on world join/disconnect) |
| [begin](begin.md) | `static synchronized String begin(boolean full)` | Snapshot game state, diff against previous, build envelope start |
| [finish](finish.md) | `static String finish(String begun)` | Drain channels into the envelope and close it |

## See Also

| Item | Description |
|------|-------------|
| [GameStateCollector](GameStateCollector.java/README.md) | Raw state snapshots and JSON formatting |
| [GameChannels](GameChannels.java/README.md) | Message channels drained in `finish` |
| [McpHttpServer](McpHttpServer.java/README.md) | The MCP server that calls `begin`/`finish` for every request |
