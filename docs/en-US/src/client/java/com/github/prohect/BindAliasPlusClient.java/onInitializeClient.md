# onInitializeClient method (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
public void onInitializeClient()
```

## Parameters

| Name | Type | Description |
| ---- | ---- | ----------- |

## Remarks

Full client initialization entry point. Called once by Fabric Loader.

Performs the following in order:

1. **Register built-in aliasesWithArgs** — Instantiates each built-in alias class
   (Attack, Use, Forward, Back, Left, Right, Jump, Sneak, Sprint, Drop, Log,
   Slot, SwapSlot, Wait, Yaw, Pitch, SetYaw, SetPitch, Alias, Bind, Unbind,
   Say, LocalSay, SendCommand, Silent, SetPerspective, Var, Lock, RunAlias,
   OpenInventory) and calls `putToAliasesWithArgs[_notSuggested]().addToScreenBlackList()`
   as appropriate.

2. **Register built-in aliasesWithoutArgs** — Instantiates built-in no-arg aliases
   (CyclePerspective, SwapHand, PickItem, Shutdown, ReloadCFG, UnloadCFGAliases,
   UnloadCFGBinds, UnloadCFGVars, UnloadCFGAll, LockAlias_OnLock,
   LockAlias_Unlock, ReapplyAlias) and convenience `UserAlias` wrappers for
   boolean actions (`+attack`, `-attack`, `+forward`, `-forward`, etc.).

3. **Create config file** — Ensures `cfgPath` exists (creates empty if absent).

4. **Register autoload hook** — `ClientPlayConnectionEvents.JOIN` calls `loadCFG()`.

5. **Register disconnect cleanup** — `ClientPlayConnectionEvents.DISCONNECT`
   clears locks, queue, and resets `silentMode`.

6. **Register client commands**:
   - `alias <keyName> <args>` — define/override a user alias.
   - `bindByAliasName <key> <aliasName>` — bind a key to an existing alias.
   - `bind <key> <args>` — bind a key to an alias definition (auto-generates intermediate aliases).
   - `unbind <key>` — remove a key binding.
   - `reloadCFG` — re-read config file.
   - `unloadCFGAliases` / `unloadCFGBinds` / `unloadCFGVars` / `unloadCFGAll` — clear autoloaded state.
   - `var <varName> <source>` — create/update a variable.
   - `runAlias <aliasName>` — manually run an alias.

All commands check `silentMode` before sending feedback messages.

## See Also

| Item                                                              | Description                                 |
| ----------------------------------------------------------------- | ------------------------------------------- |
| [loadCFG](loadCFG.md)                                             | Config file loading (called from JOIN hook) |
| [Alias](../alias/Alias.java/Alias.md)                             | Core alias registry populated here          |
| [commandBindExecute](commandBindExecute.md)                       | `/bind` command handler                     |
| [commandAliasExecute](commandAliasExecute.md)                     | `/alias` command handler                    |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | `/bindByAliasName` command handler          |
| [commandUnbindExecute](commandUnbindExecute.md)                   | `/unbind` command handler                   |
| [commandVarExecute](commandVarExecute.md)                         | `/var` command handler                      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
