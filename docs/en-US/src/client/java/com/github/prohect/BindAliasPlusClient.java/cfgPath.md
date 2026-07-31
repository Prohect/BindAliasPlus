# cfgPath field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static final java.nio.file.Path cfgPath
```

## Remarks

Absolute path to the mod's config file: `<minecraft_config_dir>/bind-alias.cfg`.

Resolved via `FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".cfg")`
at class load time. The file is created (empty) in `onInitializeClient()` if it
doesn't yet exist. Read by `loadCFG()` on world join.

Written by the mod's config persistence mechanism (not shown in this file).

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
