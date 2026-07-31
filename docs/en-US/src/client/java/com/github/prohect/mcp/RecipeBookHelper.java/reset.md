# reset method (src/client/java/com/github/prohect/mcp/RecipeBookHelper.java)

## Syntax

```java
public static void reset()
```

## Remarks

Clears the `reportedDisplayIds` set and resets the `baselineJoinTick` to `Long.MIN_VALUE`, forcing the next `onlyNew` call to start fresh (return all recipes). Called on world join/disconnect to prevent stale recipe tracking across worlds.

## See Also

| Item | Description |
|------|-------------|
| [onlyNew](onlyNew.md) | The method whose state is reset |
