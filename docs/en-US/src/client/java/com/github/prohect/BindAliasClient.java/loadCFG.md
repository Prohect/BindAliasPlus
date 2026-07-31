# loadCFG method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public void loadCFG()
```

## Parameters

_None._

## Remarks

Reads the CFG file at `cfgPath` and dispatches each non-comment, non-blank line to the appropriate handler:

1. If the file does not exist, creates it and returns immediately (nothing to load).
2. Reads the entire file into a byte array via `Files.newInputStream`.
3. Converts bytes to `String` and iterates over lines.
4. For each line: strips a leading `/` (legacy comment notation), then:
   - `alias <name> <definition>` → `commandAliasExecute(name, definition, true)`
   - `bind <key> <definition>` → `commandBindExecute(key, definition, true)`
   - `bindByAliasName <key> <aliasName>` → `commandBindByAliasNameExecute(key, aliasName, true)`
   - `unbind <key>` → `commandUnbindExecute(key)`
   - `var <name> <source>` → `commandVarExecute(name, source, true)`
   - `runAlias <definition>` → runs the definition as a `UserAlias` chain
   - Unknown commands → logged as warnings

Each handler's `fromAutoload=true` variant is called so that CFG-loaded bindings/variables/aliases are tracked and can be cleaned up by `unloadCFG*` commands. Errors per line are caught and logged without aborting the rest of the file.

## See Also

| Item | Description |
|------|-------------|
| [cfgPath](cfgPath.md) | Path to the CFG file |
| [commandAliasExecute](commandAliasExecute.md) | Handles `alias` lines |
| [commandBindExecute](commandBindExecute.md) | Handles `bind` lines |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | Handles `bindByAliasName` lines |
| [commandUnbindExecute](commandUnbindExecute.md) | Handles `unbind` lines |
| [commandVarExecute](commandVarExecute.md) | Handles `var` lines |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
