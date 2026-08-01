# getKeyBindingForAction 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

将游戏动作类型字符串映射到对应的原版 `KeyMapping`。

## 语法

```java
private static net.minecraft.client.KeyMapping getKeyBindingForAction(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 游戏动作，可带可不带 `"gameKey:"` 前缀（例如 `"attack"`、`"gameKey:forward"`） |

## 返回值

对应的 `Minecraft.options` KeyMapping；若动作不是可识别的游戏按键，则返回 `null`。

## 备注

若存在 `"gameKey:"` 前缀则剥离，然后通过 switch 表达式将裸动作名映射到其 KeyMapping：

| 动作 | KeyMapping |
|--------|-----------|
| `attack` | `options.keyAttack` |
| `use` | `options.keyUse` |
| `forward` | `options.keyUp` |
| `back` | `options.keyDown` |
| `left` | `options.keyLeft` |
| `right` | `options.keyRight` |
| `jump` | `options.keyJump` |
| `sneak` | `options.keyShift` |
| `sprint` | `options.keySprint` |

对任何无法识别的动作返回 `null`——调用方随后回退到 `lockAliasByName()`/`unlockAliasByName()`，将输入当作自定义别名名称处理。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
