# isValidVarName method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Validates that a variable name is acceptable — must not start with a number.

## Syntax

```java
private boolean isValidVarName(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| varName | String | The proposed variable name |

## Remarks

**Validation rules:**
- Must not be null or empty.
- Must not start with a digit (0-9). Checked via regex `^[0-9].*`.

**Return value:** `true` if the name is valid, `false` otherwise.

**Why no leading digits:** This constraint prevents ambiguity between variable names and literal numbers. For example, `5` is a number, not a variable name. It also ensures that `resolveValue("5")` correctly treats it as a literal integer rather than looking it up as a variable.

**Callers:** Both `run()` overloads call this before storing a variable.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Calls this for validation |
| [resolveValue](resolveValue.md) | Number resolution (distinguishes names from numbers) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
