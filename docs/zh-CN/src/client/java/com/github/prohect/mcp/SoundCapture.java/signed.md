# signed 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
private static String signed(int v)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `v` | `int` | 要格式化的整数值 |

## 返回值

非负数前缀 `"+"`、负数前缀 `"-"` 的值。零返回 `"+0"`。

## 备注

方位字符串中偏航角/俯仰角的恒带符号整数格式化（例如 `"yaw-40"`、`"pitch+20"`）。显式符号使输出解析无歧义。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [directionOf](directionOf.md) | 调用方 |
