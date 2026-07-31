# appendTooltipIfValuable method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
private static void appendTooltipIfValuable(MinecraftClient mc, ClientPlayerEntity p, ItemStack stack, StringBuilder sb)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `mc` | `Minecraft` | The Minecraft client instance |
| `p` | `ClientPlayerEntity` | The local player |
| `stack` | `ItemStack` | The item stack to describe |
| `sb` | `StringBuilder` | The builder to append the description to |

## Remarks

Formats an item stack description for use in inventory/hotbar/container item entries. Appends the stack's display name and count. For items with valuable properties (enchantments, custom name, lore, durability warnings), appends additional tooltip details similar to the vanilla item tooltip.

## See Also

| Item | Description |
|------|-------------|
| [hotbarItems](hotbarItems.md) | Primary caller for hotbar items |
| [containerSnapshot](containerSnapshot.md) | Caller for container items |
