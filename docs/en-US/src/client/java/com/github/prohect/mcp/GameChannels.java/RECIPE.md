# RECIPE field (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static final String RECIPE = "recipe"
```

## Remarks

Channel name constant for newly unlocked recipe notifications. Fed by [`ClientPacketListenerMixin`](../../mixin/client/ClientPacketListenerMixin.java/README.md) which intercepts `RecipeBookAddS2CPacket` and reports each recipe with `Entry#shouldShowNotification() == true` by its result item's locale display name. Non-coalescing: each recipe unlock is a separate entry. Best-effort: exceptions during packet processing are silently swallowed.

## See Also

| Item | Description |
|------|-------------|
| [ClientPacketListenerMixin](../../mixin/client/ClientPacketListenerMixin.java/README.md) | Feeder of this channel |
| [RecipeBookHelper](RecipeBookHelper.java/README.md) | Read side for the `listRecipes`/`applyRecipe` APIs |
| [CHAT](CHAT.md) | The chat channel |
| [MOD](MOD.md) | The mod-log channel |
| [SOUND](SOUND.md) | The sound-event channel |
