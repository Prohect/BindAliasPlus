# BindAlias (src/main/java/com/github/prohect/BindAlias.java)

## Syntax

```java
public class com.github.prohect.BindAlias implements net.fabricmc.api.ModInitializer
```

## Static Initializer

_None._

## Remarks

Mod entry point for the common (server-capable) side. Its `onInitialize()` runs as soon as
Minecraft is ready for mod loading, but the actual functionality of BindAlias is entirely
client-side and lives in [BindAliasClient](../../../../client/java/com/github/prohect/BindAliasClient.java/BindAliasClient.md).

This class exists primarily to define `MOD_ID` and the shared logger, which are referenced by
both client and data-generator sources.

## See Also

| Item                                                                                                                                   | Description                            |
| -------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------- |
| [ClientModInitializer](https://fabricmc.net/wiki/documentation:fabric_mod_initializer)                                                 | Client-side equivalent with real logic |
| [BindAliasClient](../../../../client/java/com/github/prohect/BindAliasClient.java/BindAliasClient.md)                      | Client-side mod initializer            |
| [BindAliasDataGenerator](../../../../client/java/com/github/prohect/BindAliasDataGenerator.java/BindAliasDataGenerator.md) | Data generation entry point            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
