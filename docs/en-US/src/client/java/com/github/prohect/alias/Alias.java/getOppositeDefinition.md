# getOppositeDefinition method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static String getOppositeDefinition(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | An alias chain string containing `+`/`-` prefixed definitions |

## Return value

A new alias chain string where every `+` prefix has been flipped to `-` and vice versa. Definitions without a `+`/`-` prefix are omitted from the output.

## Remarks

Used by switch-alias locking mechanisms. When a lock blocks a `+action`, the lock system generates the opposite definition (`-action`) and runs it to release the held key.

The method delegates to `getDefinitions(args)` to split the chain, then iterates each definition: if it starts with `+`, replaces with `-`; if it starts with `-`, replaces with `+`. Results are joined with `divider4AliasDefinition` (space).

Double-quoted blocks in the args are respected by the underlying `getDefinitions()` call.

## See Also

| Item | Description |
|------|-------------|
| [getDefinitions](getDefinitions.md) | Splits the chain into individual definitions |
| [LockAlias](builtinAlias/LockAlias.java/LockAlias.md) | Uses this method to generate release definitions |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
