# getKeyBindingForAction 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

将游戏动作类型字符串映射到其对应的原版 `KeyBinding`。

## 语法

```java
private static net.minecraft.client.option.KeyBinding getKeyBindingForAction(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 游戏动作，可带或不带 `"gameKey:"` 前缀（例如 `"attack"`、`"gameKey:forward"`） |

## 返回值

对应的 `MinecraftClient.getInstance().options` KeyBinding，如果该动作不是可识别的游戏按键则返回 `null`。

## 备注

如果有 `"gameKey:"` 前缀则去掉，然后通过 switch 表达式将裸动作名映射到其 KeyBinding：

| 动作 | KeyBinding |
|--------|-----------|
| `attack` | `options.attackKey` |
| `use` | `options.useKey` |
| `forward` | `options.forwardKey` |
| `back` | `options.backKey` |
| `left` | `options.leftKey` |
| `right` | `options.rightKey` |
| `jump` | `options.jumpKey` |
| `sneak` | `options.sneakKey` |
| `sprint` | `options.sprintKey` |

对任何无法识别的动作返回 `null`——调用方随后回退到 `lockAliasByName()`/`unlockAliasByName()`，将输入当作自定义别名名称处理。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
