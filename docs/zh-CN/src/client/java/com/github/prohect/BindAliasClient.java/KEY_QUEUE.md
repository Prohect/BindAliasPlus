# KEY_QUEUE 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static final java.util.ArrayDeque<com.github.prohect.KeyPressed> KEY_QUEUE
```

## 备注

待处理按键事件的 FIFO 队列。写入者：`KeyBoardMixin`（键盘按下/松开）和 `MouseMixin`（鼠标按键按下/松开）。读取者：`MinecraftClientMixin`（每刻排空队列，并通过查找 `BINDING_PLUS` 分发事件）。断开连接时被清空。只能在游戏线程上访问。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
