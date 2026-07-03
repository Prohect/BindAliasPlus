# BindAliasPlus (src/main/java/com/github/prohect/BindAliasPlus.java)

## Syntax

```java
public class com.github.prohect.BindAliasPlus implements net.fabricmc.api.ModInitializer
```

## Static Initializer

_None._

## Remarks

Mod entry point for the common (server-capable) side. Its `onInitialize()` runs as soon as
Minecraft is ready for mod loading, but the actual functionality of BindAliasPlus is entirely
client-side and lives in [BindAliasPlusClient](../../../../client/java/com/github/prohect/BindAliasPlusClient.java/BindAliasPlusClient.md).

This class exists primarily to define `MOD_ID` and the shared logger, which are referenced by
both client and data-generator sources.

## See Also

| Item                                                                                                                                   | Description                            |
| -------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------- |
| [ClientModInitializer](https://fabricmc.net/wiki/documentation:fabric_mod_initializer)                                                 | Client-side equivalent with real logic |
| [BindAliasPlusClient](../../../../client/java/com/github/prohect/BindAliasPlusClient.java/BindAliasPlusClient.md)                      | Client-side mod initializer            |
| [BindAliasPlusDataGenerator](../../../../client/java/com/github/prohect/BindAliasPlusDataGenerator.java/BindAliasPlusDataGenerator.md) | Data generation entry point            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
