# BindAliasClient

## Fields

| Name | Type | Description |
|------|------|-------------|
| [INSTANCE](INSTANCE.md) | `BindAliasClient` | Singleton instance — eagerly initialized |
| [cfgPath](cfgPath.md) | `Path` | Path to the CFG file (`config/bindaliasplus.cfg`) |
| [KEY_QUEUE](KEY_QUEUE.md) | `ArrayDeque<KeyPressed>` | FIFO queue of pending key events from mixins |
| [BINDING_PLUS](BINDING_PLUS.md) | `Map<InputConstants.Key, BindAliasKeyBinding>` | All active key→alias bindings |
| [LOGGER](LOGGER.md) | `Logger` | SLF4J logger named `"bind-alias"` |
| [currentTick](currentTick.md) | `long` | Monotonic tick counter, incremented every client tick |
| [joinTick](joinTick.md) | `long` | Tick when the player last joined a world (`-1` if never) |
| [silentMode](silentMode.md) | `boolean` | When true, suppresses mod feedback messages in chat |
| [currentScreen](currentScreen.md) | `Screen` | Cached current screen, updated every tick |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [onInitializeClient](onInitializeClient.md) | `void onInitializeClient()` | **Entry point** — registers all builtin aliases, commands, lifecycle hooks, and starts MCP server |
| [tickPrefix](tickPrefix.md) | `static String tickPrefix()` | Returns `[client_tick:N]` log prefix (ticks since join) |
| [loadCFG](loadCFG.md) | `void loadCFG()` | Reads and executes all lines from the CFG file |
| [commandVarExecute](commandVarExecute.md) | `int commandVarExecute(String, String)` | Handles `var\name\source` — creates/updates a mod variable |
| [commandUnbindExecute](commandUnbindExecute.md) | `int commandUnbindExecute(String)` | Handles `unbind\key` — removes a key binding |
| [commandBindExecute](commandBindExecute.md) | `int commandBindExecute(String, String)` | Handles `bind\key\definition` — creates inline alias and binds key |
| [commandAliasExecute](commandAliasExecute.md) | `int commandAliasExecute(String, String)` | Handles `alias\name_with_definition` — creates/redefines a user alias |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | `int commandBindByAliasNameExecute(String, String)` | Handles `bindByAliasName\key\aliasName` — binds key to existing alias |
| [getSuggestions4aliasDefinitionCompletableFuture](getSuggestions4aliasDefinitionCompletableFuture.md) | `static CompletableFuture<Suggestions> ...` | Provides tab-completion suggestions for alias definition arguments |
| [parseKey](parseKey.md) | `InputConstants.Key parseKey(String)` | Converts a key name string to Minecraft's `InputConstants.Key` |

## See Also

| Item | Description |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | The alias interface — registration maps populated in `onInitializeClient` |
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | User-defined alias runner invoked by command handlers |
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | Record stored in `BINDING_PLUS` by `commandBindExecute` |
| [KeyPressed](KeyPressed.md) | Record queued in `KEY_QUEUE` by mixins |
| [McpHttpServer](../mcp/McpHttpServer.java/McpHttpServer.md) | MCP HTTP server started in `onInitializeClient` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
