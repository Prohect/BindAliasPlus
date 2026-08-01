# register 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
public static void register()
```

## 备注

通过 `MinecraftClient.getInstance().getSoundManager().addListener(INSTANCE)` 在客户端 `SoundManager` 上注册单例 `SoundCapture` 实例。可安全地多次调用——`SoundManager` 按监听者身份去重。在模组初始化期间由 `BindAliasClient.onInitializeClient()` 调用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onPlaySound](onPlaySound.md) | 为每个声音调用的回调 |
| [SoundCapture](SoundCapture.md) | 类文档 |
