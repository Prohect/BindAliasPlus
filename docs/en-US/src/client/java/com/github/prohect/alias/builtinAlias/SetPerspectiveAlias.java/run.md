# run method (src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java)

Parses the integer argument (0-2) and sets the camera perspective.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SetPerspectiveAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Integer 0-2: 0=FPS, 1=TPS (third-person back), 2=TPS2 (third-person front) |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — resolves `flag` (int) using `VarAlias.resolveInt()`.
2. Validate `flag` is in range [0, 2]. If out of range, log warning and return.
3. Get current perspective (`options.getPerspective()`).
4. Get target perspective (`Perspective.values()[flag]`).
5. If current != target:
   - Call `options.setPerspective(targetPerspective)`.
   - If the change crosses between first-person and third-person, update camera entity via `mc.setCameraEntity(...)`.

**Return value:** `this` (fluent return).

**Side effects:**
- Changes the camera perspective (visible immediately).
- Updates the camera entity when crossing FPS↔TPS boundary.

**Error handling:**
- Options null: logs warning, returns.
- Invalid range (not 0-2): logs warning with valid range hint, returns.

**No screen suppression:** Works on any screen.

## See Also

| Item | Description |
|------|-------------|
| [SetPerspectiveAlias](SetPerspectiveAlias.md) | Class overview |
| [CyclePerspectiveAlias](../CyclePerspectiveAlias.java/run.md) | Cycle through perspectives |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
