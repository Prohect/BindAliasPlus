# BindAlias

主模组初始化器（公共源码集，客户端和服务器共享）。实现 `ModInitializer` —— 记录启动日志并设置 `MOD_ID` 常量和日志器。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [MOD_ID](MOD_ID.md) | `String`（static，`"bind-alias"`） | 模组的唯一标识符字符串 |
| [LOGGER](LOGGER.md) | `Logger`（static） | 模组的 SLF4J 日志器，名为 `"bind-alias"` |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onInitialize](onInitialize.md) | `void onInitialize()` | Fabric `ModInitializer` 入口点 —— 记录启动日志 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAliasClient](../../client/java/com/github/prohect/BindAliasClient.java/README.md) | 完成模组大部分设置的客户端侧初始化器 |
