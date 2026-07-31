# onInitialize method (src/main/java/com/github/prohect/BindAlias.java)

## Syntax

```java
public void onInitialize()
```

## Remarks

Called by Fabric Loader once Minecraft reaches the mod-load-ready state.

Currently logs `"Hello Fabric world!"` via `LOGGER.info()`. All real mod logic
(alias registration, config loading, command registration) resides in the
client-side entry point [BindAliasClient.onInitializeClient](../../../../client/java/com/github/prohect/BindAliasClient.java/onInitializeClient.md).

## See Also

| Item                                                                                                                                | Description                                      |
| ----------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| [BindAliasClient.onInitializeClient](../../../../client/java/com/github/prohect/BindAliasClient.java/onInitializeClient.md) | Client-side counterpart with real initialization |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
