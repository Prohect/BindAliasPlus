# getPlayerYaw method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
private java.lang.Double getPlayerYaw()
```

## Parameters

| Name     | Type | Description |
| -------- | ---- | ----------- |
| _(none)_ |      |             |

## Remarks

Returns the player's current yaw angle (horizontal rotation) as a `Double`. Calls `player.getYRot()` and casts to `double`.

Returns `null` if the player is null.

**Side effects**: None (reads player state).

**Callers**: `getValueFromSource()` when source is `"yaw"`.

Return value: The yaw angle in degrees, or `null` if unavailable.

## See Also

| Item                                | Description                   |
| ----------------------------------- | ----------------------------- |
| [getPlayerPitch](getPlayerPitch.md) | Vertical rotation counterpart |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
