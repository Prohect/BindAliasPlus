# isVariable method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Checks whether a given string refers to an existing stored variable.

## Syntax

```java
public static boolean isVariable(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| input | String | The name to check |

## Remarks

**Algorithm:** If input is null or empty, return false. Otherwise, check `GENERAL_VARIABLES.containsKey(input.trim())`.

**Return value:** `true` if the trimmed input is a key in `GENERAL_VARIABLES`.

**Note:** This only checks `GENERAL_VARIABLES`, not `CONTAINER_SLOT_VARIABLES`. A variable that was created with a `cN` source exists in both maps, so checking `GENERAL_VARIABLES` is sufficient to determine if it exists. However, the container-slot semantics are not reflected by this check — it only tells you if a value is stored.

## See Also

| Item | Description |
|------|-------------|
| [resolveValue](resolveValue.md) | Resolve to actual value |
| [GENERAL_VARIABLES](GENERAL_VARIABLES.md) | The storage map checked |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
