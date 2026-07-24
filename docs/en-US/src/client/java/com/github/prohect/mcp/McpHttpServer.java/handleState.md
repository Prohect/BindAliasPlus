# handleState method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleState(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange for `GET /state`. |

## Remarks

`GET /state` — returns a comprehensive JSON snapshot of the current game state. Built on the main thread via `onMainThread()`.

**Response fields**:

| Field | Type | Description |
|-------|------|-------------|
| `screen` | `string\|null` | Fully qualified class name of the open screen, or `null` |
| `dimension` | `string` | Current dimension (e.g. `minecraft:overworld`) |
| `worldName` | `string\|null` | Singleplayer world name or multiplayer server name |
| `x`, `y`, `z` | `number` | Player position |
| `yaw`, `pitch` | `number` | Player rotation |
| `health` | `number` | Current health |
| `maxHealth` | `number` | Maximum health |
| `heldItem` | `string\|null` | Registry key of held item (e.g. `minecraft:diamond_sword`) |
| `heldItemCount` | `number` | Stack size of held item |
| `hotbarSlot` | `number` | Selected hotbar slot (1-indexed, 1–9) |
| `container` | `object\|absent` | Present only when an `AbstractContainerScreen` is open. See `buildContainerJson`. |

On error, returns `{"error": "..."}` with status 500.

## See Also

| Item | Description |
|------|-------------|
| [buildContainerJson](buildContainerJson.md) | Builds the `container` sub-object |
| [onMainThread](onMainThread.md) | Thread bridge used to access game state |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
