# onInitializeDataGenerator 方法（src/client/java/com/github/prohect/BindAliasDataGenerator.java）

## 语法

```java
public void onInitializeDataGenerator(net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `fabricDataGenerator` | `FabricDataGenerator` | Fabric 数据生成器实例 —— 未使用 |

## 备注

在数据生成时由 Fabric 的数据生成系统调用。目前方法体为空操作 —— 不注册任何数据生成包。存在的目的是满足 `DataGeneratorEntrypoint` 契约，使模组将来在需要时能够参与数据生成。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAliasDataGenerator](BindAliasDataGenerator.md) | 类概览 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
