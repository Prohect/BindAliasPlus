# divider4AliasArgs field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final char divider4AliasArgs
```

## Remarks

Delimiter character used to separate the alias name from its arguments within a
single definition. Defaults to `'\\'` (backslash).

Used by `getDefinitionSplits()` to split a definition like `"attack\1"` into
`["attack", "1"]`. Double-quoted blocks are respected — a backslash inside quotes
is treated as literal text, not a divider.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
