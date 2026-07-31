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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
