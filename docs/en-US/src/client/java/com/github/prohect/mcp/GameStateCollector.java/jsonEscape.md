# jsonEscape method (src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## Syntax

```java
static String jsonEscape(String s)
```

## Return value

The input string escaped per JSON rules and wrapped in double quotes, or `"null"` if the input is `null`.

## Remarks

Shared JSON string escaping utility. Escapes backslashes, double quotes, newlines, carriage returns, and tabs. Wraps the result in double quotes so callers can append it directly as a JSON string value. Null input produces the literal `"null"` (for JSON null). Used by nearly every method in this class and by `RecipeBookHelper.recipesJson`.

## See Also

| Item | Description |
|------|-------------|
| [RecipeBookHelper.recipesJson](RecipeBookHelper.java/recipesJson.md) | External caller |
