# FreeCursorAlias (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

Agent-tooling alias that frees the mouse cursor from Minecraft's grab, allowing the OS cursor to move freely across screens during dev/test.

## Syntax

```java
public class FreeCursorAlias extends BuiltinAliasWithBooleanArgs<FreeCursorAlias>
```

## Static Initializer

_None._

## Remarks

Usage: `+freeCursor` frees the cursor, `-freeCursor` restores normal grab behavior.

Works by setting a static `freeCursor` flag that `MouseMixin.cancelGrabMouse()` checks at the HEAD of `grabMouse()` — if true, the grab is cancelled via `ci.cancel()`.

This is hidden from command suggestions since it's an agent tool, not something a human would normally invoke from the chat box.

## Requirements

- `MouseMixin.cancelGrabMouse()` must be registered in the mixin config.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Entry point |
| [freeCursor](freeCursor.md) | Static flag field |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
