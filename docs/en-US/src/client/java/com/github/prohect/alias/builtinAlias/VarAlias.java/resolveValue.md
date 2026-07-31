# resolveValue method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Static resolver that returns a `Number` for a given input — either a literal number string or a variable name.

## Syntax

```java
public static java.lang.Number resolveValue(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| input | String | A variable name (e.g., `"mySlot"`) or a numeric string (e.g., `"5"`, `"3.14"`) |

## Remarks

**Algorithm (resolved in order):**

1. If input is null or empty, return null.
2. Trim the input.
3. Try `Integer.parseInt(trimmed)` — if successful, return the Integer.
4. Try `Double.parseDouble(trimmed)` — if successful, return the Double.
5. Look up in `GENERAL_VARIABLES.get(trimmed)` — if found, return the stored Number.
6. Return null (not a number and not a variable).

**Design rationale:** Literal numbers are checked FIRST before variable lookup. This ensures that a user who types `5` gets the value 5, not the value of a variable named "5" (which is actually impossible since variable names can't start with numbers, but the order still matters for names like strings that happen to also be parseable as numbers).

**Callers:** Used by `resolveInt()` and `resolveDouble()` convenience methods. Also called indirectly by all aliases that use `BuiltinAliasWithIntegerArgs.parseArgs()` or `BuiltinAliasWithDoubleArgs.parseArgs()`.

**Return value:** An `Integer`, `Double`, or null.

## See Also

| Item | Description |
|------|-------------|
| [resolveInt](resolveInt.md) | Convenience — returns int or null |
| [resolveDouble](resolveDouble.md) | Convenience — returns double or null |
| [isVariable](isVariable.md) | Check if a name exists in storage |
| [GENERAL_VARIABLES](GENERAL_VARIABLES.md) | The variable storage map |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
