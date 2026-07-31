# isUnderTextInputScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isUnderTextInputScreen()
```

## Remarks

Checks whether the current screen is one that accepts text input.

Returns `true` when the current screen is an instance of:

- `ChatScreen` — the chat input overlay
- `CommandBlockEditScreen` — command block editor
- `SignEditScreen` — sign editor
- `BookEditScreen` — book and quill editor

Used by callers to decide whether to suppress key-press events that would
interfere with typing.

## Return value

`true` if a text-input screen is currently open.

## See Also

| Item                                    | Description                          |
| --------------------------------------- | ------------------------------------ |
| [getCurrentScreen](getCurrentScreen.md) | Provides the current screen instance |
| [isUnderAnyScreen](isUnderAnyScreen.md) | Broader check for any open screen    |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
