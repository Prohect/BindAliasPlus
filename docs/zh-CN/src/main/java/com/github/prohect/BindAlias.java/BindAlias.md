# BindAlias（src/main/java/com/github/prohect/BindAlias.java）

## 语法

```java
public class BindAlias implements net.fabricmc.api.ModInitializer
```

## 静态初始化

_无。_

## 备注

主模组初始化器（公共源码集）。实现 `ModInitializer`，Fabric 在模组初始化期间调用它一次。将 `MOD_ID` 常量设置为 `"bind-alias"`，并以该名称创建模组的 SLF4J 日志器。`onInitialize()` 方法记录一条启动消息 —— 模组初始化的大部分工作（别名注册、按键绑定、MCP 服务器启动）发生在客户端源集的 `BindAliasClient.onInitializeClient()` 中。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAliasClient](../../client/java/com/github/prohect/BindAliasClient.java/README.md) | 注册所有别名、按键绑定并启动 MCP 服务器的客户端侧初始化器 |
| [MOD_ID](MOD_ID.md) | 模组的字符串 ID |
| [LOGGER](LOGGER.md) | 模组的日志器 |
| [onInitialize](onInitialize.md) | Fabric 入口点 |
