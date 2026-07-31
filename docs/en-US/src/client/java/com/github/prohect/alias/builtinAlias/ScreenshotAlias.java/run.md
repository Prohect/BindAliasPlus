# run method (src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java)

Captures a screenshot when the alias is activated (`+screenshot`).

## Syntax

```java
public ScreenshotAlias run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | `String` | `"1"` (press / `+screenshot`) or `"0"` (release / `-screenshot`) |

## Remarks

- Guarded against text-input screens — no screenshot while typing.
- Guarded against null player (no screenshot before joining a world).
- Calls `Screenshot.grab()` which saves a PNG to the game directory with a timestamped name and displays a system chat message with the filename.

## See Also

| Item | Description |
|------|-------------|
| [ScreenshotAlias](ScreenshotAlias.md) | Class documentation |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
