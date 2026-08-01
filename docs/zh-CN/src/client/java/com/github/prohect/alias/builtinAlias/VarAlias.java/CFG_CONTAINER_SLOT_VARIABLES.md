# CFG_CONTAINER_SLOT_VARIABLES 字段（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

跟踪哪些容器槽位变量名称从配置文件加载的集合。

## 语法

```java
public static final java.util.Set<java.lang.String> CFG_CONTAINER_SLOT_VARIABLES
```

## 备注

**用途：** 跟踪 CFG 自动加载期间创建的容器槽位变量（`CONTAINER_SLOT_VARIABLES` 条目）的名称。`UnloadUserVarsAlias` 使用它来识别清理期间应**保留**哪些容器槽位变量。

**写入者：** `VarAlias.run(String, boolean)`——当 `fromAutoload` 为 true 且源是 `cN` 源时，将变量名称添加到此集合。

**读取者：** `UnloadUserVarsAlias.run()`——检查此集合以将 CFG 加载的容器槽位变量排除在移除之外。`UnloadUserAllAlias.run()` 也用于计数。

**注意：** 与 `CFG_VARIABLES` 不同，此集合**不**被 `UnloadCFGVarsAlias` 使用——该别名只清理 `GENERAL_VARIABLES` 和 `CFG_VARIABLES`，完全忽略容器槽位变量。这种不对称意味着 `UnloadUserVarsAlias` 是清理容器槽位变量的唯一途径。

**线程安全：** 仅从游戏线程访问。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
