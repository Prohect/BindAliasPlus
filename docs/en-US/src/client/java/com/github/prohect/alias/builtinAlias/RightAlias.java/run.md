# run method (src/client/java/com/github/prohect/alias/builtinAlias/RightAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                           |
| ------ | -------- | --------------------------------------------------------------------- |
| `args` | `String` | `"0"` to release the key, `"1"` to press it. Parsed by `parseArgs()`. |

## Remarks

Simulates a right movement key press or release by calling `KeyMapping.setDown(flag)` and incrementing `clickCount` on press.

Algorithm:

1. Calls `parseArgs(args)` to set the inherited `flag` field (`true` for `"1"`, `false` for `"0"`).
2. If `Alias.isUnderTextInputScreen()` is true and `flag` is true (press event), returns early to avoid interfering with text input.
3. Obtains `Minecraft.getInstance().options.keyRight`.
4. Calls `rightKey.setDown(flag)` to set the key state.
5. If `flag` is true, increments `rightKey.clickCount` to ensure the game registers the click.

Side effects: modifies `keyRight.setDown` and `keyRight.clickCount` on the game's `KeyMapping`.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"builtinRight"` matches an `AliasRecord`.

Error handling: `parseArgs()` logs a warning for invalid args (not `"0"` or `"1"`) via `BindAliasPlusClient.LOGGER`.

## See Also

| Item                                                                                         | Description                               |
| -------------------------------------------------------------------------------------------- | ----------------------------------------- |
| [RightAlias](RightAlias.md)                                                                  | Owning class                              |
| [BuiltinAliasWithBooleanArgs.parseArgs](../../BuiltinAliasWithBooleanArgs.java/parseArgs.md) | Parses `"0"`/`"1"` into `flag`            |
| [Alias.isUnderTextInputScreen](../../Alias.java/isUnderTextInputScreen.md)                   | Guard against interfering with text input |
| [LeftAlias.run](../LeftAlias.java/run.md)                                                    | Same pattern for `keyLeft`                |
| [ForwardAlias.run](../ForwardAlias.java/run.md)                                              | Same pattern for `keyUp`                  |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
