# pressed method (src/client/java/com/github/prohect/KeyPressed.java)

## Syntax

```java
public boolean pressed()
```

## Parameters

_None._

## Remarks

Record accessor for the press state — `true` for key-down / button-down, `false` for key-up / button-up. The `MinecraftClientMixin` tick loop uses this to decide whether to invoke the press alias or the release alias from `BindAliasKeyBinding`.

## See Also

| Item | Description |
|------|-------------|
| [key](key.md) | The key involved in the event |
| [BindAliasKeyBinding](../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | Press/release alias pair dispatched based on this flag |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
