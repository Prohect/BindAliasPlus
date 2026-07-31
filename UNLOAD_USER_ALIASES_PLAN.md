# Unload User-Defined Aliases/Vars/Binds — Implementation Plan

## Motivation

The existing `unloadCFG*` series removes items loaded from the config file. There is no equivalent to remove items created by the user/agent at runtime (via `/alias`, `/bind`, `/var` commands, or via `runAlias` tool calls that invoke `alias\`, `bind\`, `var\`).

New `unloadUser*` aliases fill this gap, enabling the agent to clean up its own runtime definitions without touching CFG-loaded or builtin items.

## Existing Metadata

| Item                     | Registry                            | CFG origin marker                  |
| ------------------------ | ----------------------------------- | ---------------------------------- |
| Aliases                  | `Alias.aliasesWithoutArgs`          | `UserAlias.isFromCFG()`            |
| Variables                | `VarAlias.GENERAL_VARIABLES`        | `VarAlias.CFG_VARIABLES` set       |
| Container_slot_variables | `VarAlias.CONTAINER_SLOT_VARIABLES` | `CFG_CONTAINER_SLOT_VARIABLES` set |
| Binds                    | `BindAliasClient.BINDING_PLUS`      | `KeyBindingPlus.fromCFG()`         |
| Predefined (builtin)     | Various registries                  | `UserAlias.isPredefined()`         |

**Classification logic**:

|                            | `fromAutoload` | `predefined` | Category                                 |
| -------------------------- | -------------- | ------------ | ---------------------------------------- |
| `+attack`, `-attack`, etc. | true           | true         | **Builtin** (never unloaded)             |
| CFG-loaded user aliases    | true           | false        | **CFG** (unloaded by `unloadCFG*`)       |
| Runtime-defined aliases    | false          | false        | **User** (unloaded by new `unloadUser*`) |

For variables: in `CFG_VARIABLES` = CFG; not in set = user.
For Container_slot_variables: in `CFG_CONTAINER_SLOT_VARIABLES` = CFG; not in set = user.

For binds: `fromCFG() == true` = CFG; `false` = user.

## New Aliases

### `unloadUserAliases`

Removes all `UserAlias` instances where `isFromCFG() == false && isPredefined() == false`.

```java
public class UnloadUserAliasesAlias extends BuiltinAliasWithoutArgs<...> {
    public UnloadUserAliasesAlias() { super("unloadUserAliases"); }

    @Override
    public UnloadUserAliasesAlias run(String args) {
        ...
        return this;
    }
}
```

### `unloadUserVars`

Removes all variables NOT in `CFG_VARIABLES`.

```java
public class UnloadUserVarsAlias extends BuiltinAliasWithoutArgs<...> {
    public UnloadUserVarsAlias() { super("unloadUserVars"); }

    @Override
    public UnloadUserVarsAlias run(String args) {
        ...
        return this;
    }
}
```

### `unloadUserBinds`

Removes all binds where `fromCFG() == false`.

Also cleans up associated aliases from `Alias.aliasesWithoutArgs_fromBindCommand`.

```java
public class UnloadUserBindsAlias extends BuiltinAliasWithoutArgs<...> {
    public UnloadUserBindsAlias() { super("unloadUserBinds"); }

    @Override
    public UnloadUserBindsAlias run(String args) {
        ...
        return this;
    }
}
```

### `unloadUserAll`

Convenience alias that calls `unloadUserAliases` + `unloadUserBinds` + `unloadUserVars` with silent mode.

```java
public class UnloadUserAllAlias extends BuiltinAliasWithoutArgs<...> {
    public UnloadUserAllAlias() { super("unloadUserAll"); }

    @Override
    public UnloadUserAllAlias run(String args) {
        ...
        return this;
    }
}
```

## Registration

In `BindAliasClient.onInitializeClient()`, add:

```java
new UnloadUserAliasesAlias().putToAliasesWithoutArgs();
new UnloadUserVarsAlias().putToAliasesWithoutArgs();
new UnloadUserBindsAlias().putToAliasesWithoutArgs();
new UnloadUserAllAlias().putToAliasesWithoutArgs();
```

## MCP Tool Descriptions

Update `mcp_server.js` ALIAS_RULES and RUNALIAS_DESCRIPTION to include the new aliases.

## Files to Create

| File                                                     | Description                      |
| -------------------------------------------------------- | -------------------------------- |
| `src/.../alias/builtinAlias/UnloadUserAliasesAlias.java` | Unload runtime-defined aliases   |
| `src/.../alias/builtinAlias/UnloadUserVarsAlias.java`    | Unload runtime-defined variables |
| `src/.../alias/builtinAlias/UnloadUserBindsAlias.java`   | Unload runtime-defined binds     |
| `src/.../alias/builtinAlias/UnloadUserAllAlias.java`     | Convenience: all three           |

## Summary Table

| Unload operation        | CFG items  | User items | Builtin items |
| ----------------------- | ---------- | ---------- | ------------- |
| `unloadCFGAliases`      | ✅ removed | ❌ kept    | ❌ kept       |
| `unloadCFGVars`         | ✅ removed | ❌ kept    | n/a           |
| `unloadCFGBinds`        | ✅ removed | ❌ kept    | n/a           |
| `unloadCFGAll`          | ✅ removed | ❌ kept    | ❌ kept       |
| **`unloadUserAliases`** | ❌ kept    | ✅ removed | ❌ kept       |
| **`unloadUserVars`**    | ❌ kept    | ✅ removed | n/a           |
| **`unloadUserBinds`**   | ❌ kept    | ✅ removed | n/a           |
| **`unloadUserAll`**     | ❌ kept    | ✅ removed | ❌ kept       |
