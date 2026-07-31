# BuiltinAliasWithIntegerArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithIntegerArgs.java)

## Syntax

```java
public abstract class BuiltinAliasWithIntegerArgs<T extends BuiltinAliasWithIntegerArgs<T>> extends BuiltinAliasWithArgs<T>
```

Abstract base class for builtin aliases whose single argument is an `int`. Parses the args string into a `flag` field of type `int`. Used by `slot`, `wait`, `yaw`, and `pitch` aliases.

## Remarks

`parseArgs(args)` resolves the argument in two steps:

1. **Variable resolution**: Calls `VarAlias.resolveInt(args)` — if a user-defined variable with that name exists, its value is used. This allows e.g. `var\s\hotbarSlot` then `slot\s` to switch to a variable-determined slot.
2. **Literal parse**: If not a variable, attempts `Integer.parseInt(args)`. On failure, logs an error via `BindAliasClient.LOGGER`.

The resolved value is stored in `this.flag`. Concrete subclasses read `flag` in their `run()` method to perform the alias action (select a slot, defer a wait, rotate camera).

**Note on the Javadoc comment in source** (`@param args 0->key up, or false, 1->key down, or true`): This comment is a copy from `BuiltinAliasWithBooleanArgs` and does not accurately describe integer-arg parsing. The actual parsing does not treat `"0"` / `"1"` specially; it parses any integer via `Integer.parseInt`.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithDoubleArgs](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Double-arg counterpart (setYaw, setPitch) |
| [VarAlias.resolveInt](builtinAlias/VarAlias.java/resolveInt.md) | Variable-resolution step in parsing |
| [builtinAlias](builtinAlias/README.md) | slot, wait, yaw, pitch implementations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
