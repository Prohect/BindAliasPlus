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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
