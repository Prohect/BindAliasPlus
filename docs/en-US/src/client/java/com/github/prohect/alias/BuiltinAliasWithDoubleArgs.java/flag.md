# flag field (src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java)

## Syntax

```java
public double flag
```

Stores the parsed `double` value from the args string. Read by concrete subclass `run()` methods to apply the rotation (setYaw, setPitch).

## Remarks

Set by `parseArgs(args)` via `VarAlias.resolveDouble()` or `Double.parseDouble()`. Default value is `0.0`.

## See Also

| Item | Description |
|------|-------------|
| [parseArgs](parseArgs.md) | Sets this field from args string |
| [BuiltinAliasWithIntegerArgs.flag](BuiltinAliasWithIntegerArgs.java/flag.md) | Integer counterpart |
| [BuiltinAliasWithBooleanArgs.flag](BuiltinAliasWithBooleanArgs.java/flag.md) | Boolean counterpart |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
