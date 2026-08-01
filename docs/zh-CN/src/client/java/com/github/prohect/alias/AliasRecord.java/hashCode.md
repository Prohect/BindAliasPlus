# hashCode 方法（src/client/java/com/github/prohect/alias/AliasRecord.java）

## 语法

```java
public final int hashCode()
```

## 返回值

由 `aliasName` 和 `args` 两个组件计算出的哈希码。

## 备注

由 Java `record` 实现自动生成。与 `equals` 一致——两个相等的 record 产生相同的哈希。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [equals](equals.md) | 基于相同组件的相等性 |
| [AliasRecord](AliasRecord.md) | 所属的 record 类型 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
