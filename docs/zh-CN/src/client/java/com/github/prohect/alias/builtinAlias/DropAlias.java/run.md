# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java）

处理 `+drop`（按下）和 `-drop`（松开），支持立即的首次丢弃和容器界面感知。

## 语法

```java
public com.github.prohect.alias.builtinAlias.DropAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示按下（`+drop`），`"0"` 表示松开（`-drop`） |

## 备注

**按下（`flag == true`）：**

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下）：** 若文本输入界面打开，则立即返回。
3. 若玩家为 null，则立即返回。
4. 检查容器界面（`AbstractContainerScreen`）是否打开：
   - **容器路径：** 若悬停的槽位有物品，则通过 `containerScreen.slotClicked(hoveredSlot, hoveredSlot.index, button, ContainerInput.THROW)` 立即丢弃。按住 Ctrl 时按钮为 1（整个堆叠），否则为 0（单个物品）。不设置 KeyMapping 直接返回——后续的持续丢弃由 `tickDrop()` 处理。
   - **3D 游戏路径：** 设置 `keyDrop.setDown(true)` 并递增 `clickCount++` 以触发立即的首次丢弃。之后由 `tickDrop()` 驱动持续丢弃。

**松开（`flag == false`）：**

1. 重置 `ticksHeld = 0`。
2. 设置 `keyDrop.setDown(false)`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [tickDrop](tickDrop.md) | 每个客户端刻调用的持续丢弃驱动器 |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | 界面切换后无额外丢弃的重新应用 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
