# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/BindAlias.java）

向服务器发送 `/bind` 命令，为别名创建按键绑定。

## 语法

```java
public com.github.prohect.alias.builtinAlias.BindAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 别名名称与按键，以 `\` 分隔：`aliasName\<key>` |

## 备注

1. 构造命令行：`"bind" + Alias.divider4AliasDefinition + normalizedArgs`。
2. 规范化参数：将任何参数分隔符（`\`）替换为正确的别名定义分隔符。这确保分隔符格式一致。
3. 检查 `Minecraft.player` 是否非 null。若为 null，记录警告。
4. 通过 `player.connection.sendCommand(line)` 发送命令。

服务器处理 `/bind` 命令，创建将物理按键或鼠标按钮与别名名称关联的持久按键绑定。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasAlias.run()](../AliasAlias.java/run.md) | `/alias` 命令的相同模式 |
| [BindAliasKeyBinding](../../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | 本地按键绑定表示 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
