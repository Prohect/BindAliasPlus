# appendTooltipIfValuable 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static void appendTooltipIfValuable(Minecraft mc, LocalPlayer p, ItemStack stack, StringBuilder sb)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `mc` | `Minecraft` | Minecraft 客户端实例 |
| `p` | `LocalPlayer` | 本地玩家 |
| `stack` | `ItemStack` | 要描述的物品堆叠 |
| `sb` | `StringBuilder` | 用于追加描述的构建器 |

## 备注

格式化物品堆叠描述，用于物品栏/快捷栏/容器的物品条目。追加堆叠的显示名称和数量。对于具有有价值属性的物品（附魔、自定义名称、lore、耐久警告），追加类似原版物品提示的额外提示细节。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [hotbarItems](hotbarItems.md) | 快捷栏物品的主要调用方 |
| [containerSnapshot](containerSnapshot.md) | 容器物品的调用方 |
