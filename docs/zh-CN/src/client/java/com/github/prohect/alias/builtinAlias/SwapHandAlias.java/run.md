# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java）

向服务器发送 `SWAP_ITEM_WITH_OFFHAND` 数据包以交换主手和副手物品。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SwapHandAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 如果 `isUnderTextInputScreen()` 为 true，则立即返回。
2. 通过 `mc.getNetworkHandler()` 获取网络处理器。
3. 如果为 null，记录警告并返回。
4. 向服务器发送 `PlayerActionC2SPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN)`。（Yarn：`PlayerActionC2SPacket`；Mojang：`ServerboundPlayerActionPacket`）

**返回值：** `this`（流畅返回）。

**副作用：** 通过服务器数据包交换主手和副手物品。交换在服务端处理，客户端物品栏相应更新。

**界面抑制：** 在文本输入界面上被取消。

**为什么不用按键绑定：** 早期基于按键绑定的方法（源码中已注释）被直接发送数据包所取代，以保证可靠性——它避免了按键绑定状态冲突和轮询延迟。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SwapHandAlias](SwapHandAlias.md) | 类概览 |
| [SwapSlotAlias](../SwapSlotAlias.java/run.md) | 交换任意两个槽位 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
