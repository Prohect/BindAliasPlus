# GameStateCollector

Utility class that assembles the raw game-state snapshot (player position, health, inventory, screen info, etc.) into JSON fragments for the MCP response envelope. Also provides container/hotbar slot-granularity diffing and shared formatting helpers.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `HELD_KEY_NAMES` | `Map<String, String>` (static, private) | Maps alias names (`"forward"`, `"attack"`, etc.) to human-readable key names for the `held_keys` member |

## Methods

**Snapshot collection:**

| Name | Signature | Description |
|------|-----------|-------------|
| [collect](collect.md) | `static LinkedHashMap<String, String> collect()` | Assembles the full game-state snapshot |
| [worldName](worldName.md) | `static String worldName(MinecraftClient mc)` | Extracts the dimension/world name |
| [posJson](posJson.md) | `static String posJson(ClientPlayerEntity p)` | Formats player position as JSON |
| [effectsJson](effectsJson.md) | `static String effectsJson(Collection<MobEffectInstance> effects)` | Formats active status effects as JSON |
| [targetJson](targetJson.md) | `static String targetJson(MinecraftClient mc, ClientPlayerEntity p)` | Formats the targeted entity as JSON |
| [playersJson](playersJson.md) | `static String playersJson(MinecraftClient mc, ClientPlayerEntity p)` | Formats nearby players with directional info |
| [heldKeysJson](heldKeysJson.md) | `static String heldKeysJson()` | Formats currently held movement/action keys |

**Hotbar:**

| Name | Signature | Description |
|------|-----------|-------------|
| [hotbarItems](hotbarItems.md) | `static Map<String, String> hotbarItems(ClientPlayerEntity p)` | Maps hotbar slot→item description (1-9) |
| [hotbarEmptyRanges](hotbarEmptyRanges.md) | `static String hotbarEmptyRanges(ClientPlayerEntity p)` | Compressed empty-slot ranges for hotbar |
| [hotbarFullJson](hotbarFullJson.md) | `static String hotbarFullJson(Map<String, String> items)` | Formats hotbar as full JSON |
| [hotbarDiffJson](hotbarDiffJson.md) | `static String hotbarDiffJson(Map<String, String> last, Map<String, String> cur)` | Formats hotbar as slot-level diff |

**Container:**

| Name | Signature | Description |
|------|-----------|-------------|
| [containerSnapshot](containerSnapshot.md) | `static ContainerSnapshot containerSnapshot(MinecraftClient mc, ClientPlayerEntity p)` | Extracts container menu state |
| [containerFullJson](containerFullJson.md) | `static String containerFullJson(ContainerSnapshot snap)` | Formats container as full JSON |
| [containerDiffJson](containerDiffJson.md) | `static String containerDiffJson(ContainerSnapshot last, ContainerSnapshot cur)` | Formats container as slot-level diff |
| [gridJson](gridJson.md) | `static String gridJson(List<int[]> grid)` | Formats crafting/recipe-book grid information |

**Formatting/shared helpers:**

| Name | Signature | Description |
|------|-----------|-------------|
| [jsonEscape](jsonEscape.md) | `static String jsonEscape(String s)` | Escapes a string for JSON; wraps in quotes |
| [fmt1](fmt1.md) | `static String fmt1(double v)` | Formats a double to 1 decimal place |
| [fmt2](fmt2.md) | `static String fmt2(double v)` | Formats a double to 2 decimal places |
| [formatDuration](formatDuration.md) | `static String formatDuration(int ticks)` | Formats tick duration as `MM:SS` |
| [compressRanges](compressRanges.md) | `static String compressRanges(List<Integer> indices)` | Compresses a list of indices into range strings |
| [buildGridRow](buildGridRow.md) | `static String buildGridRow(String[] cells, int width)` | Builds a formatted grid row string |
| [appendTooltipIfValuable](appendTooltipIfValuable.md) | `static void appendTooltipIfValuable(MinecraftClient mc, ClientPlayerEntity p, ItemStack stack, StringBuilder sb)` | Appends tooltip details for items with valuable enchants/lore |

## See Also

| Item | Description |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | The caller — diffs snapshots and builds the response envelope |
| [SoundCapture.directionOf](SoundCapture.java/directionOf.md) | Reused for `playersJson` directional formatting |
