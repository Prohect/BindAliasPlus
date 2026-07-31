# parseArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public void parseArgs(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | Must be `"0"` (release / off) or `"1"` (press / on) |

## Remarks

Parses the args string and sets the `flag` field:

- `"0"` → `flag = false` (release)
- `"1"` → `flag = true` (press)
- Any other value → logs a warning via `BindAliasClient.LOGGER` and sets `flag = false`

This method is called at the beginning of each concrete subclass's `run()` method. For example, `AttackAlias.run("1")` calls `parseArgs("1")`, which sets `flag = true`, then `AttackAlias` injects the press into vanilla's attack key mapping.

## See Also

| Item | Description |
|------|-------------|
| [flag](flag.md) | The field set by this method |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | Calls `run("1")` if `flag` is still true |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
