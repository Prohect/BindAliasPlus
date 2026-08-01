# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/CyclePerspectiveAlias.java）

循环切换相机视角：FPS → TPS → TPS2 → FPS。

## 语法

```java
public com.github.prohect.alias.builtinAlias.CyclePerspectiveAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 未使用（一次性别名，忽略） |

## 备注

1. 获取 `MinecraftClient.getInstance().options`。如果为 null，记录警告并返回（防御性检查——不应发生）。
2. 通过 `options.getPerspective()` 获取当前 `Perspective`。
3. 通过 `currentPerspective.cycle()` 循环到下一个类型——枚举顺序为 FIRST_PERSON → THIRD_PERSON_BACK → THIRD_PERSON_FRONT → FIRST_PERSON...
4. 调用 `options.setPerspective(nextPerspective)`。
5. 如果切换在第一人称和第三人称之间跨越：
   - **到第一人称：** `mc.setCameraEntity(mc.getCameraEntity())` — 复用现有相机实体
   - **到第三人称：** `mc.setCameraEntity(null)` — 清除相机实体，游戏从背面/正面渲染

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetPerspectiveAlias.run()](../SetPerspectiveAlias.java/run.md) | 直接设置特定视角 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
