# loadCFG 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public void loadCFG()
```

## 参数

_无。_

## 备注

读取 `cfgPath` 处的 CFG 文件，并将每个非注释、非空行分发给相应的处理器：

1. 若文件不存在，创建它并立即返回（没有可加载的内容）。
2. 通过 `Files.newInputStream` 将整个文件读入字节数组。
3. 将字节转换为 `String` 并遍历各行。
4. 对每一行：去除开头的 `/`（旧式注释记法），然后：
   - `alias <name> <definition>` → `commandAliasExecute(name, definition, true)`
   - `bind <key> <definition>` → `commandBindExecute(key, definition, true)`
   - `bindByAliasName <key> <aliasName>` → `commandBindByAliasNameExecute(key, aliasName, true)`
   - `unbind <key>` → `commandUnbindExecute(key)`
   - `var <name> <source>` → `commandVarExecute(name, source, true)`
   - `runAlias <definition>` → 将定义作为 `UserAlias` 链运行
   - 未知命令 → 记录为警告

调用每个处理器的 `fromAutoload=true` 变体，使 CFG 加载的绑定/变量/别名被跟踪，并可由 `unloadCFG*` 命令清理。每行的错误会被捕获并记录，不会中止其余文件的处理。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [cfgPath](cfgPath.md) | CFG 文件的路径 |
| [commandAliasExecute](commandAliasExecute.md) | 处理 `alias` 行 |
| [commandBindExecute](commandBindExecute.md) | 处理 `bind` 行 |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | 处理 `bindByAliasName` 行 |
| [commandUnbindExecute](commandUnbindExecute.md) | 处理 `unbind` 行 |
| [commandVarExecute](commandVarExecute.md) | 处理 `var` 行 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
