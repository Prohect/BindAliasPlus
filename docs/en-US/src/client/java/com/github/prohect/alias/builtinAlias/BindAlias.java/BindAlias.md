# BindAlias (src/client/java/com/github/prohect/alias/builtinAlias/BindAlias.java)

Builtin alias that sends a `/bind` command to the server to create a key binding for an alias. Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.BindAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.BindAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"bind"`. This alias delegates key-binding creation to the server-side command system. Instead of binding keys locally, it sends a `/bind` command via `player.connection.sendCommand()`.

The args format is: `bind\<aliasName> <key>`. For example: `bind\+attack mouse.left` binds the left mouse button to the `+attack` alias.

The implementation reconstructs the command line by prefixing `"bind"` + the alias definition divider, then normalizing any arg dividers (`\`) in the args to the proper alias definition divider character. This ensures the server receives a consistent format regardless of how the chain separators were written.

If the player is null (not connected to a server), a warning is logged.

## See Also

| Item | Description |
|------|-------------|
| [AliasAlias](../AliasAlias.java/AliasAlias.md) | Sends `/alias` commands to define/redefine aliases |
| [BindAliasKeyBinding](../../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | Local key binding representation |
| [Alias.divider4AliasDefinition](../../Alias.java/divider4AliasDefinition.md) | Separator character |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
