# AliasAlias (src/client/java/com/github/prohect/alias/builtinAlias/AliasAlias.java)

Builtin alias that defines or redefines a user alias at runtime via the server-side `/alias` command. Extends `BuiltinAliasWithStringArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.AliasAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.AliasAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"alias"`. This alias delegates alias definition to the server-side command system. Instead of creating the alias locally, it sends a `/alias` command to the server via `player.connection.sendCommand()`.

The args format is: `alias\<name> <definition>`. For example:
- `alias\turnDown setPitch\90` defines an alias named `turnDown` that sets pitch to 90
- `alias\switchAlias swapSlot\19 +use wait\1 -use swapSlot\19` defines a multi-alias chain

The implementation reconstructs the command line by prefixing `"alias"` + the alias divider character, then replacing any occurrences of the arg divider (`\`) with the proper alias definition divider. This normalizes the separator characters so the server receives a consistent format.

If the player is null (not connected to a server), a warning is logged.

## See Also

| Item | Description |
|------|-------------|
| [BindAlias](../BindAlias.java/BindAlias.md) | Sends `/bind` command to server |
| [UserAlias](../../UserAlias.java/UserAlias.md) | Local representation of a user-defined alias |
| [Alias.divider4AliasDefinition](../../Alias.java/divider4AliasDefinition.md) | Separator character for alias definitions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
