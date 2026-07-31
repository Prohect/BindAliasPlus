# CFG_VARIABLES field (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Set tracking which general variable names were loaded from the configuration file.

## Syntax

```java
public static final java.util.Set<java.lang.String> CFG_VARIABLES
```

## Remarks

**Purpose:** Tracks the names of general variables (`GENERAL_VARIABLES` entries) that were created during CFG autoload. Used by `UnloadCFGVarsAlias` to identify which variables to remove, and by `UnloadUserVarsAlias` to identify which variables to PRESERVE (everything NOT in this set is considered "user-created" and removed).

**Writer:** `VarAlias.run(String, boolean)` — when `fromAutoload` is true and the source is not a `cN` source, adds the variable name to this set.

**Readers:**
- `UnloadCFGVarsAlias.run()` — iterates this set to remove CFG-loaded variables.
- `UnloadUserVarsAlias.run()` — checks this set to exclude CFG-loaded variables from removal.
- `UnloadCFGAllAlias.run()` — reads `size()` for counting before unload.

**Thread safety:** Accessed only from the game thread.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
