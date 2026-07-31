# aliasNameOnKeyReleased method (src/client/java/com/github/prohect/BindAliasKeyBinding.java)

## Syntax

```java
public java.lang.String aliasNameOnKeyReleased()
```

## Parameters

_None._

## Remarks

Record accessor for the alias name invoked on key release. This is the second argument to the canonical constructor. May be an empty string for one-shot actions where only the press matters (e.g., `esc` only runs on press). The alias must be an `AliasWithoutArgs`.

## See Also

| Item | Description |
|------|-------------|
| [aliasNameOnKeyPressed](aliasNameOnKeyPressed.md) | The alias invoked on key press |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
