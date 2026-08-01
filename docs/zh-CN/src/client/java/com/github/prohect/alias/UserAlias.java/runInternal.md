# runInternal 方法（src/client/java/com/github/prohect/alias/UserAlias.java）

## 语法

```java
public void runInternal(List<UserAlias> userAliasesCallChains)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `userAliasesCallChains` | `List<UserAlias>` | 累积的调用链——第一个元素必须是链的根 `UserAlias`。用于检测无限递归。 |

## 备注

当一个 `UserAlias` 嵌套在另一个 `UserAlias` 的链中时调用的递归执行变体。分派逻辑与 `run()` 相同，有两个关键区别：

1. **无限循环检测**：在执行嵌套的 `UserAlias` 之前，检查它是否已在 `userAliasesCallChains` 中。如果是，则记录警告（`"infinite loop detected checking UserAliasesCallChains"`）并跳过它。
2. **WaitAlias 链展开**：遇到 `WaitAlias` 时，它不仅收集此别名队列中的剩余条目，还会**展开整个调用链**——收集 `userAliasesCallChains` 中每个父别名的剩余条目（按相反顺序迭代，从最内层到根）。这确保延迟延续捕获完整的剩余链，而不仅仅是当前子链。

**WaitAlias 展开的工作原理**：将此别名的剩余队列条目收集到 `definitionLeft` 之后，方法按相反顺序迭代 `userAliasesCallChains`。对每个父别名，将其剩余的 `aliases` 队列排空到 `definitionLeft` 中，并用适当的分隔符重建链。这确保等待计时器触发时，原始链的其余部分（在所有嵌套层级）都能正确执行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [run](run.md) | 非递归入口点——对嵌套的 UserAlias 委托给此方法 |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | 引起上述链展开的别名 |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | 展开期间被排空的队列条目 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
