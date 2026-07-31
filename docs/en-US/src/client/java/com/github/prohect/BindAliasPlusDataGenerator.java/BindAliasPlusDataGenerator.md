# BindAliasDataGenerator (src/client/java/com/github/prohect/BindAliasDataGenerator.java)

## Syntax

```java
public class com.github.prohect.BindAliasDataGenerator implements net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
```

## Static Initializer

_None._

## Remarks

Fabric data generation entry point. Currently a no-op placeholder —
the mod does not (yet) generate any data assets via the datagen system.

Instantiated by Fabric Loader when running the `runDatagen` task.
No state; stateless utility class.

## See Also

| Item                                                                | Description                            |
| ------------------------------------------------------------------- | -------------------------------------- |
| [BindAlias](../BindAliasClient.java/BindAliasClient.md) | Client mod initializer with real logic |
| [onInitializeDataGenerator](onInitializeDataGenerator.md)           | The single entry-point method          |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
