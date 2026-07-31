# fromCFG method (src/client/java/com/github/prohect/BindAliasKeyBinding.java)

## Syntax

```java
public boolean fromCFG()
```

## Parameters

_None._

## Remarks

Record accessor indicating whether this binding was loaded from the CFG file. When `true`, the binding is tracked for removal by `unloadCFGBinds`. Runtime bindings (created via `/bind` or `/bindByAliasName` commands) use the convenience constructor, which defaults this to `false`.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGBindsAlias](../alias/builtinAlias/UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | Removes all bindings with `fromCFG = true` |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
