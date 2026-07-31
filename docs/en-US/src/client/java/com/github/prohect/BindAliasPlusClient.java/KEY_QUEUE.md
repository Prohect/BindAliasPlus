# KEY_QUEUE field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static final java.util.ArrayDeque<com.github.prohect.KeyPressed> KEY_QUEUE
```

## Remarks

FIFO queue of pending `KeyPressed` events. Populated by the key-mixin hook
(keyboard/mouse handlers) and consumed each tick by the mod's tick handler
to dispatch alias chains.

`ArrayDeque` is used for O(1) push/pop. Not thread-safe — intended for
the render thread only.

Cleared on disconnect.

## See Also

| Item                                           | Description                          |
| ---------------------------------------------- | ------------------------------------ |
| [KeyPressed](../KeyPressed.java/KeyPressed.md) | The record type stored in this queue | [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)* |
