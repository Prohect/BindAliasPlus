# KeyPressed (src/client/java/com/github/prohect/KeyPressed.java)

## Syntax

```java
public final class com.github.prohect.KeyPressed extends java.lang.Record
```

## Static Initializer

_None._

## Remarks

Immutable record representing a single key input event (press or release).
Instances are pushed to [KEY_QUEUE](../BindAliasClient.java/KEY_QUEUE.md)
by the key-mixin hook and consumed by the mod's tick handler to dispatch
alias chains.

Not thread-safe (intended for the render thread only).

## See Also

| Item                                                       | Description                                       |
| ---------------------------------------------------------- | ------------------------------------------------- |
| [KeyBindingPlus](../KeyBindingPlus.java/KeyBindingPlus.md) | Binds a key to aliases triggered on press/release |
| [KEY_QUEUE](../BindAliasClient.java/KEY_QUEUE.md)      | The queue that holds these events                 |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
