# run method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public UserAlias run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | **Ignored** — `UserAlias` uses its own stored `this.args` definition string instead |

## Return value

Returns `this` (the `UserAlias` instance).

## Remarks

The primary entry point for alias-chain execution. Called by external triggers (MCP server, key bindings) to execute a user-defined alias chain.

**Execution flow**:

1. **Decode**: Calls `decodeArgs2Alias(this.args)` to parse the stored definition string into an `ArrayDeque<AliasRecord>`.
2. **Dispatch loop**: For each `AliasRecord` in the queue:
   - **Look up** the alias in global maps in order: `aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`.
   - **`null`**: Skip silently (misspelled or missing alias).
   - **`UserAlias`**: Delegate to `runInternal()` with this alias as the root of the call chain.
   - **`WaitAlias`**: Collect remaining queue items into a continuation string, call `waitAlias.run(args, continuation)`, then **return immediately** (deferring further execution).
   - **Other builtin**: Execute. If the alias is screen-blacklisted (`blackList4Screen`) and a screen is open, only release events (`"0"` args) execute.

**Screen blacklist logic**: When an alias is in `blackList4Screen`:
- `!isUnderAnyScreen()` → execute normally
- Screen is open + `args == "0"` (release) → execute (so keys don't get stuck)
- Screen is open + `args != "0"` → suppressed

**Important**: This method ignores its `args` parameter entirely. The definition string comes from the alias's own `this.args` field, set at construction. The parameter exists only to satisfy the `AliasWithoutArgs` interface contract.

## See Also

| Item | Description |
|------|-------------|
| [runInternal](runInternal.md) | The recursive variant with loop detection |
| [decodeArgs2Alias](decodeArgs2Alias.md) | The parsing step |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | The record type used in the dispatch queue |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | Deferred execution target |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
