# run method (src/client/java/com/github/prohect/alias/builtinAlias/ForwardAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                           |
| ------ | -------- | --------------------------------------------------------------------- |
| `args` | `String` | `"0"` to release the key, `"1"` to press it. Parsed by `parseArgs()`. |

## Remarks

Simulates a forward movement key press or release by calling `KeyMapping.setDown(flag)` and incrementing `clickCount` on press.

Algorithm:

1. Calls `parseArgs(args)` to set the inherited `flag` field (`true` for `"1"`, `false` for `"0"`).
2. If `Alias.isUnderTextInputScreen()` is true and `flag` is true (press event), returns early to avoid interfering with text input.
3. Obtains `Minecraft.getInstance().options.keyUp`.
4. Calls `forwardKey.setDown(flag)` to set the key state.
5. If `flag` is true, increments `forwardKey.clickCount` to ensure the game registers the click.

Side effects: modifies `keyUp.setDown` and `keyUp.clickCount` on the game's `KeyMapping`.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"builtinForward"` matches an `AliasRecord`.

Error handling: `parseArgs()` logs a warning for invalid args (not `"0"` or `"1"`) via `BindAliasClient.LOGGER`.

## See Also

| Item                                                                                         | Description                               |
| -------------------------------------------------------------------------------------------- | ----------------------------------------- |
| [ForwardAlias](ForwardAlias.md)                                                              | Owning class                              |
| [BuiltinAliasWithBooleanArgs.parseArgs](../../BuiltinAliasWithBooleanArgs.java/parseArgs.md) | Parses `"0"`/`"1"` into `flag`            |
| [Alias.isUnderTextInputScreen](../../Alias.java/isUnderTextInputScreen.md)                   | Guard against interfering with text input |
| [AttackAlias.run](../AttackAlias.java/run.md)                                                | Same pattern for `keyAttack`              |
| [BackAlias.run](../BackAlias.java/run.md)                                                    | Same pattern for `keyDown`                |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
