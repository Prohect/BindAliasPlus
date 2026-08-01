# BindAliasDataGenerator（src/client/java/com/github/prohect/BindAliasDataGenerator.java）

## 语法

```java
public class com.github.prohect.BindAliasDataGenerator implements net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
```

## 静态初始化

_无。_

## 备注

一个 Fabric `DataGeneratorEntrypoint` 实现，充当 BindAlias 模组的数据生成入口点。`onInitializeDataGenerator` 方法目前为空 —— 该模组不产生任何数据生成资源（配方、战利品表、标签等）。

此类作为结构性占位存在。若模组将来需要生成数据（如配方进度），Fabric 会通过 `fabric-datagen` 模块在此调用它。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onInitializeDataGenerator](onInitializeDataGenerator.md) | Fabric 数据生成入口方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
