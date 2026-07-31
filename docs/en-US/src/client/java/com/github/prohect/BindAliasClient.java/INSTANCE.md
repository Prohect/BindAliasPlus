# INSTANCE field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static final com.github.prohect.BindAliasClient INSTANCE
```

## Remarks

The single, eagerly-instantiated instance of `BindAliasClient`. Used infrequently — most access goes through static fields directly. Referenced by `MinecraftClientMixin` to dispatch key events, and by `McpHttpServer` to trigger CFG reloads.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
