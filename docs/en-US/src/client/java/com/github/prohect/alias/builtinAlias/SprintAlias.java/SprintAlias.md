# SprintAlias (src/client/java/com/github/prohect/alias/builtinAlias/SprintAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SprintAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SprintAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that simulates pressing or releasing the sprint key (`keySprint`).

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()` into `Alias.aliasesWithArgs`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread via the key-binding dispatch pipeline.

Key collaborators: extends [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) which provides `parseArgs()` for `"0"`/`"1"` parsing and `flag` field. Uses `Minecraft.getInstance().options.keySprint` to drive the game's sprint key binding.

## See Also

| Item                                                                                                 | Description                                      |
| ---------------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Parent class providing boolean arg parsing       |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md)                      | Grandparent class for registration               |
| [Alias](../../Alias.java/Alias.md)                                                                   | Core interface; `isUnderTextInputScreen()` guard |
| [AttackAlias](../AttackAlias.java/AttackAlias.md)                                                    | Same pattern for `keyAttack`                     |
| [SneakAlias](../SneakAlias.java/SneakAlias.md)                                                       | Same pattern for `keyShift`                      |
| [run](run.md)                                                                                        | The `run` method that dispatches the key event   |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
