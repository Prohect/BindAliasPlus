# divider4AliasDefinition field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final char divider4AliasDefinition
```

## Remarks

Delimiter character used to separate individual alias definitions within a definition
string. Defaults to `' '` (space).

Used by `getDefinitions()` to split a raw args string into a list of definitions.
Double-quoted blocks are respected — a space inside quotes is treated as literal
text, not a divider.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
