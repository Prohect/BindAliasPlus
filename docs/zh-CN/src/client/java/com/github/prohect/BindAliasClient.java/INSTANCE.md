# INSTANCE 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static final com.github.prohect.BindAliasClient INSTANCE
```

## 备注

`BindAliasClient` 的唯一的、急切实例化的实例。使用频率不高 —— 大多数访问直接走静态字段。被 `MinecraftClientMixin` 引用以分发按键事件，被 `McpHttpServer` 引用以触发 CFG 重载。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
