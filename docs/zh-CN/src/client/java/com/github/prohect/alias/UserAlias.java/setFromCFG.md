# setFromCFG 方法（src/client/java/com/github/prohect/alias/UserAlias.java）

## 语法

```java
public void setFromCFG(boolean fromAutoload)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `fromAutoload` | `boolean` | `true` 表示标记为 CFG 加载，`false` 表示标记为用户创建 |

## 备注

在构造后设置 `fromCFG` 标志。这允许 CFG 加载逻辑（`BindAliasClient.loadCFG()`）标记从配置文件加载的现有别名，将它们与交互式创建的别名区分开来。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isFromCFG](isFromCFG.md) | 此标志的获取方法 |
| [BindAliasClient.loadCFG](BindAliasClient.java/loadCFG.md) | 从 cfg 加载别名时使用此设置方法 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
