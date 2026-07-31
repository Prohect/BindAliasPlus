# RunAliasAlias (src/client/java/com/github/prohect/alias/builtinAlias/RunAliasAlias.java)

Builtin alias that executes a registered alias by name with optional extra arguments. Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.RunAliasAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.RunAliasAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinRunAlias` (internal, exposed as `builtinRunAlias`).

**Purpose:** Allows one alias to invoke another alias by name. The argument is the alias name, optionally followed by backslash-separated extra arguments to pass to the invoked alias.

**Usage pattern:**
- `builtinRunAlias\slot\3` — calls the `slot` alias with argument `3`
- `builtinRunAlias\say\hello` — calls the `say` alias with argument `hello`
- `builtinRunAlias\myAlias` — calls the user-defined alias `myAlias` with no extra args

**How it resolves:** Splits the args string at the first `\` (backslash): the part before is the alias name, the part after becomes extra args. Then searches all four alias registries in order:
1. `aliasesWithoutArgs`
2. `aliasesWithoutArgs_notSuggested`
3. `aliasesWithArgs`
4. `aliasesWithArgs_notSuggested`

If found, calls `alias.run(extraArgs)`. If not found, logs a warning.

**Comparison with UserAlias chain:** In a chain definition like `slot\3 say\hello`, each space-separated token is treated as a separate alias call automatically (handled by `UserAlias.run()`). `builtinRunAlias` is the explicit, programmatic way to invoke another alias from within an alias's `run()` method, or from MCP.

## See Also

| Item | Description |
|------|-------------|
| [AliasAlias](../AliasAlias.java/AliasAlias.md) | Define aliases at runtime |
| [UserAlias](../../UserAlias.java/UserAlias.md) | User-defined alias chain execution |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Base class for string-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
