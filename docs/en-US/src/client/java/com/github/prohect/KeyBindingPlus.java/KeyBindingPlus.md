# KeyBindingPlus (src/client/java/com/github/prohect/KeyBindingPlus.java)

## Syntax

```java
public final class com.github.prohect.KeyBindingPlus extends java.lang.Record
```

## Static Initializer

_None._

## Remarks

Immutable record that maps a keyboard or mouse key press to alias names
to dispatch on press and release. Stored in [BINDING_PLUS](../BindAliasPlusClient.java/BINDING_PLUS.md).

Only aliasWithoutArgs aliases can be triggered by a key event, since
they contain no runtime arguments — the alias name alone is sufficient.

The two-arg convenience constructor is used for runtime bindings
(e.g., `/bind` command); the three-arg canonical constructor is used
when loading from the config file to preserve autoload provenance.

## See Also

| Item                                                                    | Description                                 |
| ----------------------------------------------------------------------- | ------------------------------------------- |
| [KeyPressed](../KeyPressed.java/KeyPressed.md)                          | Key event record consumed via `KEY_QUEUE`   |
| [BINDING_PLUS](../BindAliasPlusClient.java/BINDING_PLUS.md)             | The map storing `Key` → `KeyBindingPlus`    |
| [commandBindExecute](../BindAliasPlusClient.java/commandBindExecute.md) | Creates `KeyBindingPlus` entries at runtime |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
