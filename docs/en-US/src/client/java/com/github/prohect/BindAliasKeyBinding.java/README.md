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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
