# BindAlias

## Fields

| Name     | Type     | Description                                                                                         |
| -------- | -------- | --------------------------------------------------------------------------------------------------- |
| `MOD_ID` | `String` | Namespace identifier for the mod: `"bind-alias"`. Used as logger name and config path segment. |
| `LOGGER` | `Logger` | SLF4J logger for this mod. Name is `MOD_ID`.                                                        |

## Methods

| Name           | Signature             | Description                                                                                               |
| -------------- | --------------------- | --------------------------------------------------------------------------------------------------------- |
| `onInitialize` | `void onInitialize()` | Mod initialization entry point. Currently logs a greeting; all real logic lives in `BindAliasClient`. |

## See Also

| Item                                                                                                              | Description                                 |
| ----------------------------------------------------------------------------------------------------------------- | ------------------------------------------- |
| [BindAliasClient](../../../../client/java/com/github/prohect/BindAliasClient.java/BindAliasClient.md) | Client-side entry point with all real logic |
| [ModInitializer](https://fabricmc.net/wiki/documentation:fabric_mod_initializer)                                  | Fabric mod initialization interface         |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
