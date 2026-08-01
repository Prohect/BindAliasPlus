# reset 方法（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public static void reset()
```

## 备注

清空 `reportedDisplayIds` 集合并把 `baselineJoinTick` 重置为 `Long.MIN_VALUE`，强制下一次 `onlyNew` 调用从头开始（返回全部配方）。在加入世界/断开连接时调用，防止配方跟踪跨世界过期。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onlyNew](onlyNew.md) | 状态被重置的方法 |
