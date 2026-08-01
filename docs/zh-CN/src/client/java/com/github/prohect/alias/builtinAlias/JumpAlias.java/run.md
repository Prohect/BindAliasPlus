# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/JumpAlias.java）

通过操纵原版跳跃按键绑定来处理 `+jump`（按下）和 `-jump`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.JumpAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 按下（`+jump`）为 `"1"`，松开（`-jump`）为 `"0"` |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 如果 `flag` 为 true 且文本输入界面已打开，则立即返回。跳跃在非文本界面上有效。松开事件始终处理。
3. 获取 `MinecraftClient.getInstance().options.jumpKey` 并调用 `setPressed(flag)`，按下时额外执行 `timesPressed++`。

按住 `+jump` 使玩家在地面上持续跳跃（与原版按住空格行为一致）并在水中向上游。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SneakAlias.run()](../SneakAlias.java/run.md) | 潜行的相同模式 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
