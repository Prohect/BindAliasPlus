# flag field (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public boolean flag
```

Stores the current boolean state of the alias: `true` when the key is pressed / held, `false` when released.

## Remarks

Set by `parseArgs(args)` — `"1"` → `true`, `"0"` → `false`. Read by concrete subclass `run()` methods to determine the action (inject press or release into vanilla key mapping). Also read by `reapplyToGameKeyMapping()` to decide whether to re-sync after screen transitions.

Default value is `false` (field default for `boolean`).

## See Also

| Item | Description |
|------|-------------|
| [parseArgs](parseArgs.md) | Sets this field from args string |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | Uses this field to re-sync held keys |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
