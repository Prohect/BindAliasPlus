# BindAlias

The main mod initializer (common source set, shared across client and server). Implements `ModInitializer` — logs startup and sets up the `MOD_ID` constant and logger.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [MOD_ID](MOD_ID.md) | `String` (static, `"bind-alias"`) | The mod's unique identifier string |
| [LOGGER](LOGGER.md) | `Logger` (static) | The mod's SLF4J logger, named `"bind-alias"` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [onInitialize](onInitialize.md) | `void onInitialize()` | Fabric `ModInitializer` entry point — logs startup |

## See Also

| Item | Description |
|------|-------------|
| [BindAliasClient](../../client/java/com/github/prohect/BindAliasClient.java/README.md) | The client-side initializer where most of the mod's setup happens |
