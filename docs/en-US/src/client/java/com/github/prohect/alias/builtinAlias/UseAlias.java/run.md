# run method (src/client/java/com/github/prohect/alias/builtinAlias/UseAlias.java)

Manually parses "0"/"1" args and presses or releases the use/item key (right-click).

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UseAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to use item / interact, `"0"` to release. Other values log a warning. |

## Remarks

**Algorithm:**

1. Manually determine `flag` by switching on `args`:
   - `"0"` → flag = false
   - `"1"` → flag = true
   - default → log warning, flag stays false
2. If a text-input screen is open AND flag is true, return immediately (press suppressed).
3. Get the vanilla `keyUse` keybinding.
4. Call `key.setDown(flag)`.
5. If flag is true, increment `clickCount` for the initial-press tick.

**Return value:** `this` (fluent return).

**Side effects:** Uses the held item or interacts with the targeted block/entity. Holding triggers continuous use (eating, bow drawing, shield blocking).

**Screen suppression:** Press is suppressed on text-input screens. Additionally, `+use` is fully suppressed on ALL screens by the MCP's builtin guard for safety.

**Why manual parsing:** Unlike most BooleanArgs aliases, this one does NOT use `parseArgs()`. The args are manually checked for "0"/"1". Invalid args log a warning but leave the key state unchanged.

## See Also

| Item | Description |
|------|-------------|
| [UseAlias](UseAlias.md) | Class overview |
| [AttackAlias](../AttackAlias.java/run.md) | Left-click counterpart |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | Reapply after screen transitions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
