# prohect

The root package of BindAliasPlus. **`BindAliasPlusClient.java` is THE entry point** — it implements `ClientModInitializer`, registers all aliases and keybindings, and wires everything together (~43KB). The `alias/` sub-package contains the core alias interface hierarchy and all alias implementations, `mixin/` holds Minecraft injection points, and `util/` provides reflection-based helpers. `KeyBindingPlus.java` and `KeyPressed.java` are simple records that form the key→alias binding system. `BindAliasPlusDataGenerator.java` is minimal boilerplate.

**Reading order:** BindAliasPlusClient → alias/Alias.java → KeyBindingPlus → KeyPressed → alias/UserAlias → mixins.

## Contents

| Name                                                                         | Description                                                                                                        |
| ---------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| [alias](alias/README.md)                                                     | **Core alias interface hierarchy, all alias implementations, and the user-defined alias chain system**             |
| [BindAliasPlusClient.java](BindAliasPlusClient.java/README.md)               | **THE entry point** — `ClientModInitializer`, registers all aliases, keybindings, and networking callbacks (~43KB) |
| [BindAliasPlusDataGenerator.java](BindAliasPlusDataGenerator.java/README.md) | Minimal boilerplate data generator entry point                                                                     |
| [KeyBindingPlus.java](KeyBindingPlus.java/README.md)                         | Simple record linking a key combination to an alias name — the binding definition                                  |
| [KeyPressed.java](KeyPressed.java/README.md)                                 | Simple record representing a pressed key with modifiers — feeds into the KEY_QUEUE                                 |
| mixin/                                                                       | **Minecraft injection points** for keyboard, mouse, screen, and client tick hooks                                  |
| util/                                                                        | Reflection-based helpers (e.g., `McScreenHelper` for screen access)                                                |

_Documented for Commit: [5f57a834ca640636c88177748bafb5e9a7ce180a](https://github.com/Prohect/BindAliasPlus/tree/5f57a834ca640636c88177748bafb5e9a7ce180a)_
