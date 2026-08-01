# aliasNameOnKeyPressed 方法（src/client/java/com/github/prohect/BindAliasKeyBinding.java）

## 语法

```java
public java.lang.String aliasNameOnKeyPressed()
```

## 参数

_无。_

## 备注

按下按键时调用的别名名称的记录访问器。这是规范构造函数的第一个参数。若未定义按下动作，可为空字符串。该别名必须是 `AliasWithoutArgs` —— 它将通过 `UserAlias.run("")` 以无参数方式调用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [aliasNameOnKeyReleased](aliasNameOnKeyReleased.md) | 松开按键时调用的别名 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
