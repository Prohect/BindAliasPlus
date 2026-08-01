# onInitialize 方法（src/main/java/com/github/prohect/BindAlias.java）

## 语法

```java
@Override
public void onInitialize()
```

## 备注

Fabric `ModInitializer` 入口点。在游戏达到模组加载就绪状态后、资源完全加载前，于模组初始化期间被调用一次。通过模组的日志器记录 `"Hello Fabric world!"`。初始化的大部分工作 —— 别名注册、按键绑定设置、CFG 加载和 MCP 服务器启动 —— 发生在客户端源集的 `BindAliasClient.onInitializeClient()` 中。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAliasClient.onInitializeClient](../../client/java/com/github/prohect/BindAliasClient.java/onInitializeClient.md) | 注册别名和按键绑定的客户端侧初始化 |
