# run method (src/client/java/com/github/prohect/alias/builtinAlias/CyclePerspectiveAlias.java)

Cycles the camera perspective: FPS → TPS → TPS2 → FPS.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | Not used (one-shot alias, ignored) |

## Remarks

1. Retrieves `MinecraftClient.getInstance().options`. If null, logs a warning and returns (defensive check — should never happen).
2. Gets the current `Perspective` via `options.getPerspective()`.
3. Cycles to the next type via `currentPerspective.cycle()` — the enum order is FIRST_PERSON → THIRD_PERSON_BACK → THIRD_PERSON_FRONT → FIRST_PERSON...
4. Calls `options.setPerspective(nextPerspective)`.
5. If the transition crosses between first-person and third-person:
   - **To first-person:** `mc.setCameraEntity(mc.getCameraEntity())` — reuses existing camera entity
   - **To third-person:** `mc.setCameraEntity(null)` — clears camera entity, game renders from behind/front

## See Also

| Item | Description |
|------|-------------|
| [SetPerspectiveAlias.run()](../SetPerspectiveAlias.java/run.md) | Sets a specific perspective directly |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
