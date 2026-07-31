# divider4AliasDefinition field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final char divider4AliasDefinition = ' '
```

The character that separates individual alias invocations within an alias chain. Default is space (`' '`).

## Remarks

Used by `getDefinitions()` to split a chain like `"+attack slot\1 wait\5 -attack"` into individual definitions. `BuiltinAliasWithStringArgs` overrides this with `';'` because its aliases (e.g. `say`, `alias`, `sendCommand`) may contain spaces in their arguments.

When `UserAlias` reconstructs deferred chains after a `WaitAlias`, it joins remaining items with this divider.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
