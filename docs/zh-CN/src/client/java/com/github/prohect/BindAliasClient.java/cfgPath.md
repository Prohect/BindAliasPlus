# cfgPath 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static final java.nio.file.Path cfgPath
```

## 备注

在类加载时通过 `FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".cfg")` 解析。通常指向 `config/bindaliasplus.cfg`。若不存在，则在 `onInitializeClient` 和 `loadCFG` 期间创建。`loadCFG` 逐行读取它，以恢复持久化的别名/绑定/变量定义。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
