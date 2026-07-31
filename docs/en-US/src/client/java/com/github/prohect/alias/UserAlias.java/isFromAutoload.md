# isFromAutoload method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public boolean isFromAutoload()
```

## Remarks

Returns whether this alias was loaded from the auto-loaded config file
(`bind-alias.cfg`).

This flag helps distinguish aliases created by the user via commands from
those loaded automatically when joining a world, which can affect behavior
such as whether the alias is persisted in the config.

## Return value

`true` if this alias originated from auto-load; `false` if created at runtime.

## See Also

| Item                                  | Description    |
| ------------------------------------- | -------------- |
| [setFromAutoload](setFromAutoload.md) | Sets this flag |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
