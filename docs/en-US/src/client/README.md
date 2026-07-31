# client

The **client-only** source set. All code here runs exclusively on the Fabric client — never on a dedicated server. This is where the alias system, key-binding infrastructure, MCP HTTP server, and Fabric mixins live.

## Contents

| Name | Description |
|------|-------------|
| [java/](java/README.md) | Java source root — the `com.github.prohect` package tree |

## Key subsystems

| Subsystem | Package | Description |
|-----------|---------|-------------|
| **Alias engine** | `alias/` | Interface hierarchy (`Alias`, `AliasWithArgs`, `AliasWithoutArgs`) and concrete builtin aliases — the core command-macro system |
| **Key bindings** | `prohect/` | `BindAliasKeyBinding`, `KeyPressed`, and the central `BindAliasClient` orchestrator that connects keys to aliases |
| **MCP server** | `mcp/` | Localhost HTTP JSON-RPC server for AI agent control — game state snapshots, screenshots, recipe book, and sound capture |
| **Mixins** | `mixin/` | Injections into vanilla Minecraft classes for tick driving, key routing, screen tracking, movement, and silent mode |
| **Utilities** | `util/` | Helpers — lock management, screen-type checks, and other shared convenience methods |

## Entry point

**`BindAliasClient.onInitializeClient()`** is the single client initialization point. It registers ~60 builtin aliases, 7 client commands, lifecycle event handlers, and starts the MCP HTTP server.

*Documented for Commit: [7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3](https://github.com/Prohect/BindAlias/tree/7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3)*
