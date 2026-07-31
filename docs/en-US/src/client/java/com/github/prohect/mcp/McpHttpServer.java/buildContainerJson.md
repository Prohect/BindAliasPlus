# buildContainerJson method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static String buildContainerJson(AbstractContainerMenu menu)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `menu` | `AbstractContainerMenu` | The open container menu to inspect. |

## Remarks

Builds a compressed, read-only JSON view of an open container menu for the `/state` endpoint. The output is designed to be parsed by an AI agent and supports all container types (chest, furnace, crafting table, anvil, etc.).

**Output structure**:

| Field | Description |
|-------|-------------|
| `menu` | Menu class name (e.g. `net.minecraft.world.inventory.ChestMenu`) |
| `items` | Array of occupied slots: `{index, item, count}`. Player inventory slots use 1–41 numbering; container slots use `"cN"` strings (1-based, matching `swapSlot` syntax). |
| `emptyInv` | Compressed string of empty player inventory slot ranges, e.g. `"1-9 10-36"`. Uses the same 1–41 numbering. |
| `grid` | _(present only for non-inventory slots)_ ASCII visual grid of container layout: `#` = empty, `$` = occupied, ` ` = no slot. Includes pixel coordinate range (`xy`) and per-cell c-indices (`cells`). |

**Size limit**: If the output exceeds `CONTAINER_JSON_MAX` (6000 chars), returns `{"menu":...,"error":"too large; use screenshot instead"}` instead of the full data.

**Grid encoding**: Non-inventory slots (chest grid, crafting grid, furnace, etc.) are mapped to a character grid using their `slot.x` and `slot.y` coordinates (which are pixel positions in the container GUI). The pixel range is normalized to row/column indices using a standard 18px cell pitch.

## See Also

| Item | Description |
|------|-------------|
| [handleState](handleState.md) | Calls this to include container data in the state response |
| [SwapSlotAlias](../../alias/builtinAlias/SwapSlotAlias.java/SwapSlotAlias.md) | Uses the same `cN` addressing convention |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAlias/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
