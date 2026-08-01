# isUnderTextInputScreen 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static boolean isUnderTextInputScreen()
```

## 返回值

若当前界面是文本输入界面：`ChatScreen`、`CommandBlockEditScreen`、`SignEditScreen` 或 `BookEditScreen`，返回 `true`，否则返回 `false`。

## 备注

布尔参数别名（移动、攻击、使用）用它来在玩家输入文字时抑制按键注入。如果玩家在聊天界面打开时按下 `W`，`+forward` 别名会先检查此方法，并且**不会**向前进移动注入游戏的 `KeyboardInput`。

**调用方**：所有与游戏操作交互的 `+`/`-` 开关别名（`+attack`、`+use`、`+forward`、`+back`、`+left`、`+right`、`+jump`、`+sneak`、`+sprint`、`+drop`）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isUnderAnyScreen](isUnderAnyScreen.md) | 限制更少 —— 任意界面均返回 `true` |
| [getCurrentScreen](getCurrentScreen.md) | 底层的界面查询 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
