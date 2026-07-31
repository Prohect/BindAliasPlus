# run method (src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java)

Closes the current screen or opens the pause menu.

## Syntax

```java
public EscAlias run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | `String` | `"0"` = close only, `"1"` = toggle (close screen or open pause) |

## Remarks

- If a screen is open (any screen), calls `onClose()` to close it. This works regardless of the arg value.
- If no screen is open AND flag is `1` AND the player is in a world, opens the pause menu.
- Uses `getCurrentScreen().onClose()` from the `Alias` interface for screen access.

## See Also

| Item | Description |
|------|-------------|
| [EscAlias](EscAlias.md) | Class documentation |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
