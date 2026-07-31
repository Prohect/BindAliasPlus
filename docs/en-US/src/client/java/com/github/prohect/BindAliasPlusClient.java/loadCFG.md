# loadCFG method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public void loadCFG()
```

## Parameters

| Name | Type | Description |
| ---- | ---- | ----------- |

## Remarks

Reads and parses the config file at `cfgPath`. Called on world join
via `ClientPlayConnectionEvents.JOIN`.

Algorithm:

1. Call `cfgPath.toFile().createNewFile()` — if the file didn't exist
   and was just created, return immediately (empty file, nothing to load).
2. Read the entire file into a byte array via `Files.newInputStream(cfgPath)`.
3. Convert bytes to a `String` and iterate over each line.
4. For each non-blank, non-comment line (comments start with `#`):
   - Strip leading `/` if present (supports command-style prefix).
   - Dispatch to the appropriate handler based on the line prefix:
     - `alias ` → `commandAliasExecute(aliasName, definition, true)`
     - `bind ` → `commandBindExecute(key, args, true)`
     - `bindByAliasName ` → `commandBindByAliasNameExecute(key, aliasName, true)`
     - `unbind ` → `commandUnbindExecute(key)`
     - `var ` → `commandVarExecute(varName, source, true)`
     - `runAlias ` → `new RunAliasAlias().run(aliasName)`
   - Lines with unknown prefixes log a warning.
5. Each line is wrapped in a try-catch; parse errors log a warning and continue.

**Side effects**: Populates `BINDING_PLUS`, `Alias.aliasesWithoutArgs`,
`VarAlias.VARIABLES`, and `Alias.aliasesWithoutArgs_fromBindCommand` based on
file contents.

**Error handling**: Malformed lines are logged and skipped. File I/O errors are
logged; the method returns early if the file can't be opened.

## See Also

| Item                                                                                   | Description                       |
| -------------------------------------------------------------------------------------- | --------------------------------- |
| [onInitializeClient](onInitializeClient.md)                                            | Where the JOIN hook is registered |
| [commandBindExecute](commandBindExecute.md)                                            | Handles `bind` lines              |
| [commandAliasExecute](commandAliasExecute.md)                                          | Handles `alias` lines             |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md)                      | Handles `bindByAliasName` lines   |
| [commandVarExecute](commandVarExecute.md)                                              | Handles `var` lines               |
| [UnloadCFGAllAlias](../alias/builtinAlias/UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | Clears all autoloaded state       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
