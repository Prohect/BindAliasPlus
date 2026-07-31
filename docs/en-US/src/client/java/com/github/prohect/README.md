# prohect

Root package for the BindAlias mod. Contains the client entry point (`BindAliasClient`), key-binding infrastructure, the alias execution engine, MCP HTTP server, mixins, and utilities.

## Recommended reading order

| Order | Item | Reason |
|-------|------|--------|
| 1 | [alias](alias/README.md) | Core abstraction — all alias types, registration maps, parsing |
| 2 | [BindAliasClient.java](BindAliasClient.java/README.md) | Client init — wires aliases, key bindings, screen blacklist, CFG load |
| 3 | [BindAliasKeyBinding.java](BindAliasKeyBinding.java/README.md) | Key binding wrapper — how key presses become alias invocations |
| 4 | [KeyPressed.java](KeyPressed.java/README.md) | Key event record — queued key events for the tick loop |
| 5 | [mcp](mcp/README.md) | MCP HTTP JSON-RPC server — external control of aliases |
| 6 | [mixin](mixin/README.md) | Mixins — tick driver, key routing, screen tracking, packet hooks |
| 7 | [util](util/README.md) | Utilities — color names, slot helpers, screen-related helpers |

## Contents

| Name | Description |
|------|-------------|
| [alias](alias/README.md) | Alias type hierarchy, registration maps, parsing utilities |
| [BindAliasClient.java](BindAliasClient.java/README.md) | Client mod initializer — registers all builtin aliases and key bindings |
| [BindAliasDataGenerator.java](BindAliasDataGenerator.java/README.md) | Data generation entry point for the mod |
| [BindAliasKeyBinding.java](BindAliasKeyBinding.java/README.md) | Custom key binding — maps a key to an alias name for execution |
| [KeyPressed.java](KeyPressed.java/README.md) | Record for queued key-press events (key code, action, modifiers) |
| [mcp](mcp/README.md) | MCP HTTP server, state tracking, screenshot, recipe helpers |
| [mixin](mixin/README.md) | Mixins injecting into Minecraft client classes |
| [util](util/README.md) | Utility classes (color, screen helpers, slot helpers) |

## See Also

| Item | Description |
|------|-------------|
| [com/github/prohect/alias](alias/README.md) | The alias execution engine — all user-visible commands |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
