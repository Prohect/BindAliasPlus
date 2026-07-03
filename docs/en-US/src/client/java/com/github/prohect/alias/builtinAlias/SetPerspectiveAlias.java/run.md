# run method (src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                      |
| ------ | -------- | -------------------------------------------------------------------------------- |
| `args` | `String` | An integer 0-2: 0 = first person, 1 = third person back, 2 = third person front. |

## Remarks

Sets the camera perspective to the specified mode. Only updates if the target differs from the current perspective.

**Algorithm**:

1. Parse `args` to set `flag` (inherited integer).
2. Guard against null `options`.
3. Validate `flag` is 0-2; log warning if out of range.
4. Get current `CameraType` and target from `CameraType.values()[flag]`.
5. If different, call `options.setCameraType(targetPerspective)`.
6. If switching between first and third person, update `minecraftClient.setCameraEntity()` accordingly.

**Side effects**: Changes the camera perspective (client-side only). Updates the camera entity for third-person rendering.

**Callers**: Invoked by the alias dispatch system.

## See Also

| Item                                                              | Description   |
| ----------------------------------------------------------------- | ------------- |
| [CyclePerspectiveAlias.run](../CyclePerspectiveAlias.java/run.md) | Cycle variant |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
