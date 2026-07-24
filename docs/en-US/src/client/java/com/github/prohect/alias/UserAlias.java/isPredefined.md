# isPredefined method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public boolean isPredefined()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | Simple accessor — no parameters. |

## Remarks

Returns whether this `UserAlias` is a **predefined** (protected) alias. Predefined aliases are built into the mod at registration time via the 3-argument constructor and cannot be overwritten by user config or the `/alias` command.

Used by the alias management system to skip predefined aliases during unbind and reload operations. The `predefined` field is set once at construction and never changes.

## See Also

| Item | Description |
|------|-------------|
| [UserAlias](UserAlias.md) | Constructor that sets the `predefined` flag |
| [Alias.aliasesWithoutArgs](../Alias.java/Alias.md) | Registry where predefined aliases live |
| [UnbindAlias](../builtinAlias/UnbindAlias.java/UnbindAlias.md) | Checks this before removing an alias |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
