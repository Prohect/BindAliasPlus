# aliasNameOnKeyReleased 方法（src/client/java/com/github/prohect/BindAliasKeyBinding.java）

## 语法

```java
public java.lang.String aliasNameOnKeyReleased()
```

## 参数

_无。_

## 备注

松开按键时调用的别名名称的记录访问器。这是规范构造函数的第二个参数。对于只有按下动作才重要的一次性动作（如 `esc` 仅在按下时运行），可为空字符串。该别名必须是 `AliasWithoutArgs`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [aliasNameOnKeyPressed](aliasNameOnKeyPressed.md) | 按下按键时调用的别名 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
