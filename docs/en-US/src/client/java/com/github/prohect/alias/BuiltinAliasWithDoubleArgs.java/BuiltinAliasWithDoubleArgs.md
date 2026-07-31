# BuiltinAliasWithDoubleArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java)

## Syntax

```java
public abstract class BuiltinAliasWithDoubleArgs<T extends BuiltinAliasWithDoubleArgs<T>> extends BuiltinAliasWithArgs<T>
```

Abstract base class for builtin aliases whose single argument is a `double` floating-point value. Parses the args string into a `flag` field of type `double`. Used by `setYaw` and `setPitch` aliases.

## Remarks

`parseArgs(args)` resolves the argument in two steps:

1. **Variable resolution**: Calls `VarAlias.resolveDouble(args)` — if a user-defined variable with that name exists, its value is used.
2. **Literal parse**: If not a variable, attempts `Double.parseDouble(args)`. On failure, logs an error via `BindAliasClient.LOGGER`.

The resolved value is stored in `this.flag`. Concrete subclasses read `flag` in their `run()` method to apply the rotation.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Integer-arg counterpart (yaw, pitch) |
| [VarAlias.resolveDouble](builtinAlias/VarAlias.java/resolveDouble.md) | Variable-resolution step in parsing |
| [builtinAlias](builtinAlias/README.md) | setYaw, setPitch implementations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
