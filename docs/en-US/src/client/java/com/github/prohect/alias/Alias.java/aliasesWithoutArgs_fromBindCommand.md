# aliasesWithoutArgs_fromBindCommand field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final java.util.HashMap<java.lang.String, com.github.prohect.alias.AliasWithoutArgs<?>> aliasesWithoutArgs_fromBindCommand
```

## Remarks

Registry for aliases created via the `/alias bind` command.

Kept separate from the main registries so that bind-command aliases can be managed
independently (e.g., listed or removed specifically by the bind subcommand).
Not looked up during normal alias dispatch — used only by the `/alias bind`
command handler.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
