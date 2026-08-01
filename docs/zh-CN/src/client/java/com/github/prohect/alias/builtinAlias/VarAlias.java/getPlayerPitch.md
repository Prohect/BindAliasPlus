# getPlayerPitch 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

返回玩家当前的俯仰角。

## 语法

```java
private java.lang.Double getPlayerPitch()
```

## 备注

**返回值：** 玩家当前的 X 旋转（俯仰角）作为 `Double`，如果玩家不可用则返回 null。

**俯仰角方向：** 负值向上看，正值向下看。原版游戏将俯仰角钳制在 [-90, 90] 范围内。

**错误处理：** 如果不可用，记录 `"[var] Player is null"`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | 主要调用者（用于 `"pitch"` 源） |
| [getPlayerYaw](getPlayerYaw.md) | 偏航角对应物 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
