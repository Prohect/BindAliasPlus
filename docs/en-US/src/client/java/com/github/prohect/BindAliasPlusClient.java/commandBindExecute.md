# commandBindExecute method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private int commandBindExecute(java.lang.String, java.lang.String, boolean)
```

## Parameters

| Name           | Type      | Description                                                       |
| -------------- | --------- | ----------------------------------------------------------------- |
| `keyName`      | `String`  | Key string (e.g., `"f"`, `"mouse1"`). Parsed by `parseKey()`.     |
| `args`         | `String`  | Alias definition string (a chain of alias definitions with args). |
| `fromAutoload` | `boolean` | `true` if called from `loadCFG()`.                                |

## Remarks

Binds a key to an alias definition string. First attempts a direct alias-by-name
binding; if that doesn't succeed, creates an anonymous intermediate alias.

Algorithm:

1. Try `commandBindByAliasNameExecute(keyName, args, fromAutoload)` — if it
   returns `1`, this is just a named-alias binding; return `1`.
2. Generate two random 16-character alias names (for press and release).
3. Parse the key via `parseKey(keyName)`. Return `2` if key is unknown.
4. Create a `UserAlias` from `args` and store in
   `Alias.aliasesWithoutArgs_fromBindCommand` under the first random name.
5. Compute the opposite definition via `Alias.getOppositeDefinition(args)`.
   If non-blank, create another `UserAlias` under the second random name.
6. Put a `KeyBindingPlus` entry into `BINDING_PLUS` mapping the key to the
   generated alias names. The release alias name is set only if the opposite
   definition exists (otherwise empty string).

Return values:

- `1` — bound to an existing named alias (step 1 succeeded).
- `2` — unknown key name.
- `3` — bound via generated intermediate aliases (steps 2-6).

The three-arg overload delegates with `fromAutoload = false`.

## See Also

| Item                                                                        | Description                          |
| --------------------------------------------------------------------------- | ------------------------------------ |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md)           | Tried first for named-alias binding  |
| [parseKey](parseKey.md)                                                     | Key name parser                      |
| [Alias.getOppositeDefinition](../alias/Alias.java/getOppositeDefinition.md) | Computes release alias               |
| [KeyBindingPlus](../KeyBindingPlus.java/KeyBindingPlus.md)                  | The mapping stored in `BINDING_PLUS` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
