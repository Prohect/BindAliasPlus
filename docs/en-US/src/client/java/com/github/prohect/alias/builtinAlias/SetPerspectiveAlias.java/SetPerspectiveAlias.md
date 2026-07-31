# SetPerspectiveAlias (src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java)

Builtin alias that sets the camera perspective to first-person, third-person back, or third-person front. Extends `BuiltinAliasWithIntegerArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SetPerspectiveAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.SetPerspectiveAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinSetPerspective` (internal, exposed via `builtinSetPerspective`).

**Usage:** `builtinSetPerspective\N` where N is:
- `0` — First Person (FPS)
- `1` — Third Person Back (TPS)
- `2` — Third Person Front (TPS2)

**Behavior:** Sets the camera to the specified perspective mode. If the perspective change crosses between first-person and third-person, the camera entity is updated accordingly (`setCameraEntity`).

**Argument resolution:** The integer argument is resolved through `VarAlias.resolveInt()`, so it can be a literal number or a variable name.

**Input validation:** Values outside the range 0-2 are rejected with a warning log. Only changes the camera if the target is different from the current perspective.

**Difference from CyclePerspectiveAlias:** `SetPerspectiveAlias` sets an absolute perspective, while `CyclePerspectiveAlias` cycles through FPS → TPS → TPS2 → FPS on each invocation.

**No screen suppression:** Works on any screen (it modifies camera settings, not game input).

## See Also

| Item | Description |
|------|-------------|
| [CyclePerspectiveAlias](../CyclePerspectiveAlias.java/CyclePerspectiveAlias.md) | Cycle through perspectives (FPS→TPS→TPS2→FPS) |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Base class for integer-arg aliases |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system used for argument resolution |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
