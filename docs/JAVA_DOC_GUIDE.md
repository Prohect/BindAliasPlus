# Java Doc Drafting Guide

Extension of [docs/README.md](../README.md) — see that first for the general schema, path structure, and cross-reference rules. This file adds only Java-specific conventions.

## Source layout

```
src/client/java/com/github/prohect/   ← Fabric client-only sources (mixins, aliases, MCP)
src/main/java/com/github/prohect/     ← common sources (entry point, shared config)
```

Generated doc stubs mirror this under `docs/en-US/src/`. Run `<doc-generator-script>` after `<build-command>` to scaffold missing stubs.

## Class / Interface / Enum / Record (`<Type>.md`)

Fill **Remarks**: purpose, lifecycle (singleton? per-call?), thread safety, key collaborators.
Fill **See Also**: parent/implemented interfaces, subtypes, heavy users.

## Method (`<method>.md`)

Fill **Parameters** from the signature. Fill **Remarks**: algorithm (step-by-step), side effects (state, logging, network), callers, error handling. For non-void methods, describe the return value.

## Field (`<field>.md`)

Only public/protected fields get a stub. Fill **Remarks**: what it stores, who reads/writes it, thread safety, default value.

## Static initializer (`static-init.md`)

Only generated when the source has an explicit `static { }` block. Document what is initialized, why a static block instead of field initializers, and failure modes.

## README.md (per-source-file overview)

Fill the **Fields** table (all fields, type, one-line description). Fill the **Methods** table (all public/protected methods, condensed signature, one-line description). Group by category (lifecycle, command handlers, utilities, etc.). Fill **See Also** with related types.

## Mapping branches

This project targets multiple Minecraft versions with different mappings. When a method/class name differs between branches, note the mapping in the doc's Syntax block or Remarks:

- **Mojang** (26.x): `MultiPlayerGameMode`, `AbstractContainerMenu`, `Component`, `Minecraft`
- **Yarn** (1.21.x): `ClientPlayerInteractionManager`, `ScreenHandler`, `Text`, `MinecraftClient`

## Commit footer

Every doc file ends with a commit-SHA footer. Do not remove or modify it — the generator uses it for staleness checks.
