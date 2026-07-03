# onInitialize method (src/main/java/com/github/prohect/BindAliasPlus.java)

## Syntax

```java
public void onInitialize()
```

## Remarks

Called by Fabric Loader once Minecraft reaches the mod-load-ready state.

Currently logs `"Hello Fabric world!"` via `LOGGER.info()`. All real mod logic
(alias registration, config loading, command registration) resides in the
client-side entry point [BindAliasPlusClient.onInitializeClient](../../../../client/java/com/github/prohect/BindAliasPlusClient.java/onInitializeClient.md).

## See Also

| Item                                                                                                                                | Description                                      |
| ----------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| [BindAliasPlusClient.onInitializeClient](../../../../client/java/com/github/prohect/BindAliasPlusClient.java/onInitializeClient.md) | Client-side counterpart with real initialization |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
