# aliasesWithoutArgs_fromBindCommand field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs_fromBindCommand
```

Map of user aliases created specifically by the `bind` command. Keys are alias names assigned via `bind\key\aliasName`. These aliases are used by `BindAliasKeyBinding` to look up the alias to execute when a bound key is pressed.

## Remarks

Separate from `aliasesWithoutArgs` because the `bind` command creates aliases that should only be triggered by key events, not by name in alias chains. The `unbind` command removes entries from this map.

Populated by the `BindAlias` builtin alias. Read by `BindAliasKeyBinding.onKeyPressed()` to dispatch key presses to the corresponding user alias.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
