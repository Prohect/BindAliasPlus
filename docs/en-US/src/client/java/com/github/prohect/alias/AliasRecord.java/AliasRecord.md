# AliasRecord (src/client/java/com/github/prohect/alias/AliasRecord.java)

## Syntax

```java
public record AliasRecord(@NotNull String args, @NotNull String aliasName)
```

Immutable record holding a single alias invocation: the alias name and its arguments string. Used by `UserAlias` to store the parsed token queue (`ArrayDeque<AliasRecord> aliases`) and by `WaitAlias` to capture the deferred remainder of an alias chain.

## Remarks

This is a standard Java `record`, giving automatic `equals`, `hashCode`, `toString`, and component accessors. The components are:

- **`aliasName`** — the name to look up in the global alias maps (`aliasesWithoutArgs`, `aliasesWithArgs`, etc.).
- **`args`** — the arguments string (already split by `Alias.divider4AliasArgs`), or empty string for no-arg aliases. Only builtin `AliasWithArgs` instances use this value; for `AliasWithoutArgs` it is always `""`.

When `UserAlias.decodeArgs2Alias()` parses a definition like `slot\3`, it produces `new AliasRecord("3", "slot")`. For a no-arg like `esc`, it produces `new AliasRecord("", "esc")`.

## See Also

| Item | Description |
|------|-------------|
| [UserAlias](UserAlias.java/UserAlias.md) | Decodes definition strings into `AliasRecord` queue |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | Stores deferred aliases as `AliasRecord` for delayed execution |
| [Alias](Alias.java/Alias.md) | The root interface — defines the arg divider used when reconstructing chains |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
