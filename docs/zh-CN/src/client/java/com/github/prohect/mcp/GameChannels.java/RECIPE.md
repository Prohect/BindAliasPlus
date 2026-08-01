# RECIPE 字段（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static final String RECIPE = "recipe"
```

## 备注

新解锁配方通知的 channel（频道）名常量。由 [`ClientPacketListenerMixin`](../../mixin/client/ClientPacketListenerMixin.java/README.md) 提供数据，它拦截 `RecipeBookAddS2CPacket`，并以 `Entry#shouldShowNotification() == true` 的每个配方按其结果物品的语言显示名上报。非合并式：每次配方解锁都是独立的条目。尽力而为：数据包处理过程中的异常被静默吞掉。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ClientPacketListenerMixin](../../mixin/client/ClientPacketListenerMixin.java/README.md) | 此 channel 的数据提供方 |
| [RecipeBookHelper](RecipeBookHelper.java/README.md) | `listRecipes`/`applyRecipe` API 的读取侧 |
| [CHAT](CHAT.md) | 聊天 channel |
| [MOD](MOD.md) | 模组日志 channel |
| [SOUND](SOUND.md) | 声音事件 channel |
