# SetPerspectiveAlias (src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SetPerspectiveAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.SetPerspectiveAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to set the camera perspective to a specific mode. Registered as `builtinSetPerspective`.

**Purpose**: Directly sets the camera type rather than cycling. Values: 0 = first person, 1 = third person back, 2 = third person front. Also updates the camera entity when switching between first and third person.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Interacts with `Minecraft.options` and `Minecraft.setCameraEntity()`.

## See Also

| Item                                                                                                 | Description                    |
| ---------------------------------------------------------------------------------------------------- | ------------------------------ |
| [CyclePerspectiveAlias](../CyclePerspectiveAlias.java/CyclePerspectiveAlias.md)                      | Cycles to the next perspective |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Parent class                   |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
