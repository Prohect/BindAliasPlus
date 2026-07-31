# onInitializeClient method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public void onInitializeClient()
```

## Parameters

_None._

## Remarks

Called once by Fabric at client startup. This is the single initialization entry point for the entire mod. It performs the following in order:

1. **Register ~35 builtin aliases with arguments** — each via a builder chain (`new SomeAlias().putToAliasesWithArgs()`) for aliases like `slot`, `yaw`, `pitch`, `say`, `sendCommand`, `var`, `alias`, `bind`, `unbind`, `wait`, `swapSlot`, `setYaw`, `setPitch`, `log`, `lock`, `runAlias`, `applyRecipe`, etc. The `_notSuggested` variants omit the alias from command suggestions: `+attack`, `+use`, `+forward`, `+back`, `+left`, `+right`, `+jump`, `+sneak`, `+sprint`, `+drop`, `+screenshot`, `+playerList`, `+freeCursor`, `esc`, `+advancements`, `+debugOverlay`, `+silent`, `FPS/TPS/TPS2`, `+openInventory`, `+lockKey`/`-lockKey`, `reapply`.

2. **Register ~5 builtin aliases without arguments** — `cyclePerspective`, `swapHand`, `pickItem`, `toggleInventory`, `shutdown`, `reloadCFG`, and the various `unloadCFG*` / `unloadUser*` cleanup aliases.

3. **Register `+`/`-` switch wrappers** — creates `UserAlias` entries for each boolean action (e.g., `+attack` → `builtinAttack\1`, `-attack` → `builtinAttack\0`). These are what users actually invoke via key bindings.

4. **Create CFG file** — if `config/bindaliasplus.cfg` does not exist, creates an empty one.

5. **Register tick counter** — `ClientTickEvents.START_CLIENT_TICK` increments `currentTick`.

6. **Register join hook** — `ClientPlayConnectionEvents.JOIN` sets `joinTick`, initializes MCP channel state, resets trackers, registers `SoundCapture`, and calls `loadCFG()` to restore persistent config.

7. **Register disconnect hook** — clears all locks, drains `KEY_QUEUE`, resets `silentMode`.

8. **Register client commands** — `/alias`, `/bindByAliasName`, `/bind`, `/unbind`, `/reloadCFG`, `/unloadCFGAliases`, `/unloadCFGBinds`, `/unloadCFGVars`, `/unloadCFGAll`, `/unloadUserAliases`, `/unloadUserBinds`, `/unloadUserVars`, `/unloadUserAll`, `/var`, `/runAlias` — all with argument suggestions via `getSuggestions4aliasDefinitionCompletableFuture`.

9. **Start MCP HTTP server** — `McpHttpServer.start()`.

10. **Register shutdown hook** — `ClientLifecycleEvents.CLIENT_STOPPING` stops the MCP server.

## See Also

| Item | Description |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | Registration maps (`aliasesWithArgs`, `aliasesWithoutArgs`, etc.) populated here |
| [McpHttpServer](../mcp/McpHttpServer.java/McpHttpServer.md) | Started at the end of initialization |
| [loadCFG](loadCFG.md) | Called on world join to restore persistent config |
| [getSuggestions4aliasDefinitionCompletableFuture](getSuggestions4aliasDefinitionCompletableFuture.md) | Provides tab-completion for command arguments |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
