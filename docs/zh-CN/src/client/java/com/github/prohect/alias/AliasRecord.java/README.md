# AliasRecord

表示单次别名调用的不可变 record `(args, aliasName)`。存储在延迟执行队列中。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [aliasName](aliasName.md) | `String` | 在全局别名映射中查找的名称 |
| [args](args.md) | `String` | 参数字符串（无参数别名为 `""`） |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [equals](equals.md) | `boolean equals(Object)` | 逐组件相等性比较 |
| [hashCode](hashCode.md) | `int hashCode()` | 逐组件哈希 |
| [toString](toString.md) | `String toString()` | 调试用字符串表示 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UserAlias](UserAlias.java/UserAlias.md) | 将定义字符串解码为 `AliasRecord` 队列 |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | 将延迟的别名存储为 `AliasRecord` 以便按刻延迟执行 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
