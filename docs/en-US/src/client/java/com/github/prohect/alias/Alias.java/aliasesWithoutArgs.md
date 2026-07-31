# aliasesWithoutArgs field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs
```

Global map of all aliases that take no arguments and are suggested to the user. Keys are alias names (e.g. `"esc"`, `"toggleInventory"`, `"swapHand"`). Populated by `BuiltinAliasWithoutArgs.putToAliasesWithoutArgs()` during client initialization, and by `AliasAlias` when the user defines a new `UserAlias`.

## Remarks

This is the **first** map checked during alias-chain execution in `UserAlias.run()`. If an alias is found here, its `run("")` is called (empty args for `AliasWithoutArgs`).

**Readers**: `UserAlias.run()`, `KeyBoardMixin` (key-event routing), the `bind` command (suggestion lookup).

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
