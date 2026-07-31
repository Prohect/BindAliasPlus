# divider4AliasArgs field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final char divider4AliasArgs = '\\'
```

The character that separates an alias name from its arguments and individual arguments from each other within a single alias invocation. Value is backslash (`\`).

## Remarks

Used by `getDefinitionSplits()` to split a definition like `"slot\3"` into `["slot", "3"]`, or `"swapSlot\1\c2"` into `["swapSlot", "1", "c2"]`. Also used when `UserAlias` reconstructs deferred chains: args are re-joined with this divider.

The backslash was chosen because it is a safe, non-alphanumeric character unlikely to appear in Minecraft command arguments or chat messages outside the mod's own syntax.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
