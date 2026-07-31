# CyclePerspectiveAlias (src/client/java/com/github/prohect/alias/builtinAlias/CyclePerspectiveAlias.java)

Builtin one-shot alias that cycles the camera perspective through FPS → TPS (third-person back) → TPS2 (third-person front). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"cyclePerspective"`. This is a one-shot alias (no args) — each invocation advances the camera one step.

The implementation uses `CameraType.cycle()` to advance to the next perspective in the enum order (FIRST_PERSON → THIRD_PERSON_BACK → THIRD_PERSON_FRONT → FIRST_PERSON...).

When switching between first-person and third-person, the camera entity is updated:
- Switching **to** first-person: `mc.setCameraEntity(mc.getCameraEntity())` — reuses the existing camera entity
- Switching **to** third-person: `mc.setCameraEntity(null)` — sets the camera entity to null so the game renders from behind/front

If `Minecraft.options` is null (should never happen in normal operation), a warning is logged and the alias returns immediately.

## See Also

| Item | Description |
|------|-------------|
| [SetPerspectiveAlias](../SetPerspectiveAlias.java/SetPerspectiveAlias.md) | Sets a specific perspective (FPS, TPS, TPS2) |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Base class for no-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
