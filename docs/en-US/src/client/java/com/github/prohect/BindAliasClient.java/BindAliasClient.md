# BindAliasClient (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public class com.github.prohect.BindAliasClient implements net.fabricmc.api.ClientModInitializer
```

## Static Initializer

_None._

## Remarks

`BindAliasClient` is **the** central class of the mod. It is a singleton (`INSTANCE`) that implements `ClientModInitializer` so Fabric calls `onInitializeClient()` once at client startup. Every alias, every key binding, every chat command, and every lifecycle hook originates here.

Lifecycle:
1. **Static init** — fields are initialized eagerly (queue, maps, logger, paths).
2. **`onInitializeClient()`** — registers ~60 builtin aliases, 7 client commands, tick counter, CFG autoload-on-join, disconnect cleanup, and starts the MCP HTTP server.
3. **Tick loop** — `ClientTickEvents.START_CLIENT_TICK` increments `currentTick`. The `KEY_QUEUE` is consumed by MinecraftClientMixin each tick to dispatch key events to the right aliases.
4. **World join** — `loadCFG()` is called, MCP channels are initialized.
5. **Disconnect** — locks are cleared, queue drained, silent mode reset.
6. **Shutdown** — `ClientLifecycleEvents.CLIENT_STOPPING` stops the MCP server.

The class holds all global mod state (`KEY_QUEUE`, `BINDING_PLUS`, `currentScreen`, `silentMode`, tick counters) as public static fields so mixins and aliases can access them without injection.

## See Also

| Item | Description |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | Alias interface — registration maps that `onInitializeClient` populates |
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | User-defined alias runner — invoked when key bindings and commands execute alias chains |
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | Key→alias mapping stored in `BINDING_PLUS` |
| [KeyPressed](KeyPressed.md) | Events queued in `KEY_QUEUE` |
| [McpHttpServer](../mcp/McpHttpServer.java/McpHttpServer.md) | MCP server started in `onInitializeClient` |
| [MinecraftClientMixin](../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | Tick driver that consumes `KEY_QUEUE` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

