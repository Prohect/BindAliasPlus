# KeyBindingPlus

## Fields (Record Components)

| Name                     | Type      | Description                                                             |
| ------------------------ | --------- | ----------------------------------------------------------------------- |
| `aliasNameOnKeyPressed`  | `String`  | Alias name dispatched when the bound key is pressed down. May be empty. |
| `aliasNameOnKeyReleased` | `String`  | Alias name dispatched when the bound key is released. May be empty.     |
| `fromAutoload`           | `boolean` | `true` if this binding was loaded from `bind-alias-plus.cfg`.           |

## Methods

| Name                           | Signature                        | Description                                                                    |
| ------------------------------ | -------------------------------- | ------------------------------------------------------------------------------ |
| `KeyBindingPlus` (convenience) | `KeyBindingPlus(String, String)` | Two-arg constructor for runtime-created bindings; sets `fromAutoload = false`. |

_Auto-generated: `equals`, `hashCode`, `toString`, and accessor methods._

## See Also

| Item                                                        | Description                                           |
| ----------------------------------------------------------- | ----------------------------------------------------- |
| [KeyPressed](../KeyPressed.java/KeyPressed.md)              | The key event that triggers this binding              |
| [BINDING_PLUS](../BindAliasPlusClient.java/BINDING_PLUS.md) | The map that stores `Key` → `KeyBindingPlus` mappings |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
