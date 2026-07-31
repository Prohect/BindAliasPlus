# run method (src/client/java/com/github/prohect/alias/builtinAlias/PickItemAlias.java)

Triggers the vanilla pick-block keybinding to pick the targeted block/entity.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.PickItemAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. If `isUnderTextInputScreen()` is true, return immediately (screen suppression).
2. If `mc.player` is null, return immediately.
3. Get the vanilla `keyPickItem` keybinding from `mc.options`.
4. Call `pickKey.setDown(true)` and `pickKey.clickCount++` to simulate a pick-block keypress.
5. The game processes the pick on the next polling cycle via `pickBlockOrEntity()`.

**Side effects:**
- In Creative mode: the block/entity is cloned to the selected hotbar slot.
- In Survival mode: a matching item is moved from the inventory to the selected slot.
- The `keyPickItem` keybinding state is modified (set down, click count incremented).

**Screen suppression:** Cancelled when a text-input screen (chat, sign, book, command block) is open.

## See Also

| Item | Description |
|------|-------------|
| [PickItemAlias](PickItemAlias.md) | Class overview |
| [SlotAlias](../SlotAlias.java/run.md) | Direct hotbar slot selection |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
