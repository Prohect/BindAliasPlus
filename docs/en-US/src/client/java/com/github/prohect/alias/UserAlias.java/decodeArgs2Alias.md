# decodeArgs2Alias method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
private void decodeArgs2Alias(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                          |
| ------ | -------- | -------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Raw definition string. Split by `Alias.divider4AliasDefinition`, each definition split by `Alias.divider4AliasArgs`. |

## Remarks

Parses the definition string into `AliasRecord` entries and pushes them to
`this.aliases`.

Algorithm:

1. Call `Alias.getDefinitions(args)` to split into individual definitions.
2. For each definition, call `Alias.getDefinitionSplits()` to separate the
   alias name from its arguments.
3. Count non-blank splits to determine the parsing mode:
   - **0 non-blank splits**: Skip (no valid definition).
   - **1 non-blank split**: The sole token is the alias name. Create an
     `AliasRecord` with empty args.
   - **2+ non-blank splits**: The first non-blank token is the alias name;
     subsequent non-blank tokens are joined with `Alias.divider4AliasArgs`
     as the arguments string.

This method is called by both `run()` and `runInternal()` before dispatch.
Because the `aliases` deque is not cleared before parsing, callers must
ensure it is empty before calling this method.

## See Also

| Item                                                              | Description                            |
| ----------------------------------------------------------------- | -------------------------------------- |
| [Alias.getDefinitions](../Alias.java/getDefinitions.md)           | First-stage split                      |
| [Alias.getDefinitionSplits](../Alias.java/getDefinitionSplits.md) | Second-stage split                     |
| [Alias.divider4AliasArgs](../Alias.java/divider4AliasArgs.md)     | Delimiter used to join argument tokens |
| [run](run.md)                                                     | Calls this method before dispatch      |
| [runInternal](runInternal.md)                                     | Calls this method before dispatch      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
