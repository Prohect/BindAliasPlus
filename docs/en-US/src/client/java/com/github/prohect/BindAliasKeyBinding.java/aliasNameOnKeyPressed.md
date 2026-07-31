# aliasNameOnKeyPressed method (src/client/java/com/github/prohect/BindAliasKeyBinding.java)

## Syntax

```java
public java.lang.String aliasNameOnKeyPressed()
```

## Parameters

_None._

## Remarks

Record accessor for the alias name invoked on key press. This is the first argument to the canonical constructor. May be an empty string if no press action is defined. The alias must be an `AliasWithoutArgs` — it will be invoked via `UserAlias.run("")` with no arguments.

## See Also

| Item | Description |
|------|-------------|
| [aliasNameOnKeyReleased](aliasNameOnKeyReleased.md) | The alias invoked on key release |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
