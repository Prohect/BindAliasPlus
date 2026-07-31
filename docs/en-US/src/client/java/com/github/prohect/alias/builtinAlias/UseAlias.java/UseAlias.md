# UseAlias (src/client/java/com/github/prohect/alias/builtinAlias/UseAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UseAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.UseAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to simulate pressing or releasing the use/place key (right-click). Registered as `builtinUse`.

**Purpose**: Programmatically controls the vanilla use key (`keyUse`). When pressed (`"1"`), sets `keyUse.setDown(true)` and increments `clickCount` for an immediate use action. When released (`"0"`), sets `keyUse.setDown(false)`.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

**Key collaborators**: Directly manipulates `Minecraft.getInstance().options.keyUse`.

## See Also

| Item                                                                            | Description  |
| ------------------------------------------------------------------------------- | ------------ |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
