# equals 方法（src/client/java/com/github/prohect/alias/AliasRecord.java）

## 语法

```java
public final boolean equals(Object o)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `o` | `Object` | 要比较是否相等的对象 |

## 返回值

如果 `o` 是具有相同 `aliasName` 和 `args` 值的 `AliasRecord`，则返回 `true`，否则返回 `false`。

## 备注

由 Java `record` 实现自动生成。当且仅当两个 `AliasRecord` 实例的 `aliasName` 和 `args` 组件都相等（通过 `Objects.equals`）时，它们才相等。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [hashCode](hashCode.md) | 基于相同组件的哈希，与 `equals` 一致 |
| [AliasRecord](AliasRecord.md) | 所属的 record 类型 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
