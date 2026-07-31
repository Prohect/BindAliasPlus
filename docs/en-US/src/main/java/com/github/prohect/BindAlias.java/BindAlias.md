# BindAlias (src/main/java/com/github/prohect/BindAlias.java)

## Syntax

```java
public class BindAlias implements net.fabricmc.api.ModInitializer
```

## Static Initializer

_None._

## Remarks

The main mod initializer (common source set). Implements `ModInitializer`, which Fabric calls once during mod initialization. Sets the `MOD_ID` constant to `"bind-alias"` and creates the mod's SLF4J logger with that name. The `onInitialize()` method logs a startup message — the bulk of mod initialization (alias registration, key bindings, MCP server startup) happens in the client source set's `BindAliasClient.onInitializeClient()`.

## See Also

| Item | Description |
|------|-------------|
| [BindAliasClient](../../client/java/com/github/prohect/BindAliasClient.java/README.md) | The client-side initializer that registers all aliases, key bindings, and starts the MCP server |
| [MOD_ID](MOD_ID.md) | The mod's string ID |
| [LOGGER](LOGGER.md) | The mod's logger |
| [onInitialize](onInitialize.md) | The Fabric entry point |
