# CyclePerspectiveAlias (src/client/java/com/github/prohect/alias/builtinAlias/CyclePerspectiveAlias.java)

循环切换相机视角（FPS → TPS（第三人称背面）→ TPS2（第三人称正面））的内置一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"cyclePerspective"`。这是一个一次性别名（无参数）——每次调用使相机前进一步。

实现使用 `Perspective.next()` 前进到枚举顺序中的下一个视角（FIRST_PERSON → THIRD_PERSON_BACK → THIRD_PERSON_FRONT → FIRST_PERSON...）。

在第一人称和第三人称之间切换时，通过 `gameRenderer.onCameraEntitySet()` 更新相机实体：
- 切换到**第一人称**：传入 `mc.getCameraEntity()` — 复用现有相机实体
- 切换到**第三人称**：传入 `null` — 游戏从背面/正面渲染

如果 `MinecraftClient.getInstance().options` 为 null（正常操作中不应发生），则记录警告并立即返回。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetPerspectiveAlias](../SetPerspectiveAlias.java/SetPerspectiveAlias.md) | 设置特定视角（FPS、TPS、TPS2） |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 无参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
