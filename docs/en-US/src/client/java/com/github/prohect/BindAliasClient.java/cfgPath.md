# cfgPath field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static final java.nio.file.Path cfgPath
```

## Remarks

Resolved via `FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".cfg")` at class-load time. Typically points to `config/bindaliasplus.cfg`. Created if absent during `onInitializeClient` and `loadCFG`. Read line-by-line in `loadCFG` to restore persistent alias/bind/var definitions.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
