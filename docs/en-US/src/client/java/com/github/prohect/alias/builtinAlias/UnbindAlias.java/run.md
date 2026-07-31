# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnbindAlias.java)

Constructs and sends an `unbind` command to the server.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnbindAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Arguments for the unbind command (key name, etc.) |

## Remarks

**Algorithm:**

1. Construct the command line: `"unbind" + divider4AliasDefinition + processedArgs`.
   - `divider4AliasDefinition` is a space character (`' '`).
   - args are preprocessed: backslashes replaced with spaces via regex.
2. If `mc.player` is null, log a warning and return.
3. Send the command: `player.connection.sendCommand(line)`.

**Return value:** `this` (fluent return).

**Side effects:** Sends an unbind command to the server's command handler. The server processes the command to remove the specified keybinding.

**No screen suppression:** Works on any screen.

## See Also

| Item | Description |
|------|-------------|
| [UnbindAlias](UnbindAlias.md) | Class overview |
| [SendCommandAlias](../SendCommandAlias.java/run.md) | Underlying command dispatch |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
