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

1. 如果 `isUnderTextInputScreen()` 为 true，则立即返回（界面抑制）。
2. 如果 `mc.player` 为 null，则立即返回。
3. 从 `mc.options` 获取原版 `pickItemKey` 按键绑定。
4. 调用 `pickKey.setPressed(true)` 和 `pickKey.timesPressed++` 模拟选取方块按键按下。
5. 游戏在下一个轮询周期通过 `pickBlockOrEntity()` 处理选取。

**副作用：**
- 在创造模式中：方块/实体被复制到选中的快捷栏槽位。
- 在生存模式中：匹配的物品从物品栏移动到选中的槽位。
- `pickItemKey` 按键绑定状态被修改（设置为按下，点击计数递增）。

**界面抑制：** 文本输入界面（聊天界面、告示牌、书、命令方块）打开时被取消。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PickItemAlias](PickItemAlias.md) | 类概览 |
| [SlotAlias](../SlotAlias.java/run.md) | 直接快捷栏槽位选择 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
