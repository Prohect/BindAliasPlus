# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnbindAlias.java）

构建并向服务器发送 `unbind` 命令。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnbindAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | unbind 命令的参数（按键名称等） |

## 备注

**算法：**

1. 构建命令行：`"unbind" + divider4AliasDefinition + processedArgs`。
   - `divider4AliasDefinition` 是空格字符（`' '`）。
   - 参数经过预处理：反斜杠通过正则表达式替换为空格。
2. 如果 `mc.player` 为 null，记录一条警告并返回。
3. 发送命令：`player.connection.sendCommand(line)`。

**返回值：** `this`（流畅式返回）。

**副作用：** 向服务器的命令处理器发送 unbind 命令。服务器处理该命令以移除指定的按键绑定。

**无界面抑制：** 在任何界面上都能工作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnbindAlias](UnbindAlias.md) | 类概览 |
| [SendCommandAlias](../SendCommandAlias.java/run.md) | 底层命令分发 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
