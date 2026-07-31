# onInitialize method (src/main/java/com/github/prohect/BindAlias.java)

## Syntax

```java
@Override
public void onInitialize()
```

## Remarks

Fabric `ModInitializer` entry point. Called once during mod initialization after the game reaches a mod-load-ready state but before resources are fully loaded. Logs `"Hello Fabric world!"` via the mod's logger. The bulk of initialization — alias registration, key binding setup, CFG loading, and MCP server startup — happens in `BindAliasClient.onInitializeClient()` in the client source set.

## See Also

| Item | Description |
|------|-------------|
| [BindAliasClient.onInitializeClient](../../client/java/com/github/prohect/BindAliasClient.java/onInitializeClient.md) | The client-side initialization where aliases and key bindings are registered |
