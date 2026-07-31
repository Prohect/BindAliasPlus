# MOD_ID field (src/main/java/com/github/prohect/BindAlias.java)

## Syntax

```java
public static final java.lang.String MOD_ID
```

## Remarks

The mod's namespace identifier: `"bind-alias"`. Used as:

- The SLF4J logger name in both `BindAlias` and `BindAliasClient`.
- The config file path segment (`FabricLoader.getConfigDir().resolve(MOD_ID + ".cfg")`).

Read-only constant. Set at class load time.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
