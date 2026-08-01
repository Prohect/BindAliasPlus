# DropAlias

原版丢弃物品按键绑定（keyDrop / Q 键）的开关别名。支持立即的首次丢弃、操作系统式按键重复延迟后的持续丢弃，以及对容器界面的特殊处理。覆写 `reapplyToGameKeyMapping()` 以避免界面切换后产生额外丢弃。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `INITIAL_DELAY_TICKS` | `static final int` | 持续丢弃开始前的延迟（3 刻，匹配操作系统按键重复间隔） |
| `ticksHeld` | `private long` | 自上次按下以来经过的刻数；松开时重置为 0 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `DropAlias run(String args)` | 按下/松开处理器：立即首次丢弃 + 容器界面路由 |
| [tickDrop](tickDrop.md) | `void tickDrop()` | 逐刻持续丢弃驱动器，由 `MinecraftClientMixin` 调用 |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | `void reapplyToGameKeyMapping()` | 界面切换后恢复按住状态且不产生额外丢弃 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 每个客户端刻调用 `tickDrop()` |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | 界面切换后调用 `reapplyToGameKeyMapping()` |
| [AttackAlias](../AttackAlias.java/AttackAlias.md) | 无持续逐刻驱动器的更简单开关别名 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
