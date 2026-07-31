# LOGGER field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static final org.slf4j.Logger LOGGER
```

## Remarks

SLF4J logger named `"bind-alias"`. Used throughout the entire mod (not just this class) for info-level lifecycle logging (`loadCFG`, server start/stop) and error/warn-level diagnostics. The `tickPrefix()` method provides a consistent `[client_tick:N]` prefix for log messages.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
