# JumpAlias (src/client/java/com/github/prohect/alias/builtinAlias/JumpAlias.java)

模拟跳跃按键绑定（空格键）的内置别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.JumpAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.JumpAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinJump"`。用法：`+jump` 按下，`-jump` 松开。文本输入界面上按下事件会被抑制；跳跃在非文本界面上有效。按住 `+jump` 使玩家在陆地上持续跳跃、在水中向上游动，匹配原版行为。

实现操纵 `Minecraft.options.keyJump`：

- `setDown(flag)` — 按住或松开按键
- `clickCount++` — 按下时递增点击计数

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SneakAlias](../SneakAlias.java/SneakAlias.md) | 潜行键对应别名 |
| [SprintAlias](../SprintAlias.java/SprintAlias.md) | 疾跑键对应别名 |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动键 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
