# MOD_ID 字段（src/main/java/com/github/prohect/BindAlias.java）

## 语法

```java
public static final String MOD_ID = "bind-alias"
```

## 备注

模组的唯一标识符字符串。用作日志器名称、CFG 文件名（`config/bind-alias.cfg`），并被 `fabric.mod.json` 引用以进行模组注册。遵循 Fabric 使用小写连字符名称的惯例。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LOGGER](LOGGER.md) | 使用此 ID 初始化的日志器 |
