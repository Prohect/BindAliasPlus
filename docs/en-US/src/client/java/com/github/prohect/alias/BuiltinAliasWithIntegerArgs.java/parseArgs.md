# parseArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithIntegerArgs.java)

## Syntax

```java
public void parseArgs(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | A numeric string (e.g. `"3"`) or a variable name resolvable by `VarAlias.resolveInt()` |

## Remarks

Sets the `flag` field via a two-step resolution:

1. **Variable lookup**: Calls `VarAlias.resolveInt(args)`. If a variable with that name exists and holds an `Integer` value, uses it. This enables patterns like `var\s\hotbarSlot` → `slot\s`.
2. **Literal parse**: Falls back to `Integer.parseInt(args)`. On `NumberFormatException`, logs an error via `BindAliasClient.LOGGER` and leaves `flag` at `0`.

## See Also

| Item | Description |
|------|-------------|
| [flag](flag.md) | The field set by this method |
| [VarAlias.resolveInt](builtinAlias/VarAlias.java/resolveInt.md) | Variable-resolution step |
| [BuiltinAliasWithDoubleArgs.parseArgs](BuiltinAliasWithDoubleArgs.java/parseArgs.md) | Double counterpart |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
