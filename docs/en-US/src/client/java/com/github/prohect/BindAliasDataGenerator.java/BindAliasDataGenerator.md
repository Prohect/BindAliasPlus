# BindAliasDataGenerator (src/client/java/com/github/prohect/BindAliasDataGenerator.java)

## Syntax

```java
public class com.github.prohect.BindAliasDataGenerator implements net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
```

## Static Initializer

_None._

## Remarks

A Fabric `DataGeneratorEntrypoint` implementation that serves as the data generation entry point for the BindAlias mod. The `onInitializeDataGenerator` method is currently empty — no data generation assets (recipes, loot tables, tags, etc.) are produced by this mod.

This class exists as a structural placeholder. If the mod later needs to generate data (e.g., recipe advancements), this is where Fabric would call into it via the `fabric-datagen` module.

## See Also

| Item | Description |
|------|-------------|
| [onInitializeDataGenerator](onInitializeDataGenerator.md) | The Fabric data-gen entry method |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

