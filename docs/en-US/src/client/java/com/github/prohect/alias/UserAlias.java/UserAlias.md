# UserAlias (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public final class com.github.prohect.alias.UserAlias implements com.github.prohect.alias.AliasWithoutArgs<com.github.prohect.alias.UserAlias>
```

## Static Initializer

_None._

## Remarks

Represents a user-defined alias chain parsed from the config file or created by
the `/alias` command.

Each `UserAlias` stores a raw definition string (`args`) and, when executed,
parses it into a queue of `AliasRecord` entries via `decodeArgs2Alias()`. The
`run()` and `runInternal()` methods then dequeue each entry and look up the
corresponding alias implementation in the static registries
(`Alias.aliasesWithoutArgs`, `Alias.aliasesWithArgs`, etc.) in a fixed priority
order.

**Dispatch order**: suggested without-args → not-suggested without-args →
not-suggested with-args → suggested with-args.

**Special cases during dispatch**:

- **`WaitAlias`**: When encountered, remaining queue entries are packaged into
  a deferred definition string and passed to `WaitAlias.run()`. In `run()`, this
  causes an immediate return. In `runInternal()`, the root alias's queue is also
  drained into the deferred definition.
- **`UserAlias` (nested)**: In `run()`, delegates to `runInternal()` with a
  call-chain list starting from `this`. In `runInternal()`, checks for infinite
  loops by verifying the nested alias is not already in the call chain.
- **Screen blacklist**: Aliases that are `instanceof BuiltinAliasWithArgs` and
  in `Alias.blackList4Screen` are suppressed when a screen is open, except for
  key-up events (`"0"`).

**Lifecycle**: Instances are created by `loadCFG()` during mod initialization
and by the `/alias` command handler. Not thread-safe.

## See Also

| Item                                                             | Description                                          |
| ---------------------------------------------------------------- | ---------------------------------------------------- |
| [AliasWithoutArgs](../AliasWithoutArgs.java/AliasWithoutArgs.md) | Interface implemented                                |
| [Alias](../Alias.java/Alias.md)                                  | Core interface with registries and parsing utilities |
| [AliasRecord](../AliasRecord.java/AliasRecord.md)                | Record type stored in the alias queue                |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md)            | Special-cased during dispatch                        |
| [run](run.md)                                                    | Primary execution method                             |
| [runInternal](runInternal.md)                                    | Recursive dispatch with loop detection               |
| [decodeArgs2Alias](decodeArgs2Alias.md)                          | Definition string parser                             |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
