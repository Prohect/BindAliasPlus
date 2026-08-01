# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/PickItemAlias.java）

触发原版选取方块按键绑定以选取目标方块/实体。

## 语法

```java
public com.github.prohect.alias.builtinAlias.PickItemAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 若 `isUnderTextInputScreen()` 为 true，立即返回（界面抑制）。
2. 若 `mc.player` 为 null，立即返回。
3. 从 `mc.options` 获取原版 `keyPickItem` 按键绑定。
4. 调用 `pickKey.setDown(true)` 和 `pickKey.clickCount++` 模拟选取方块按键按下。
5. 游戏在下一个轮询周期通过 `pickBlockOrEntity()` 处理选取。

**副作用：**
- 创造模式下：方块/实体被克隆到选中的快捷栏槽位。
- 生存模式下：匹配的物品从物品栏移动到选中的槽位。
- `keyPickItem` 按键绑定状态被修改（设为按下、点击计数递增）。

**界面抑制：** 文本输入界面（聊天界面、告示牌、书、命令方块）打开时被取消。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PickItemAlias](PickItemAlias.md) | 类概览 |
| [SlotAlias](../SlotAlias.java/run.md) | 直接快捷栏槽位选择 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
