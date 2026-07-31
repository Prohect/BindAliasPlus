# UserAlias

## Fields

| Name           | Type                      | Description                                                                                                                                     |
| -------------- | ------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| `aliases`      | `ArrayDeque<AliasRecord>` | Queue of parsed alias entries. Populated by `decodeArgs2Alias()`, consumed by `run()` and `runInternal()`. Package-private.                     |
| `args`         | `String`                  | The raw definition string passed at construction. Used by `decodeArgs2Alias()` and returned by `getDefinitionString()`. Package-private, final. |
| `fromAutoload` | `boolean`                 | Whether this alias was loaded from the auto-loaded config (`bind-alias.cfg`). `false` by default.                                          |

## Methods

| Name                  | Signature                           | Description                                                                                         |
| --------------------- | ----------------------------------- | --------------------------------------------------------------------------------------------------- |
| `UserAlias`           | `UserAlias(String)`                 | Constructor with definition string only. `fromAutoload` defaults to `false`.                        |
| `UserAlias`           | `UserAlias(String, boolean)`        | Constructor with definition string and autoload flag.                                               |
| `isFromAutoload`      | `boolean isFromAutoload()`          | Returns whether this alias was auto-loaded from config.                                             |
| `setFromAutoload`     | `void setFromAutoload(boolean)`     | Sets the autoload flag.                                                                             |
| `getDefinitionString` | `String getDefinitionString()`      | Returns the raw definition string.                                                                  |
| `decodeArgs2Alias`    | `void decodeArgs2Alias(String)`     | Parses the definition string into `AliasRecord` entries enqueued in `this.aliases`.                 |
| `run`                 | `UserAlias run(String)`             | Parses definitions and dispatches all entries except `WaitAlias`, which short-circuits.             |
| `runInternal`         | `void runInternal(List<UserAlias>)` | Recursive dispatch with infinite-loop detection. Also handles `WaitAlias` with root alias draining. |

## See Also

| Item                                                             | Description                                          |
| ---------------------------------------------------------------- | ---------------------------------------------------- |
| [AliasWithoutArgs](../AliasWithoutArgs.java/AliasWithoutArgs.md) | Interface implemented                                |
| [Alias](../Alias.java/Alias.md)                                  | Core interface with registries and parsing utilities |
| [AliasRecord](../AliasRecord.java/AliasRecord.md)                | Entries stored in the `aliases` deque                |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md)            | Special-cased during dispatch                        |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
