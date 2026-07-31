# run method (src/client/java/com/github/prohect/alias/builtinAlias/BindAlias.java)

Sends a `/bind` command to the server to create a key binding for an alias.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.BindAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The alias name and key, separated by `\`: `aliasName\<key>` |

## Remarks

1. Constructs the command line: `"bind" + Alias.divider4AliasDefinition + normalizedArgs`.
2. Normalizes the args by replacing any occurrences of the arg divider (`\`) with the proper alias definition divider. This ensures consistent separator format.
3. Checks if `Minecraft.player` is not null. If null, logs a warning.
4. Sends the command via `player.connection.sendCommand(line)`.

The server processes the `/bind` command to create a persistent key binding that associates a physical key or mouse button with an alias name.

## See Also

| Item | Description |
|------|-------------|
| [AliasAlias.run()](../AliasAlias.java/run.md) | Same pattern for `/alias` command |
| [BindAliasKeyBinding](../../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | Local key binding representation |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
