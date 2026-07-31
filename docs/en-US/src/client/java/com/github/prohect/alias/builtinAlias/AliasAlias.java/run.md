# run method (src/client/java/com/github/prohect/alias/builtinAlias/AliasAlias.java)

Sends an `/alias` command to the server to define a user alias at runtime.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.AliasAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The alias name and definition, separated by `\`: `aliasName\<definition>` |

## Remarks

1. Constructs the command line: `"alias" + Alias.divider4AliasDefinition + normalizedArgs`.
2. Normalizes the args by replacing any occurrences of the arg divider (`\`) with the proper alias definition divider. This ensures the separators are consistent regardless of how the user typed the chain.
3. Checks if `Minecraft.player` is not null (must be connected to a server). If null, logs a warning and returns.
4. Sends the command via `player.connection.sendCommand(line)`.

**Example:** `alias\turnRight yaw\90` sends the server command `alias<sep>turnRight yaw<sep>90`, which the server processes to define a `turnRight` alias that rotates 90 degrees.

## See Also

| Item | Description |
|------|-------------|
| [BindAlias.run()](../BindAlias.java/run.md) | Same pattern for `/bind` command |
| [UserAlias](../../UserAlias.java/UserAlias.md) | Local representation of defined aliases |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
