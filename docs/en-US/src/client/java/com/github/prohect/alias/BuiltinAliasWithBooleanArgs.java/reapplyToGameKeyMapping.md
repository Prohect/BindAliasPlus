# reapplyToGameKeyMapping method (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public void reapplyToGameKeyMapping()
```

## Remarks

Re-applies the key-down state to the game's key mapping if `flag` is `true`.

When a text-input screen intercepts a key-press event, the game may lose track of
the key being held down. This method calls `run("1")` to re-assert the key-down
state, restoring the game's key mapping to match the alias state.

If `flag` is `false`, does nothing.

## See Also

| Item                      | Description                      |
| ------------------------- | -------------------------------- |
| [flag](flag.md)           | The field checked by this method |
| [parseArgs](parseArgs.md) | Sets the `flag` field            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
