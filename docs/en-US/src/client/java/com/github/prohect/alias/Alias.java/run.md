# run method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public abstract T run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                                                                    |
| ------ | -------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Arguments passed to the alias. For user aliases this is the raw trigger args; for built-in aliases it is the parsed argument string from `AliasRecord.args()`. |

## Remarks

Executes the alias with the given arguments.

This is the primary execution entry point. Each implementation defines its own
behavior:

- **UserAlias**: Parses its definition string into `AliasRecord` entries and dispatches
  them to registered aliases.
- **Built-in aliases**: Perform their specific game action (attack, use, switch slot, etc.).

The `args` parameter for user aliases comes from the key-binding trigger system.
For built-in aliases dispatched by `UserAlias.run()`, the args come from the
`AliasRecord.args()` field parsed during `decodeArgs2Alias()`.

## See Also

| Item                                                      | Description                                      |
| --------------------------------------------------------- | ------------------------------------------------ |
| [UserAlias.run](../UserAlias.java/run.md)                 | Concrete implementation for user-defined aliases |
| [decodeArgs2Alias](../UserAlias.java/decodeArgs2Alias.md) | Parses definition string before dispatch         |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
