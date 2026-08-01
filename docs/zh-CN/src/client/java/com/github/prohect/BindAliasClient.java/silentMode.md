# silentMode 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static boolean silentMode
```

## 备注

为 `true` 时，抑制聊天界面中所有别名反馈消息（模组的 `sendFeedback` 调用和 `log` 别名输出）。由 `+silent` / `-silent` 开关别名切换。在命令处理器中的每次 `context.getSource().sendFeedback(...)` 调用前都会被检查。断开连接时重置为 `false`。被 `ChatComponentMixin` 用于过滤聊天渲染。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
