# WaitAlias

延迟别名链执行的内置别名。用法：`wait\N`，其中 N 是刻数。`UserAlias` 使用双参数重载进行链延迟。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [tasksWaiting](tasksWaiting.md) | `ArrayList<WaitAliasRecord>` | 所有待处理的延迟别名任务的静态列表 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 已弃用的单参数：仅验证刻数 |
| [run](run.md) | `run(String args, String definition)` | 活跃的双参数：调度或立即执行定义 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAliasRecord](../WaitAliasRecord.java/README.md) | 延迟任务记录 |
| [UserAlias](../../UserAlias.java/README.md) | 调用双参数重载的链执行器 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/README.md) | wait 任务的刻驱动 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
