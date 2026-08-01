# LOGGER 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static final org.slf4j.Logger LOGGER
```

## 备注

名为 `"bind-alias"` 的 SLF4J 日志器。整个模组（不仅限于此类）都用它进行 info 级别的生命周期日志（`loadCFG`、服务器启动/停止）以及 error/warn 级别的诊断。`tickPrefix()` 方法为日志消息提供一致的 `[client_tick:N]` 前缀。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
