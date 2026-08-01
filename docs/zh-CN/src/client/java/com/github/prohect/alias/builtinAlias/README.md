# builtinAlias

## 目录

| 名称 | 说明 |
|------|-------------|
| [AdvancementsAlias.java](AdvancementsAlias.java/README.md) | |
| [AliasAlias.java](AliasAlias.java/README.md) | |
| [ApplyRecipeAlias.java](ApplyRecipeAlias.java/README.md) | |
| [AttackAlias.java](AttackAlias.java/README.md) | |
| [BackAlias.java](BackAlias.java/README.md) | |
| [BindAlias.java](BindAlias.java/README.md) | |
| [CyclePerspectiveAlias.java](CyclePerspectiveAlias.java/README.md) | |
| [DebugOverlayAlias.java](DebugOverlayAlias.java/README.md) | |
| [DropAlias.java](DropAlias.java/README.md) | |
| [EscAlias.java](EscAlias.java/README.md) | |
| [ForwardAlias.java](ForwardAlias.java/README.md) | |
| [FreeCursorAlias.java](FreeCursorAlias.java/README.md) | |
| [JumpAlias.java](JumpAlias.java/README.md) | |
| [LeftAlias.java](LeftAlias.java/README.md) | |
| [LocalSayAlias.java](LocalSayAlias.java/README.md) | |
| [LockAlias.java](LockAlias.java/README.md) | |
| [LockAlias_OnLock.java](LockAlias_OnLock.java/README.md) | |
| [LockAlias_Unlock.java](LockAlias_Unlock.java/README.md) | |
| [LogAlias.java](LogAlias.java/README.md) | |
| [OpenInventoryAlias.java](OpenInventoryAlias.java/README.md) | 打开或关闭玩家物品栏界面的开关别名（+/−） |
| [PickItemAlias.java](PickItemAlias.java/README.md) | 一次性：触发原版选取方块行为（中键） |
| [PitchAlias.java](PitchAlias.java/README.md) | 双精度参数：相对俯仰角旋转 `pitch\deg` |
| [PlayerListAlias.java](PlayerListAlias.java/README.md) | 显示在线玩家浮层（Tab）的开关别名（+/−） |
| [ReapplyAlias.java](ReapplyAlias.java/README.md) | 字符串参数：界面切换后重新断言按住的按键 `reapply\action` |
| [ReloadCFGAlias.java](ReloadCFGAlias.java/README.md) | 一次性：在运行时重新加载配置文件 |
| [RightAlias.java](RightAlias.java/README.md) | 向右侧移移动（D 键）的开关别名（+/−） |
| [RunAliasAlias.java](RunAliasAlias.java/README.md) | 字符串参数：按名称执行已注册的别名 `builtinRunAlias\name` |
| [SayAlias.java](SayAlias.java/README.md) | 字符串参数：向服务器发送聊天消息 `say\text` |
| [ScreenshotAlias.java](ScreenshotAlias.java/README.md) | 开关别名（+）：通过原版 F2 代码路径截图 |
| [SendCommandAlias.java](SendCommandAlias.java/README.md) | 字符串参数：发送服务器命令（不带前导 `/`） |
| [SetPerspectiveAlias.java](SetPerspectiveAlias.java/README.md) | 整数参数：设置相机视角 0=FPS，1=TPS，2=TPS2 |
| [SetPitchAlias.java](SetPitchAlias.java/README.md) | 双精度参数：绝对俯仰角设置器 `setPitch\deg` |
| [SetYawAlias.java](SetYawAlias.java/README.md) | 双精度参数：绝对偏航角设置器 `setYaw\deg` |
| [ShutdownAlias.java](ShutdownAlias.java/README.md) | 一次性：干净地关闭游戏 `builtinShutdown` |
| [SilentAlias.java](SilentAlias.java/README.md) | 切换静默模式（抑制模组反馈）的开关别名（+/−） |
| [SlotAlias.java](SlotAlias.java/README.md) | 整数参数：选择快捷栏槽位 1-9 `slot\N` |
| [SneakAlias.java](SneakAlias.java/README.md) | 潜行/蹲下（Shift 键）的开关别名（+/−） |
| [SprintAlias.java](SprintAlias.java/README.md) | 疾跑（Ctrl 键，需要 +forward）的开关别名（+/−） |
| [SwapHandAlias.java](SwapHandAlias.java/README.md) | 一次性：通过数据包交换主手和副手物品 |
| [SwapSlotAlias.java](SwapSlotAlias.java/README.md) | 多参数：在任意两个物品栏/容器槽位之间交换物品 |
| [ToggleInventoryAlias.java](ToggleInventoryAlias.java/README.md) | 一次性：切换物品栏界面的打开/关闭 |
| [UnbindAlias.java](UnbindAlias.java/README.md) | 字符串参数：向服务器发送 unbind 命令 |
| [UnloadCFGAliasesAlias.java](UnloadCFGAliasesAlias.java/README.md) | 一次性：移除 CFG 加载的用户别名 |
| [UnloadCFGAllAlias.java](UnloadCFGAllAlias.java/README.md) | 一次性：移除所有 CFG 加载的条目（别名 + 绑定 + 变量） |
| [UnloadCFGBindsAlias.java](UnloadCFGBindsAlias.java/README.md) | 一次性：移除 CFG 加载的按键绑定 |
| [UnloadCFGVarsAlias.java](UnloadCFGVarsAlias.java/README.md) | 一次性：移除 CFG 加载的通用变量 |
| [UnloadUserAliasesAlias.java](UnloadUserAliasesAlias.java/README.md) | 一次性：移除运行时创建的用户别名 |
| [UnloadUserAllAlias.java](UnloadUserAllAlias.java/README.md) | 一次性：移除所有运行时创建的条目（别名 + 绑定 + 变量） |
| [UnloadUserBindsAlias.java](UnloadUserBindsAlias.java/README.md) | 一次性：移除运行时创建的按键绑定 |
| [UnloadUserVarsAlias.java](UnloadUserVarsAlias.java/README.md) | 一次性：移除运行时创建的变量（通用 + 容器） |
| [UseAlias.java](UseAlias.java/README.md) | 使用物品 / 右键交互的开关别名（+/−） |
| [VarAlias.java](VarAlias.java/README.md) | 多参数：存储/检查变量；其他别名的中央解析系统 |
| [WaitAlias.java](WaitAlias.java/README.md) | 整数参数：将别名链执行延迟 N 刻 `wait\N` |
| [WaitAliasRecord.java](WaitAliasRecord.java/README.md) | 保存倒计时刻数和定义字符串的延迟任务记录 |
| [YawAlias.java](YawAlias.java/README.md) | 双精度参数：相对偏航角旋转 `yaw\deg` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
