# src

The BindAliasPlus source tree. **`client/` is where all real logic lives** — aliases, keybindings, and mixins that hook into Minecraft's input system. `main/` is a minimal server-side skeleton (just `MOD_ID` and a no-op initializer). **Start with `client/`**; `main/` can be skipped unless you need to understand mod registration boilerplate.

## Contents

| Name    | Description                                                                   |
| ------- | ----------------------------------------------------------------------------- |
| client/ | **All client-side code**: aliases, keybindings, mixins — the entire mod logic |
| main/   | Server-side skeleton: MOD_ID constant and a no-op initializer                 |

_Documented for Commit: [559546bace0e8a52a434e97e41e60e3a76c78989](https://github.com/Prohect/BindAliasPlus/tree/559546bace0e8a52a434e97e41e60e3a76c78989)_
