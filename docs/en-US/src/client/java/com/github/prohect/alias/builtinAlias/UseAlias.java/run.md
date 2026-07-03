# run method (src/client/java/com/github/prohect/alias/builtinAlias/UseAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                              |
| ------ | -------- | -------------------------------------------------------- |
| `args` | `String` | `"1"` to press (use item/place block), `"0"` to release. |

## Remarks

Controls the vanilla use key via `keyUse.setDown(flag)` and increments `clickCount` when pressed.

**Algorithm**:

1. Parse `args` via switch: `"0"` sets flag to false, `"1"` sets flag to true, default logs warning.
2. Get `options.keyUse` from Minecraft.
3. Call `attackKey.setDown(flag)`.
4. If flag is true, increment `attackKey.clickCount++` (triggers one immediate use action).

**Side effects**: Modifies vanilla `KeyMapping` state, which triggers use/place behavior in the next game tick.

**Callers**: Invoked by the alias dispatch system.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
