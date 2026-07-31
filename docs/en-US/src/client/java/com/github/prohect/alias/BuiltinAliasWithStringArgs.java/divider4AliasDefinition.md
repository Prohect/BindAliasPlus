# divider4AliasDefinition field (src/client/java/com/github/prohect/alias/BuiltinAliasWithStringArgs.java)

## Syntax

```java
public static final char divider4AliasDefinition = ';'
```

Overrides the default alias-definition divider. Uses `';'` (semicolon) instead of `' '` (space) because string-arg aliases may contain spaces in their arguments.

## Remarks

This field **shadows** (not overrides) `Alias.divider4AliasDefinition`. When `UserAlias` reconstructs deferred chains after a `WaitAlias`, it checks if the alias is an instance of `BuiltinAliasWithStringArgs` and, if so, uses this semicolon divider instead of the default space.

Concrete subclasses (`SayAlias`, `AliasAlias`, `BindAlias`, `UnbindAlias`, `SendCommandAlias`, `LogAlias`, `LocalSayAlias`, `ReapplyAlias`, `ReloadCFGAlias`, `RunAlias`, `ApplyRecipeAlias`) all benefit from this override because their string arguments naturally contain spaces (e.g. `say\"hello world"`).

In `UserAlias.run()`, when reconstructing deferred chains, aliases of type `AliasAlias`, `BindAlias`, or `UnbindAlias` get special treatment: their args' semicolons are converted back to spaces, and the alias is re-encoded with the `;` divider.

## See Also

| Item | Description |
|------|-------------|
| [Alias.divider4AliasDefinition](Alias.java/divider4AliasDefinition.md) | The default space divider, shadowed by this field |
| [UserAlias.run](UserAlias.java/run.md) | Where this divider is used for chain reconstruction |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
