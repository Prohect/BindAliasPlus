# isVariable 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

检查给定字符串是否引用已存储的变量。

## 语法

```java
public static boolean isVariable(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| input | String | 要检查的名称 |

## 备注

**算法：** 如果 input 为 null 或空，返回 false。否则，检查 `GENERAL_VARIABLES.containsKey(input.trim())`。

**返回值：** 去除空白后的 input 是 `GENERAL_VARIABLES` 中的键则返回 `true`。

**注意：** 这只会检查 `GENERAL_VARIABLES`，不检查 `CONTAINER_SLOT_VARIABLES`。以 `cN` 源创建的变量同时存在于两个映射中，因此检查 `GENERAL_VARIABLES` 足以确定它是否存在。但是，此检查不反映容器槽位语义——它只告诉你是否存储了值。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [resolveValue](resolveValue.md) | 解析为实际值 |
| [GENERAL_VARIABLES](GENERAL_VARIABLES.md) | 被检查的存储映射 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
