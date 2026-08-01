# RECIPE 字段（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static final String RECIPE = "recipe"
```

## 备注

新解锁配方通知的 channel 名称常量。由 [`ClientPacketListenerMixin`](../../mixin/client/ClientPacketListenerMixin.java/README.md) 供给，它拦截 `ClientboundRecipeBookAddPacket`，并对每个 `Entry#notification() == true` 的配方以其结果物品的语言显示名上报。非合并：每个配方解锁都是单独的条目。尽力而为：处理数据包期间的异常被静默吞掉。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ClientPacketListenerMixin](../../mixin/client/ClientPacketListenerMixin.java/README.md) | 此 channel 的供给方 |
| [RecipeBookHelper](RecipeBookHelper.java/README.md) | `listRecipes`/`applyRecipe` API 的读取侧 |
| [CHAT](CHAT.md) | 聊天 channel |
| [MOD](MOD.md) | 模组日志 channel |
| [SOUND](SOUND.md) | 声音事件 channel |
