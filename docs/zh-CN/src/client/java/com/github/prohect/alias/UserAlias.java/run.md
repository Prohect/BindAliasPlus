# run 方法（src/client/java/com/github/prohect/alias/UserAlias.java）

## 语法

```java
public UserAlias run(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | **被忽略**——`UserAlias` 改用自身存储的 `this.args` 定义字符串 |

## 返回值

返回 `this`（`UserAlias` 实例）。

## 备注

别名链执行的主要入口点。由外部触发器（MCP server、按键绑定）调用以执行用户定义的别名链。

**执行流程**：

1. **解码**：调用 `decodeArgs2Alias(this.args)` 将存储的定义字符串解析为 `ArrayDeque<AliasRecord>`。
2. **分派循环**：对队列中的每个 `AliasRecord`：
   - **按顺序**在全局映射中查找别名：`aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`。
   - **`null`**：静默跳过（拼写错误或缺失的别名）。
   - **`UserAlias`**：以此别名作为调用链的根，委托给 `runInternal()`。
   - **`WaitAlias`**：将队列中剩余条目收集为延续字符串，调用 `waitAlias.run(args, continuation)`，然后**立即返回**（延迟后续执行）。
   - **其他内置别名**：执行。如果别名位于界面黑名单（`blackList4Screen`）中且界面已打开，则只有松开事件（`"0"` 参数）会执行。

**界面黑名单逻辑**：当别名在 `blackList4Screen` 中时：
- `!isUnderAnyScreen()` → 正常执行
- 界面打开 + `args == "0"`（松开）→ 执行（这样按键不会卡住）
- 界面打开 + `args != "0"` → 被抑制

**重要**：此方法完全忽略其 `args` 参数。定义字符串来自别名自身的 `this.args` 字段，在构造时设置。该参数仅用于满足 `AliasWithoutArgs` 接口契约。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [runInternal](runInternal.md) | 带循环检测的递归变体 |
| [decodeArgs2Alias](decodeArgs2Alias.md) | 解析步骤 |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | 分派队列使用的 record 类型 |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | 延迟执行目标 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
