# KeyPressed

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [key](key.md) | `InputConstants.Key` | 键盘按键或鼠标按键（永不为 null） |
| [pressed](pressed.md) | `boolean` | 按下为 `true`，松开为 `false` |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [equals](equals.md) | `boolean equals(Object)` | 记录相等性 —— 两个组件必须都匹配 |
| [hashCode](hashCode.md) | `int hashCode()` | 记录哈希 —— 由两个组件派生 |
| [toString](toString.md) | `String toString()` | 记录的字符串表示 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KEY_QUEUE](../BindAliasClient.java/KEY_QUEUE.md) | 存储这些事件的队列 |
| [BindAliasKeyBinding](../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | 消费按键事件时分发的绑定 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
