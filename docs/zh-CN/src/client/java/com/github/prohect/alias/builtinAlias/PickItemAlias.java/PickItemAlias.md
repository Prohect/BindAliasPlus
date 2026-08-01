# PickItemAlias (src/client/java/com/github/prohect/alias/builtinAlias/PickItemAlias.java)

触发原版选取方块/实体行为的一次性别名——将玩家正在注视的方块或实体选取到选中的快捷栏槽位上。继承自 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.PickItemAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.PickItemAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `pickItem`（内部，以 `pickItem` 暴露）。

**行为：** 模拟按下原版选取方块按键绑定（`keyPickItem`，默认：鼠标中键）。将按键绑定设为"按下"并递增 `clickCount`，使游戏在下一个轮询周期调用 `pickBlockOrEntity()`。这会选中与目标方块/实体匹配的快捷栏槽位，或将物品栏中匹配的物品移动到选中的槽位。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，别名被取消。

**要求：** `mc.player` 必须非 null（否则静默返回）。

**与原版选取方块的关键区别：**
- 创造模式下，方块/实体直接给予选中的槽位。
- 生存模式下，若存在与目标方块/实体匹配的物品，则通过（SWAP）从物品栏移动到选中的槽位。
- 此别名触发的游戏逻辑路径与默认中键按键绑定完全相同。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 一次性别名的基类 |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | 直接选择快捷栏槽位（无选取方块逻辑） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
