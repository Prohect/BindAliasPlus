# UserAlias（src/client/java/com/github/prohect/alias/UserAlias.java）

## 语法

```java
public final class UserAlias implements AliasWithoutArgs<UserAlias>
```

表示用户定义的别名链——从 CFG 文件或 `alias` 内置命令创建的一系列别名调用。这是别名系统中的**主要执行器**：任何别名链运行时，都是由 `UserAlias` 解析链、在全局映射中按名称查找每个组成别名并分派执行。

## 备注

`UserAlias` 是唯一非内置的实现 `AliasWithoutArgs` 的类。它是层次结构中的**叶子**——不能被继承（`final`）。

### 构造

三个构造函数支持不同的来源：

| 构造函数 | 用途 |
|-------------|---------|
| `UserAlias(String args)` | 运行时用户创建（例如通过 `alias` 命令） |
| `UserAlias(String args, boolean fromAutoload)` | 启动时从 CFG 文件加载 |
| `UserAlias(String args, boolean fromCFG, boolean predefined)` | 受保护 / 预定义别名，不能被覆盖 |

`args` 字符串保存原始定义——以空格分隔的别名调用链，例如 `"+attack slot\1 wait\5 -attack"`。

### 执行流程（`run`）

1. **解码**：`decodeArgs2Alias(this.args)` 将定义字符串解析为 `ArrayDeque<AliasRecord>`。每个 `AliasRecord` 保存一个别名名称及其参数。
2. **分派循环**：对队列中的每个 `AliasRecord`：
   - 在全局映射中查找别名：`aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`。
   - **`null`**：静默跳过。
   - **`UserAlias`**：委托给 `runInternal()`（递归，通过调用链跟踪进行循环检测）。
   - **`WaitAlias`**：延迟执行——将队列中剩余条目收集为延续字符串，并调用 `waitAlias.run(args, continuation)`。调度后方法立即返回。
   - **其他内置别名**：通过 `alias.run(aliasRecord.args())` 执行。如果别名位于界面黑名单（`blackList4Screen`）中且界面已打开，则只有松开事件（`args.equals("0")`）会通过。

### `runInternal`

与 `run` 相同的分派逻辑，但维护 `List<UserAlias>` 调用链以检测无限递归。如果 `UserAlias` 已在调用链中，则记录警告日志并跳过。遇到 `WaitAlias` 时，它还会收集调用链中父别名的剩余条目（展开栈），以保留完整的延迟延续。

### CFG 跟踪

`fromCFG` 标志区分从配置文件加载的别名。`setFromCFG` 设置方法和 `isFromCFG` 获取方法使 `unloadCFGAliases` 能够识别并只移除 CFG 加载的别名，而不触碰用户创建或内置的别名。

### 预定义保护

`predefined` 标志（通过 3 参构造函数设置）将别名标记为受保护——它不能被新的 `alias` 定义覆盖。`AliasAlias` 在覆盖前使用 `isPredefined()` 获取方法进行检查。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | UserAlias 实现的接口 |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | 内部别名队列使用的 record 类型 |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | 延迟执行——UserAlias 将控制权交给它 |
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | 创建 / 覆盖 UserAlias 的 `alias` 内置别名 |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | 注册 CFG 加载的 UserAlias 的位置 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
