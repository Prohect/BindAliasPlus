# onInitializeDataGenerator method (src/client/java/com/github/prohect/BindAliasDataGenerator.java)

## Syntax

```java
public void onInitializeDataGenerator(net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `fabricDataGenerator` | `FabricDataGenerator` | The Fabric data generator instance — unused |

## Remarks

Called by Fabric's data generation system at data-gen time. Currently a no-op body — no data generation packs are registered. Exists to satisfy the `DataGeneratorEntrypoint` contract so the mod can participate in data generation if needed in the future.

## See Also

| Item | Description |
|------|-------------|
| [BindAliasDataGenerator](BindAliasDataGenerator.md) | The class overview |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
