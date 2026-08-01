# CFG_VARIABLES 字段（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

跟踪哪些通用变量名称从配置文件加载的集合。

## 语法

```java
public static final java.util.Set<java.lang.String> CFG_VARIABLES
```

## 备注

**用途：** 跟踪 CFG 自动加载期间创建的通用变量（`GENERAL_VARIABLES` 条目）的名称。`UnloadCFGVarsAlias` 使用它识别要移除的变量，`UnloadUserVarsAlias` 使用它识别要**保留**的变量（不在此集合中的一切都视为"用户创建"并被移除）。

**写入者：** `VarAlias.run(String, boolean)`——当 `fromAutoload` 为 true 且源不是 `cN` 源时，将变量名称添加到此集合。

**读取者：**
- `UnloadCFGVarsAlias.run()`——遍历此集合以移除 CFG 加载的变量。
- `UnloadUserVarsAlias.run()`——检查此集合以将 CFG 加载的变量排除在移除之外。
- `UnloadCFGAllAlias.run()`——在卸载前读取 `size()` 用于计数。

**线程安全：** 仅从游戏线程访问。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
