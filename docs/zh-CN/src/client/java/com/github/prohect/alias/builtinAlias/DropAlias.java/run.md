# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java）

处理 `+drop`（按下）和 `-drop`（松开），带立即的首次丢弃和容器界面感知。

## 语法

```java
public com.github.prohect.alias.builtinAlias.DropAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 按下（`+drop`）为 `"1"`，松开（`-drop`）为 `"0"` |

## 备注

**按下（`flag == true`）：**

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下）：** 如果文本输入界面已打开，则立即返回。
3. 如果玩家为 null，则立即返回。
4. 检查容器界面（`HandledScreen`）是否打开：
   - **容器路径：** 如果悬停槽位有物品，则通过 `containerScreen.onMouseClick(hoveredSlot, hoveredSlot.index, button, SlotActionType.THROW)` 立即丢弃。按住 Ctrl 时按钮为 1（整个堆叠），否则为 0（单个物品）。返回而不设置 KeyBinding——后续的持续丢弃由 `tickDrop()` 处理。
   - **3D 游戏路径：** 设置 `dropKey.setPressed(true)` 并递增 `timesPressed++` 以触发立即的首次丢弃。之后的持续丢弃由 `tickDrop()` 驱动。

**松开（`flag == false`）：**

1. 重置 `ticksHeld = 0`。
2. 设置 `dropKey.setPressed(false)`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [tickDrop](tickDrop.md) | 每个客户端刻调用的持续丢弃驱动器 |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | 界面切换后不产生额外丢弃的重新应用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
