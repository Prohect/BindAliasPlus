# run method (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

Sets the `freeCursor` static flag to the parsed boolean argument.

## Syntax

```java
public FreeCursorAlias run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | `String` | `"1"` (enable / `+freeCursor`) or `"0"` (disable / `-freeCursor`) |

## Remarks

The flag is read by `MouseMixin.cancelGrabMouse()` each time Minecraft attempts to grab the cursor. When `true`, the grab is cancelled at the HEAD of `grabMouse()`, keeping the OS cursor free.

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias](FreeCursorAlias.md) | Class documentation |
| [freeCursor](freeCursor.md) | The static flag field |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAliasPlus/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
