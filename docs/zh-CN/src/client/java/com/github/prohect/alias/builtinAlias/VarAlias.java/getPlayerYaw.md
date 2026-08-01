# getPlayerYaw 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

返回玩家当前的偏航角。

## 语法

```java
private java.lang.Double getPlayerYaw()
```

## 备注

**返回值：** 玩家当前的 Y 旋转（偏航角）作为 `Double`，如果玩家不可用则返回 null。

**偏航角方向：** 0 = 南，90 = 西，180 = 北，270 = 东。数值不会被钳制（它们会环绕）。

**错误处理：** 如果不可用，记录 `"[var] Player is null"`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | 主要调用者（用于 `"yaw"` 源） |
| [getPlayerPitch](getPlayerPitch.md) | 俯仰角对应物 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
