# run method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
T run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The arguments string for this alias invocation. For `AliasWithoutArgs`, this is always `""`. For `AliasWithArgs`, this contains the backslash-separated args (e.g. `"3"` for `slot\3`). |

## Return value

Returns `this` (the alias instance) to enable fluent chaining.

## Remarks

This is the **core contract** every alias must fulfill. When an alias is invoked from an alias chain, `UserAlias.run()` calls `alias.run(args)` with the args extracted from the `AliasRecord`.

**Implementation patterns by type**:

| Type | Typical `run()` behavior |
|------|--------------------------|
| `BuiltinAliasWithBooleanArgs` subclass | Calls `parseArgs("0"|"1")`, then injects into or releases from vanilla key mapping. May check `isUnderTextInputScreen()` first. |
| `BuiltinAliasWithIntegerArgs` subclass | Calls `parseArgs(args)`, sets `this.flag`, then acts (select slot, defer wait, rotate camera). |
| `BuiltinAliasWithDoubleArgs` subclass | Same as integer, but with `double`. |
| `BuiltinAliasWithStringArgs` subclass | Parses args inline (no base `parseArgs`; each subclass has its own logic). |
| `BuiltinAliasWithoutArgs` subclass | Ignores `args` — always `""` — and performs the action directly. |
| `UserAlias` | Ignores the `args` parameter — uses its own stored `this.args` definition string — and dispatches the chain. |
| `VarAlias` | Parses `name\source`, stores the resolved value in `GENERAL_VARIABLES` or `CONTAINER_SLOT_VARIABLES`. |

**Error handling**: Invalid args should be logged via `BindAliasClient.LOGGER` at `warn` or `error` level, then the method returns gracefully. Aliases must not throw exceptions on bad input — this would halt the alias chain.

## See Also

| Item | Description |
|------|-------------|
| [UserAlias.run](UserAlias.java/run.md) | The primary caller — parses chains and dispatches to individual `run()` |
| [parseArgs](BuiltinAliasWithBooleanArgs.java/parseArgs.md) | Argument parsing for boolean-arg aliases |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | All arg-accepting aliases implement this contract |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
