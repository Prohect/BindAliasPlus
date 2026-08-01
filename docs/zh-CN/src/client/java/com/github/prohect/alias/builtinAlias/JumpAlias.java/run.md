# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/JumpAlias.java）

通过操纵原版跳跃按键绑定来处理 `+jump`（按下）和 `-jump`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.JumpAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示按下（`+jump`），`"0"` 表示松开（`-jump`） |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 若 `flag` 为 true 且文本输入界面打开，则立即返回。跳跃在非文本界面上有效。松开事件始终处理。
3. 获取 `Minecraft.options.keyJump`，按下时调用 `setDown(flag)` 并执行 `clickCount++`。

按住 `+jump` 使玩家在陆地上持续跳跃（匹配原版空格键按住行为）并在水中向上游动。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SneakAlias.run()](../SneakAlias.java/run.md) | 潜行的相同模式 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
