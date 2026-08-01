# fromCFG 方法（src/client/java/com/github/prohect/BindAliasKeyBinding.java）

## 语法

```java
public boolean fromCFG()
```

## 参数

_无。_

## 备注

指示此绑定是否从 CFG 文件加载的记录访问器。为 `true` 时，该绑定被跟踪以供 `unloadCFGBinds` 移除。运行时绑定（通过 `/bind` 或 `/bindByAliasName` 命令创建）使用便捷构造函数，将其默认为 `false`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGBindsAlias](../alias/builtinAlias/UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | 移除所有 `fromCFG = true` 的绑定 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
