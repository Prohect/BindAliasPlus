# src

## 目录

| 名称 | 说明 |
|------|-------------|
| [client/](client/README.md) | Fabric 仅客户端源码 —— mixin、别名、按键绑定、用于 AI 代理控制的 MCP HTTP 服务器，以及客户端侧模组入口点 |
| [main/](main/README.md) | 客户端和服务器共享的公共源码 —— 模组初始化（`BindAlias`）、`MOD_ID` 常量和共享配置 |

## 推荐阅读顺序

1. **[main/](main/README.md)** —— 从共享模组入口点开始，了解 `MOD_ID` 和基本初始化
2. **[client/](client/README.md)** —— 模组的主体：别名注册、按键绑定基础设施、命令注册、CFG 自动加载和 MCP 服务器

*Documented for Commit: [7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3](https://github.com/Prohect/BindAlias/tree/7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3)*
