# onInitializeDataGenerator method (src/client/java/com/github/prohect/BindAliasPlusDataGenerator.java)

## Syntax

```java
public void onInitializeDataGenerator(net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator)
```

## Parameters

| Name                  | Type                  | Description                                             |
| --------------------- | --------------------- | ------------------------------------------------------- |
| `fabricDataGenerator` | `FabricDataGenerator` | The data generator instance provided by Fabric. Unused. |

## Remarks

No-op implementation. Called by Fabric Loader during data generation
(`runDatagen` Gradle task). The method body is empty — no data packs
or recipes are generated.

## See Also

| Item                                                        | Description  |
| ----------------------------------------------------------- | ------------ |
| [BindAliasPlusDataGenerator](BindAliasPlusDataGenerator.md) | Owning class |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
