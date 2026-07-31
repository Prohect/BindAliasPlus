# UserAlias (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public final class UserAlias implements AliasWithoutArgs<UserAlias>
```

Represents a user-defined alias chain — a sequence of alias invocations created from the CFG file or the `alias` builtin command. This is the **primary executor** in the alias system: when any alias chain runs, it is a `UserAlias` that parses the chain, looks up each constituent alias by name in the global maps, and dispatches execution.

## Remarks

`UserAlias` is the only non-builtin class that implements `AliasWithoutArgs`. It is a **leaf** in the hierarchy — it cannot be inherited (`final`).

### Construction

Three constructors support different origins:

| Constructor | Purpose |
|-------------|---------|
| `UserAlias(String args)` | User-created at runtime (e.g. via `alias` command) |
| `UserAlias(String args, boolean fromAutoload)` | Loaded from CFG file at startup |
| `UserAlias(String args, boolean fromCFG, boolean predefined)` | Protected / predefined alias that cannot be overwritten |

The `args` string holds the raw definition — a space-separated chain of alias invocations like `"+attack slot\1 wait\5 -attack"`.

### Execution flow (`run`)

1. **Decode**: `decodeArgs2Alias(this.args)` parses the definition string into an `ArrayDeque<AliasRecord>`. Each `AliasRecord` holds an alias name and its args.
2. **Dispatch loop**: For each `AliasRecord` from the queue:
   - Look up the alias in global maps: `aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`.
   - **`null`**: Skip silently.
   - **`UserAlias`**: Delegate to `runInternal()` (recursive, with loop detection via call-chain tracking).
   - **`WaitAlias`**: Defer execution — collect remaining queue items into a continuation string and call `waitAlias.run(args, continuation)`. The method returns immediately after scheduling.
   - **Other builtin**: Execute via `alias.run(aliasRecord.args())`. If the alias is screen-blacklisted (`blackList4Screen`) and a screen is open, only release events (`args.equals("0")`) pass through.

### `runInternal`

Same dispatch logic as `run`, but maintains a `List<UserAlias>` call chain to detect infinite recursion. If a `UserAlias` is already in the call chain, it is skipped with a warning log. On encountering a `WaitAlias`, it also collects leftover items from parent aliases in the call chain (unwinding the stack) to preserve the full deferred continuation.

### CFG tracking

The `fromCFG` flag distinguishes aliases loaded from the config file. The `setFromCFG` setter and `isFromCFG` getter allow `unloadCFGAliases` to identify and remove only CFG-loaded aliases without touching user-created or builtin ones.

### Predefined protection

The `predefined` flag (set via the 3-arg constructor) marks an alias as protected — it cannot be overwritten by a new `alias` definition. The `isPredefined()` getter is used by `AliasAlias` to check before overwriting.

## See Also

| Item | Description |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | The interface UserAlias implements |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | The record type used in the internal alias queue |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | Deferred execution — UserAlias yields to it |
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | The `alias` builtin that creates/overwrites UserAliases |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | Where CFG-loaded UserAliases are registered |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
