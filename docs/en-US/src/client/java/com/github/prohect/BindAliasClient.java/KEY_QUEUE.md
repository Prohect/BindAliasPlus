# KEY_QUEUE field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static final java.util.ArrayDeque<com.github.prohect.KeyPressed> KEY_QUEUE
```

## Remarks

A FIFO queue of pending key events. Writers: `KeyBoardMixin` (keyboard presses/releases) and `MouseMixin` (mouse button presses/releases). Reader: `MinecraftClientMixin` (each tick, it drains the queue and dispatches events by looking up `BINDING_PLUS`). Cleared on disconnect. Must only be accessed on the game thread.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
