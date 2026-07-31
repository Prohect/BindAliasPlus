# builtinAliasName field (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public final @NotNull String builtinAliasName
```

The name used for registration and lookup. Set by the constructor and never changes. Annotated `@NotNull`.

## Remarks

Every concrete builtin alias passes its name to `super(name)` in its constructor. This name is then used by `putToAliasesWithArgs()` (no-arg overload) as the key for the global `Alias.aliasesWithArgs` map.

For example, `SlotAlias` passes `"slot"`, so `new SlotAlias().putToAliasesWithArgs()` registers it as `aliasesWithArgs["slot"]`.

The `final` modifier ensures the name cannot be changed after construction — each instance represents exactly one alias identity.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | Uses this field as the registration key |
| [BuiltinAliasWithoutArgs.builtinAliasName](BuiltinAliasWithoutArgs.java/builtinAliasName.md) | Same field in the without-args base class |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
