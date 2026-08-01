# finish 方法（src/client/java/com/github/prohect/mcp/StateTracker.java）

## 语法

```java
public static String finish(String begun)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `begun` | `String` | `begin()` 产生的部分 envelope |

## 返回值

包含 channel 消息的完整 JSON envelope 字符串：`{"client_tick":N, "state":{...}, "chat":[...], ...}`。

## 备注

线程安全（调用内部同步的 `GameChannels.drain()`）。把排空出的 channel 消息追加到部分 envelope：插入 `"chat"`、`"mod"`、`"sound"` 与 `"recipe"` 数组，内含自上次 drain 以来的新消息。空 channel 省略。用 `}` 闭合 JSON 对象。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [begin](begin.md) | 第一阶段 |
| [GameChannels.drain](GameChannels.java/drain.md) | channel 消息排空 |
