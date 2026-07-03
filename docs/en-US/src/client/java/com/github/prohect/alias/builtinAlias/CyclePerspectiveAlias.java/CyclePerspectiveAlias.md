# CyclePerspectiveAlias (src/client/java/com/github/prohect/alias/builtinAlias/CyclePerspectiveAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to cycle the camera perspective to the next mode (first person → third person back → third person front → first person...). Registered as `cyclePerspective`.

**Purpose**: Mirrors vanilla F5 behavior but usable from alias sequences. Calls `CameraType.cycle()` and updates the camera entity when switching between first and third person.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithoutArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

## See Also

| Item                                                                                     | Description                     |
| ---------------------------------------------------------------------------------------- | ------------------------------- |
| [SetPerspectiveAlias](../SetPerspectiveAlias.java/SetPerspectiveAlias.md)                | Set a specific perspective mode |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class                    |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
