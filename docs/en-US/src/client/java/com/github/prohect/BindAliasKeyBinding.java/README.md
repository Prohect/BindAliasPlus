# BindAliasKeyBinding

## Fields

| Name | Type | Description |
|------|------|-------------|
| [aliasNameOnKeyPressed](aliasNameOnKeyPressed.md) | `String` | Alias name invoked on key press (may be empty) |
| [aliasNameOnKeyReleased](aliasNameOnKeyReleased.md) | `String` | Alias name invoked on key release (may be empty) |
| [fromCFG](fromCFG.md) | `boolean` | Whether this binding was loaded from the CFG file |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| `BindAliasKeyBinding(String, String)` | Convenience constructor | Creates a runtime binding with `fromCFG = false` |
| [equals](equals.md) | `boolean equals(Object)` | Record equality — all three components must match |
| [hashCode](hashCode.md) | `int hashCode()` | Record hash — derived from all three components |
| [toString](toString.md) | `String toString()` | Record string representation |

## See Also

| Item | Description |
|------|-------------|
| [BINDING_PLUS](../BindAliasClient.java/BINDING_PLUS.md) | The map where these bindings live |
| [KeyPressed](../KeyPressed.java/KeyPressed.md) | Key events that trigger alias lookups |
| [commandBindExecute](../BindAliasClient.java/commandBindExecute.md) | Creates these bindings at runtime |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
