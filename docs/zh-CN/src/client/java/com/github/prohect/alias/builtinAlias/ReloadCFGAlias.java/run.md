# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ReloadCFGAlias.java）

通过调用 `BindAliasClient.INSTANCE.loadCFG()` 触发配置文件重新加载。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ReloadCFGAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 调用 `BindAliasClient.INSTANCE.loadCFG()`——重新读取配置文件并处理其中定义的所有别名、按键绑定和变量。

**副作用：**
- 注册 CFG 中的新别名、按键绑定和变量。同名的现有条目会被覆盖。
- 已加载到内存中但 CFG 中已不再存在的 CFG 定义条目**不会**被移除——如果需要完全重置，请在重新加载前使用 `unloadCFGAll`。

**返回值：** `this`（流畅式返回）。

**无界面抑制：** 在任何界面上都能工作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ReloadCFGAlias](ReloadCFGAlias.md) | 类概览 |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/run.md) | 完全卸载 CFG（在重新加载前使用） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
