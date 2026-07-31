# UserAlias

User-defined alias chain — the primary executor in the alias system. Parses a space-separated definition string into individual alias invocations and dispatches them via the global registration maps.

Implements `AliasWithoutArgs<UserAlias>` directly (does **not** extend `BuiltinAliasWithoutArgs`).

## Fields

| Name | Type | Description |
|------|------|-------------|
| `aliases` | `ArrayDeque<AliasRecord>` | Internal queue of parsed alias invocations (populated by `decodeArgs2Alias`) |
| `args` | `String` | The raw definition string (immutable after construction) |
| `fromCFG` | `boolean` | Whether this alias was loaded from CFG file |
| `predefined` | `boolean` | Whether this alias is protected from overwriting |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [decodeArgs2Alias](decodeArgs2Alias.md) | `private void decodeArgs2Alias(String)` | Parse raw definition string into `AliasRecord` queue |
| [getDefinitionString](getDefinitionString.md) | `String getDefinitionString()` | Return the raw definition string |
| [isFromCFG](isFromCFG.md) | `boolean isFromCFG()` | CFG-loaded flag getter |
| [setFromCFG](setFromCFG.md) | `void setFromCFG(boolean)` | CFG-loaded flag setter |
| [isPredefined](isPredefined.md) | `boolean isPredefined()` | Protection flag getter |
| [run](run.md) | `UserAlias run(String)` | Execute the alias chain (entry point) |
| [runInternal](runInternal.md) | `void runInternal(List<UserAlias>)` | Recursive execution with loop detection and chain unwinding |

## See Also

| Item | Description |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | The interface UserAlias implements |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | The record type used in the internal queue |
| [Alias](Alias.java/Alias.md) | Root interface — declares the maps and parsing utilities |
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | The builtin that creates/overwrites UserAliases |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | Where CFG-loaded UserAliases are registered |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
