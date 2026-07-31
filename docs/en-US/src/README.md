# src

## Contents

| Name | Description |
|------|-------------|
| [client/](client/README.md) | Fabric client-only sources — mixins, aliases, key bindings, the MCP HTTP server for AI agent control, and the client-side mod entry point |
| [main/](main/README.md) | Common sources shared across client and server — mod init (`BindAlias`), `MOD_ID` constant, and shared configuration |

## Recommended reading order

1. **[main/](main/README.md)** — start with the shared mod entry point to understand `MOD_ID` and basic initialization
2. **[client/](client/README.md)** — the bulk of the mod: alias registration, key-binding infrastructure, command registration, CFG autoload, and the MCP server

*Documented for Commit: [7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3](https://github.com/Prohect/BindAlias/tree/7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3)*
