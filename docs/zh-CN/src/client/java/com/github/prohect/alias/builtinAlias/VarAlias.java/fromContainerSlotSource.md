# fromContainerSlotSource 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

解析 `cN` 容器槽位源字符串并返回 1 基槽位编号。

## 语法

```java
private static int fromContainerSlotSource(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| source | String | 要解析的源字符串（例如 `"c1"`、`"c5"`、`"c12"`） |

## 备注

**算法：**

1. 去除源字符串的空白。
2. 如果长度 < 2 或第一个字符不是 `'c'`，返回 `CONTAINER_SLOT_PARSE_ERR`。
3. 将 'c' 之后的子串解析为整数。
4. 如果整数 >= 1，返回它。否则返回 `CONTAINER_SLOT_PARSE_ERR`。

**返回值：** 1 基容器槽位编号（>= 1），如果不是有效的 `cN` 字符串则返回 `CONTAINER_SLOT_PARSE_ERR`（`Integer.MIN_VALUE + 17`）。

**哨兵值：** 选择 `CONTAINER_SLOT_PARSE_ERR` 是为了难以猜测且不可能与真实槽位索引冲突，使其成为明确的"不是 cN 源"信号。

**为什么是 private：** 这是仅由 `VarAlias` 方法使用的内部辅助方法。外部代码使用 `SwapSlotAlias.parseSlotRef()`，它有自己的 `cN` 解析逻辑。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | 主要调用者 |
| [SwapSlotAlias](../SwapSlotAlias.java/parseSlotRef.md) | 交换操作的外部 cN 解析 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
