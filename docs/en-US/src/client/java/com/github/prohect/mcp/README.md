# mcp

MCP (Model Context Protocol) module — the HTTP JSON-RPC server and supporting infrastructure that exposes the external API surface for AI agent tool calls. Runs on `localhost` with a configurable port.

**Recommended reading order:** start with [`McpHttpServer`](McpHttpServer.java/README.md) (the central server), then [`StateTracker`](StateTracker.java/README.md) + [`GameStateCollector`](GameStateCollector.java/README.md) (the state-delivery pipeline), then [`GameChannels`](GameChannels.java/README.md) (the message hub), then the feeders ([`SoundCapture`](SoundCapture.java/README.md), [`RecipeBookHelper`](RecipeBookHelper.java/README.md)) and the screenshot pipeline ([`ScreenshotCapture`](ScreenshotCapture.java/README.md)).

## Contents

| Name | Description |
|------|-------------|
| [GameChannels.java](GameChannels.java/README.md) | Channel-based message hub with four named channels (chat, mod, sound, recipe) — thread-safe posting, coalescing for repeating sounds, and a Log4j appender for mod logs |
| [GameStateCollector.java](GameStateCollector.java/README.md) | Assembles the raw game-state snapshot (player position, health, inventory, screen info, etc.) into JSON fragments, plus container/hotbar slot-granularity diffing and shared formatting helpers |
| [McpHttpServer.java](McpHttpServer.java/README.md) | HTTP JSON-RPC server on localhost — seven endpoints for state, screenshot, alias execution, alias definition, CFG read/write, and recipe listing, with optional deferred nap responses |
| [RecipeBookHelper.java](RecipeBookHelper.java/README.md) | Read side of the client recipe book — lists unlocked recipes with live craftability, resolves queries by item ID or locale name, and provides diff bookkeeping for the `listRecipes` endpoint |
| [ScreenshotCapture.java](ScreenshotCapture.java/README.md) | Shared state for the screenshot capture pipeline — holds the one-shot future for in-memory PNG byte transfer from the `NativeImageMixin` to `McpHttpServer` |
| [SoundCapture.java](SoundCapture.java/README.md) | `SoundEventListener` on the client sound manager — feeds the `SOUND` channel with subtitle-audible sounds and precise directional info |
| [StateTracker.java](StateTracker.java/README.md) | Tracks the last state snapshot and assembles the MCP response envelope in a two-phase `begin`/`finish` pattern — diffs state at member and slot granularity, drains message channels |

