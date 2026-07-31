# setFromAutoload method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public void setFromAutoload(boolean)
```

## Parameters

| Name           | Type      | Description                                                              |
| -------------- | --------- | ------------------------------------------------------------------------ |
| `fromAutoload` | `boolean` | `true` to mark this alias as auto-loaded from config; `false` otherwise. |

## Remarks

Sets whether this alias was loaded from the auto-loaded config file.

Called by `loadCFG()` when creating aliases from `bind-alias.cfg` to
distinguish them from aliases created by the user at runtime.

## See Also

| Item                                | Description     |
| ----------------------------------- | --------------- |
| [isFromAutoload](isFromAutoload.md) | Reads this flag |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
