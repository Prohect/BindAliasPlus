# GameStateCollector

将原始游戏状态快照（玩家位置、生命值、物品栏、界面信息等）组装为 JSON 片段以用于 MCP 响应 envelope（状态信封）的工具类。还提供容器/快捷栏槽位粒度 diff 和共享格式化辅助方法。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `HELD_KEY_NAMES` | `Map<String, String>`（static, private） | 将别名名（`"forward"`、`"attack"` 等）映射到 `held_keys` 成员的人类可读按键名 |

## 方法

**快照收集：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [collect](collect.md) | `static LinkedHashMap<String, String> collect()` | 组装完整的游戏状态快照 |
| [worldName](worldName.md) | `static String worldName(MinecraftClient mc)` | 提取维度/世界名 |
| [posJson](posJson.md) | `static String posJson(ClientPlayerEntity p)` | 将玩家位置格式化为 JSON |
| [effectsJson](effectsJson.md) | `static String effectsJson(Collection<MobEffectInstance> effects)` | 将活动状态效果格式化为 JSON |
| [targetJson](targetJson.md) | `static String targetJson(MinecraftClient mc, ClientPlayerEntity p)` | 将注视的实体格式化为 JSON |
| [playersJson](playersJson.md) | `static String playersJson(MinecraftClient mc, ClientPlayerEntity p)` | 将附近玩家及其方向信息格式化为 JSON |
| [heldKeysJson](heldKeysJson.md) | `static String heldKeysJson()` | 将当前按住的移动/动作按键格式化 |

**快捷栏：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [hotbarItems](hotbarItems.md) | `static Map<String, String> hotbarItems(ClientPlayerEntity p)` | 快捷栏槽位→物品描述（1-9） |
| [hotbarEmptyRanges](hotbarEmptyRanges.md) | `static String hotbarEmptyRanges(ClientPlayerEntity p)` | 快捷栏压缩空槽位区间 |
| [hotbarFullJson](hotbarFullJson.md) | `static String hotbarFullJson(Map<String, String> items)` | 将快捷栏格式化为完整 JSON |
| [hotbarDiffJson](hotbarDiffJson.md) | `static String hotbarDiffJson(Map<String, String> last, Map<String, String> cur)` | 将快捷栏格式化为槽位级 diff |

**容器：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [containerSnapshot](containerSnapshot.md) | `static ContainerSnapshot containerSnapshot(MinecraftClient mc, ClientPlayerEntity p)` | 提取容器菜单状态 |
| [containerFullJson](containerFullJson.md) | `static String containerFullJson(ContainerSnapshot snap)` | 将容器格式化为完整 JSON |
| [containerDiffJson](containerDiffJson.md) | `static String containerDiffJson(ContainerSnapshot last, ContainerSnapshot cur)` | 将容器格式化为槽位级 diff |
| [gridJson](gridJson.md) | `static String gridJson(List<int[]> grid)` | 格式化合成/配方书网格信息 |

**格式化/共享辅助方法：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [jsonEscape](jsonEscape.md) | `static String jsonEscape(String s)` | 转义字符串以用于 JSON；用引号包裹 |
| [fmt1](fmt1.md) | `static String fmt1(double v)` | 将 double 格式化为 1 位小数 |
| [fmt2](fmt2.md) | `static String fmt2(double v)` | 将 double 格式化为 2 位小数 |
| [formatDuration](formatDuration.md) | `static String formatDuration(int ticks)` | 将 tick 时长格式化为 `MM:SS` |
| [compressRanges](compressRanges.md) | `static String compressRanges(List<Integer> indices)` | 将索引列表压缩为区间字符串 |
| [buildGridRow](buildGridRow.md) | `static String buildGridRow(String[] cells, int width)` | 构建格式化网格行字符串 |
| [appendTooltipIfValuable](appendTooltipIfValuable.md) | `static void appendTooltipIfValuable(MinecraftClient mc, ClientPlayerEntity p, ItemStack stack, StringBuilder sb)` | 为具有有价值附魔/lore 的物品追加提示详情 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | 调用方——对快照做 diff 并构建响应 envelope |
| [SoundCapture.directionOf](SoundCapture.java/directionOf.md) | `playersJson` 的方向格式化复用 |
