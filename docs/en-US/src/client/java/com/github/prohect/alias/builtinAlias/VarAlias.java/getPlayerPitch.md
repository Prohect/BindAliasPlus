# getPlayerPitch method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
private java.lang.Double getPlayerPitch()
```

## Parameters

| Name     | Type | Description |
| -------- | ---- | ----------- |
| _(none)_ |      |             |

## Remarks

Returns the player's current pitch angle (vertical rotation) as a `Double`. Calls `player.getXRot()` and casts to `double`.

Returns `null` if the player is null.

**Side effects**: None (reads player state).

**Callers**: `getValueFromSource()` when source is `"pitch"`.

Return value: The pitch angle in degrees, or `null` if unavailable.

## See Also

| Item                            | Description                     |
| ------------------------------- | ------------------------------- |
| [getPlayerYaw](getPlayerYaw.md) | Horizontal rotation counterpart |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
