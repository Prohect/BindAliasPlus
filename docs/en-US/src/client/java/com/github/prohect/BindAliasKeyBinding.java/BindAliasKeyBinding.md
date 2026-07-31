# BindAliasKeyBinding (src/client/java/com/github/prohect/BindAliasKeyBinding.java)

## Syntax

```java
public final class com.github.prohect.BindAliasKeyBinding extends java.lang.Record
```

## Static Initializer

_None._

## Remarks

A Java `record` that maps a key to the aliases that run when the key is pressed and released. Only `AliasWithoutArgs` aliases can be triggered by key events because key bindings pass no arguments — the underlying `UserAlias.run("")` is called with an empty args string.

Each record has three components:
- `aliasNameOnKeyPressed` — alias invoked on key-down.
- `aliasNameOnKeyReleased` — alias invoked on key-up (may be empty string for one-shot key actions where only the press matters).
- `fromCFG` — tracks whether this binding was loaded from the config file so `unloadCFGBinds` can clean it.

A convenience constructor defaults `fromCFG` to `false` for runtime-created bindings.

Immutable by virtue of being a record. Instances are stored in `BindAliasClient.BINDING_PLUS` and looked up each tick by `MinecraftClientMixin` when draining `KEY_QUEUE`.

## See Also

| Item | Description |
|------|-------------|
| [BINDING_PLUS](../BindAliasClient.java/BINDING_PLUS.md) | The map where these bindings live |
| [KeyPressed](../KeyPressed.java/KeyPressed.md) | Key events that trigger alias lookups in `BINDING_PLUS` |
| [commandBindExecute](../BindAliasClient.java/commandBindExecute.md) | Creates these bindings at runtime |
| [commandBindByAliasNameExecute](../BindAliasClient.java/commandBindByAliasNameExecute.md) | Creates these bindings by alias name |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

