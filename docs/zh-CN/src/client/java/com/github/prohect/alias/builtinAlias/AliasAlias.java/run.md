# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/AliasAlias.java）

向服务器发送 `/alias` 命令，以便在运行时定义用户别名。

## 语法

```java
public com.github.prohect.alias.builtinAlias.AliasAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 别名名称与定义，以 `\` 分隔：`aliasName\<definition>` |

## 备注

1. 构造命令行：`"alias" + Alias.divider4AliasDefinition + normalizedArgs`。
2. 规范化参数：将任何参数分隔符（`\`）替换为正确的别名定义分隔符。这确保无论用户如何输入链，分隔符都保持一致。
3. 检查 `Minecraft.player` 是否非 null（必须已连接到服务器）。若为 null，记录警告并返回。
4. 通过 `player.connection.sendCommand(line)` 发送命令。

**示例：** `alias\turnRight yaw\90` 发送服务器命令 `alias<sep>turnRight yaw<sep>90`，服务器处理该命令后定义一个旋转 90 度的 `turnRight` 别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAlias.run()](../BindAlias.java/run.md) | `/bind` 命令的相同模式 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 已定义别名的本地表示 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
