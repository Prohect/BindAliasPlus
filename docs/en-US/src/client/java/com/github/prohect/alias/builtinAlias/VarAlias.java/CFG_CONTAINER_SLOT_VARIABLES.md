# CFG_CONTAINER_SLOT_VARIABLES field (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Set tracking which container slot variable names were loaded from the configuration file.

## Syntax

```java
public static final java.util.Set<java.lang.String> CFG_CONTAINER_SLOT_VARIABLES
```

## Remarks

**Purpose:** Tracks the names of container slot variables (`CONTAINER_SLOT_VARIABLES` entries) that were created during CFG autoload. Used by `UnloadUserVarsAlias` to identify which container slot variables to PRESERVE during cleanup.

**Writer:** `VarAlias.run(String, boolean)` — when `fromAutoload` is true and the source is a `cN` source, adds the variable name to this set.

**Reader:** `UnloadUserVarsAlias.run()` — checks this set to exclude CFG-loaded container slot variables from removal. Also used by `UnloadUserAllAlias.run()` for counting.

**Note:** Unlike `CFG_VARIABLES`, this set is NOT used by `UnloadCFGVarsAlias` — that alias only cleans `GENERAL_VARIABLES` and `CFG_VARIABLES`, ignoring container slot variables entirely. This asymmetry means `UnloadUserVarsAlias` is the only way to clean container slot variables.

**Thread safety:** Accessed only from the game thread.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
