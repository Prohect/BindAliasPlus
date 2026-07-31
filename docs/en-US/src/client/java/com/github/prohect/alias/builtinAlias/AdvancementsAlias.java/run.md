# run method (src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java)

Presses or releases the advancements key (L) to open the advancements screen.

## Syntax

```java
public AdvancementsAlias run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | `String` | `"1"` (press / `+advancements`) or `"0"` (release / `-advancements`) |

## Remarks

- Uses `keyAdvancements` from game options — works regardless of what key the user has bound.
- Calls `key.setDown(flag)` and increments `clickCount` on press.
- The screen opens on release via `consumeClick()`. Use `+advancements` → `wait\N` → `-advancements` to control timing.

## See Also

| Item | Description |
|------|-------------|
| [AdvancementsAlias](AdvancementsAlias.md) | Class documentation |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
