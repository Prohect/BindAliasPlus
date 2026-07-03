# divider4AliasDefinition field (src/client/java/com/github/prohect/alias/BuiltinAliasWithGreedyStringArgs.java)

## Syntax

```java
public static final char divider4AliasDefinition
```

## Remarks

Alternative definition divider used by greedy-string aliases. Value is `';'` (semicolon).

Shadows `Alias.divider4AliasDefinition` (which is space `' '`). This allows the
greedy string args to contain spaces — for example, a chat message alias can
receive the full message text including spaces as its argument string, while
still using semicolons to separate multiple definitions if needed.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
