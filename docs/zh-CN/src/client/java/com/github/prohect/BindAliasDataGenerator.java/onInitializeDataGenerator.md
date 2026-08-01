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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
