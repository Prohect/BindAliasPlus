# run method (src/client/java/com/github/prohect/alias/builtinAlias/AliasAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                   |
| ------ | -------- | ------------------------------------------------------------- |
| `args` | `String` | The alias definition string in `aliasName\definition` format. |

## Remarks

Constructs and sends an `/alias` command to the server for alias creation.

**Algorithm**:

1. Build the command string: `"alias" + divider4AliasDefinition + args`, normalizing custom definition dividers to the standard one.
2. If player is null, log warning and return.
3. Send via `player.connection.sendCommand(line)`.

**Side effects**: Sends a chat command packet to the server, which will process it as if the player typed `/alias definition`. This creates or updates a UserAlias.

**Callers**: Invoked by the alias dispatch system.

## See Also

| Item                                      | Description         |
| ----------------------------------------- | ------------------- |
| [BindAlias.run](../BindAlias.java/run.md) | `/bind` counterpart |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
