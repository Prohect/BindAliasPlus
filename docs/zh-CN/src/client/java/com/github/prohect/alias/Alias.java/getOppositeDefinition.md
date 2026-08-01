# getOppositeDefinition 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static String getOppositeDefinition(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 包含 `+`/`-` 前缀定义的别名链字符串 |

## 返回值

一个新的别名链字符串，其中每个 `+` 前缀都翻转为 `-`，反之亦然。没有 `+`/`-` 前缀的定义会从输出中省略。

## 备注

用于开关类别名的锁定机制。当锁定阻止某个 `+action` 时，锁定系统会生成相反定义（`-action`）并运行它以松开被按住的按键。

该方法委托 `getDefinitions(args)` 拆分链，然后遍历每条定义：如果以 `+` 开头则替换为 `-`；如果以 `-` 开头则替换为 `+`。结果用 `divider4AliasDefinition`（空格）拼接。

参数中的双引号包裹的块会被底层的 `getDefinitions()` 调用正确处理。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getDefinitions](getDefinitions.md) | 将链拆分为各条定义 |
| [LockAlias](builtinAlias/LockAlias.java/LockAlias.md) | 使用此方法生成松开定义 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
