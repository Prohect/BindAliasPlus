# flag field (src/client/java/com/github/prohect/alias/BuiltinAliasWithIntegerArgs.java)

## Syntax

```java
public int flag
```

Stores the parsed `int` value from the args string. Read by concrete subclass `run()` methods to select a hotbar slot (`slot`), defer execution (`wait`), or rotate camera (`yaw`, `pitch`).

## Remarks

Set by `parseArgs(args)` via `VarAlias.resolveInt()` or `Integer.parseInt()`. Default value is `0`.

## See Also

| Item | Description |
|------|-------------|
| [parseArgs](parseArgs.md) | Sets this field from args string |
| [BuiltinAliasWithDoubleArgs.flag](BuiltinAliasWithDoubleArgs.java/flag.md) | Double counterpart |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
