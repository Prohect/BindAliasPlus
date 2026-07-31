# BindAliasClient

## Fields

| Name            | Type                                      | Description                                                                                               |
| --------------- | ----------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `INSTANCE`      | `BindAliasClient`                     | Eager singleton instance. Used to access the class from mixins and built-in aliases.                      |
| `cfgPath`       | `Path`                                    | Path to `bind-alias.cfg` in the Minecraft config directory. Created on init if absent.               |
| `KEY_QUEUE`     | `ArrayDeque<KeyPressed>`                  | Thread-unsafe FIFO queue of pending key events, consumed by the tick handler.                             |
| `BINDING_PLUS`  | `Map<InputConstants.Key, KeyBindingPlus>` | Global key-binding map. Looked up on every key event to find which aliases to dispatch.                   |
| `LOGGER`        | `Logger`                                  | SLF4J logger for the client source set. Named `"bind-alias"`.                                        |
| `silentMode`    | `boolean`                                 | When `true`, suppresses chat feedback messages. Toggled by `SilentAlias`. Reset to `false` on disconnect. |
| `currentScreen` | `Screen`                                  | Cached current screen, updated by `GuiMixin` on every screen change. `null` when no screen is open.       |

## Methods

### Lifecycle

| Name                 | Signature                   | Description                                                                                                 |
| -------------------- | --------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `onInitializeClient` | `void onInitializeClient()` | Full client init: registers built-in aliases, commands, autoload hooks, and disconnect cleanup.             |
| `loadCFG`            | `void loadCFG()`            | Reads `bind-alias.cfg`, parses each line, and delegates to the corresponding `command*Execute` method. |

### Command Handlers (private helpers)

| Name                            | Signature                      | Description                                                                                 |
| ------------------------------- | ------------------------------ | ------------------------------------------------------------------------------------------- |
| `commandVarExecute`             | `int(String, String, boolean)` | Creates or updates a variable via `VarAlias`. Returns 1 on success, 0 on failure.           |
| `commandUnbindExecute`          | `int(String)`                  | Removes the binding for the given key name. Returns 1 on success, 0 if key unknown.         |
| `commandBindExecute`            | `int(String, String, boolean)` | Binds a key to an alias definition string (generates intermediate anonymous aliases).       |
| `commandAliasExecute`           | `int(String, String, boolean)` | Creates or replaces a user alias. Returns 1=created, 2=builtin conflict, 3=already builtin. |
| `commandBindByAliasNameExecute` | `int(String, String, boolean)` | Binds a key to an existing alias by name (resolves `+`/`-` prefix patterns).                |

### Utilities

| Name                                              | Signature                                                   | Description                                                                      |
| ------------------------------------------------- | ----------------------------------------------------------- | -------------------------------------------------------------------------------- |
| `getSuggestions4aliasDefinitionCompletableFuture` | `static CompletableFuture<Suggestions>(SuggestionsBuilder)` | Provides tab-completion suggestions for alias definitions and arg values.        |
| `parseKey`                                        | `InputConstants.Key(String)`                                | Converts a key name string (e.g., `"f"`, `"mouse1"`) to an `InputConstants.Key`. |

## See Also

| Item                                                                                          | Description                            |
| --------------------------------------------------------------------------------------------- | -------------------------------------- |
| [BindAlias](../../../../main/java/com/github/prohect/BindAlias.java/BindAlias.md) | Common-side mod entry point            |
| [Alias](../alias/Alias.java/Alias.md)                                                         | Core alias interface and registries    |
| [KeyBindingPlus](../KeyBindingPlus.java/KeyBindingPlus.md)                                    | Key-to-alias mapping record            |
| [KeyPressed](../KeyPressed.java/KeyPressed.md)                                                | Key event record stored in `KEY_QUEUE` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
