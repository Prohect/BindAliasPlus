# run method (src/client/java/com/github/prohect/alias/builtinAlias/CyclePerspectiveAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description |
| ------ | -------- | ----------- |
| `args` | `String` | Ignored.    |

## Remarks

Cycles the camera perspective to the next mode using `CameraType.cycle()`.

**Algorithm**:

1. Guard against null `options`.
2. Get current `CameraType` and call `currentPerspective.cycle()` to get the next.
3. Call `options.setCameraType(nextPerspective)`.
4. If switching between first and third person, update `minecraftClient.setCameraEntity()`.

**Side effects**: Changes the camera perspective (client-side only).

**Callers**: Invoked by the alias dispatch system.

## See Also

| Item                                                          | Description         |
| ------------------------------------------------------------- | ------------------- |
| [SetPerspectiveAlias.run](../SetPerspectiveAlias.java/run.md) | Set a specific mode |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
