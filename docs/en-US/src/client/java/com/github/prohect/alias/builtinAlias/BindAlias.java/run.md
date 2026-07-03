# run method (src/client/java/com/github/prohect/alias/builtinAlias/BindAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                      |
| ------ | -------- | ------------------------------------------------ |
| `args` | `String` | The bind definition string (key and alias name). |

## Remarks

Constructs and sends a `/bind` command to the server for keybinding creation.

**Algorithm**:

1. Build the command string: `"bind" + divider4AliasDefinition + args`, normalizing custom definition dividers.
2. If player is null, log warning and return.
3. Send via `player.connection.sendCommand(line)`.

**Side effects**: Sends a chat command packet to the server, which creates or updates a keybinding.

**Callers**: Invoked by the alias dispatch system.

## See Also

| Item                                          | Description           |
| --------------------------------------------- | --------------------- |
| [UnbindAlias.run](../UnbindAlias.java/run.md) | `/unbind` counterpart |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
