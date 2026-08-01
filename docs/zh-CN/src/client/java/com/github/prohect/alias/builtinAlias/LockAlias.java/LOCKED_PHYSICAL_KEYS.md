# LOCKED_PHYSICAL_KEYS 字段（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

当前被阻止到达游戏的物理输入键的公共静态集合。

## 语法

```java
public static final java.util.Set<net.minecraft.client.util.InputUtil.Key> LOCKED_PHYSICAL_KEYS
```

## 备注

键盘和鼠标 mixin 检查此集合，以确定是否应抑制物理按键/鼠标事件。当某个按键在此集合中时，mixin 会在事件到达原版按键处理之前丢弃它。

**由以下方法填充：**
- `lockAction()` — 添加被锁定的原版 KeyBinding 的原始按键，以及指向该动作的任何模组绑定按键
- `lockAliasByName()` — 添加绑定到给定别名名称的所有物理按键
- `lockModBoundKeys()` — 添加 `BINDING_PLUS` 中别名指向被锁定动作的按键

**由以下方法清空：**
- `unlockAction()` — 移除保存的按键以及任何仅被独占锁定的模组绑定按键
- `unlockAliasByName()` — 移除与特定别名关联的按键
- `clearAllLocks()` — 断开连接时清空所有内容

**读取方（mixin）：**
- `KeyBoardMixin` — 在按键按下事件时检查此集合
- `MouseMixin` — 在鼠标按钮事件时检查此集合

线程安全：仅从游戏线程访问（别名执行和 mixin 注入点）。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
