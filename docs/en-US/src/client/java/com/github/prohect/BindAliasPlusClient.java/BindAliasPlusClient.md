# BindAliasPlusClient (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
public class com.github.prohect.BindAliasPlusClient implements net.fabricmc.api.ClientModInitializer
```

## Static Initializer

_None._

## Remarks

Central client-side mod initializer and singleton. Implements `ClientModInitializer`.

**Purpose**: Registers all built-in aliases, registers client-side commands (`/alias`,
`/bind`, `/bindByAliasName`, `/unbind`, `/var`, `/reloadCFG`, `/unloadCFG*`,
`/runAlias`), wires autoload on world join via `ClientPlayConnectionEvents.JOIN`,
and cleans up state on disconnect.

**Lifecycle**: Eagerly instantiated as `INSTANCE`. `onInitializeClient()` is called
once by Fabric Loader. `loadCFG()` is called automatically on world join.

**Thread safety**: Not thread-safe. All state (`KEY_QUEUE`, `BINDING_PLUS`,
`silentMode`, `currentScreen`) is accessed only from the render thread.

**Key collaborators**:

- [Alias](../alias/Alias.java/Alias.md) and subclasses — built-in and user alias definitions.
- [KeyBindingPlus](../KeyBindingPlus.java/KeyBindingPlus.md) / [KeyPressed](../KeyPressed.java/KeyPressed.md) — key binding infrastructure.
- `GuiMixin` — updates `currentScreen` on screen changes.
- [McScreenHelper](../util/McScreenHelper.java/McScreenHelper.md) — version-agnostic screen access.

## See Also

| Item                                                                                          | Description                  |
| --------------------------------------------------------------------------------------------- | ---------------------------- |
| [ClientModInitializer](https://fabricmc.net/wiki/documentation:client_mod_initializer)        | Fabric client init interface |
| [BindAliasPlus](../../../../main/java/com/github/prohect/BindAliasPlus.java/BindAliasPlus.md) | Common-side mod entry point  |
| [loadCFG](loadCFG.md)                                                                         | Config file loader           |
| [onInitializeClient](onInitializeClient.md)                                                   | Full init routine            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
