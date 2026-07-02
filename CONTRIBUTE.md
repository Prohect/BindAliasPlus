# Contributing to BindAliasPlus

Welcome! This guide covers how to contribute to this project — source code,
documentation, config files, and everything in between.

## Table of Contents

- [Quick Start](#quick-start)
- [What's in the Repository](#whats-in-the-repository)
- [Source Code Contributions](#source-code-contributions)
  - [Branch Strategy](#branch-strategy)
  - [Build & Test](#build--test)
  - [Code Style](#code-style)
  - [Multi-Version Compatibility](#multi-version-compatibility)
- [Non-Source Contributions](#non-source-contributions)
  - [Documentation](#documentation)
  - [Config & Tooling](#config--tooling)
  - [CI / Workflows](#ci--workflows)
- [Git Hooks](#git-hooks)
  - [post-commit: Cross-Branch Sync](#post-commit-cross-branch-sync)
  - [post-checkout: JDTLS Setup](#post-checkout-jdtls-setup)
  - [Installing the Hooks](#installing-the-hooks)
- [Pull Request Process](#pull-request-process)
- [Release Workflow (maintainers)](#release-workflow-maintainers)

---

## Quick Start

```bash
# 1. Clone with all branches
git clone https://github.com/Prohect/BindAliasPlus
cd BindAliasPlus
git fetch --all

# 2. Install both git hooks (hard-link from scripts/)
ln -f scripts/sync-post-commit .git/hooks/post-commit
ln -f scripts/post-checkout    .git/hooks/post-checkout

# 3. Build (gradlew auto-downloads Gradle, Fabric Loom, and dependencies)
./gradlew build
```

> **Requires:** JDK 25 and an internet connection. `gradlew` handles everything
> else --- Gradle, Fabric Loader, Minecraft, and all dependencies are downloaded
> automatically on first build.
>
> If you cloned with `--single-branch` the hooks exit silently --- no errors.

---

## What's in the Repository

Everything tracked by git, organized by role:

### Build (produces the mod JAR)

```
src/
├── client/java/              # Client-side mod code (65 files)
│   └── com/github/prohect/
│       ├── alias/            # Alias base classes
│       ├── alias/builtinAlias/ # Built-in alias implementations
│       ├── mixin/client/     # Mixins
│       └── util/             # Utilities
├── client/resources/         # Access widener, mixin config
├── main/java/                # Main entrypoint
└── main/resources/           # fabric.mod.json, mixin config, icon

build.gradle                   # Build script
settings.gradle                # Gradle settings
gradle.properties              # MC version, mod version, dependencies
gradlew / gradlew.bat          # Gradle wrapper scripts
gradle/wrapper/                # Gradle wrapper JAR + properties
```

### Non-build (docs, config, CI, tooling)

```
CHANGELOG.md                   # Release changelog
CLAUDE.md                      # Agent instructions (internal)
CONTRIBUTE.md                  # This file
DEVELOP.md                     # Development setup guide
README.md                      # English user guide
README_CN.md                   # Chinese user guide
LICENSE                        # CC0-1.0

.gitattributes                 # Line-ending rules
.gitignore                     # Exclude patterns
.git_active_branches           # Branches receiving auto-syncs
.git_sync_across_active_branches # File patterns to auto-sync

.github/workflows/build.yml           # CI: build on push/PR
.github/workflows/publish-modrinth.yml # CI: publish to Modrinth on release

formatter.xml                  # Eclipse/VS Code Java formatter (GoogleStyle)
setup-jdtls.sh                 # JDTLS / Eclipse project setup script
scripts/sync-post-commit       # post-commit hook: cross-branch sync
scripts/post-checkout          # post-checkout hook: run setup-jdtls.sh
```

> All non-build files are **auto-synced** across active branches on commit
> (see `.git_sync_across_active_branches` for the exact list). You only need
> to commit them once on your working branch.

---

## Source Code Contributions

### Branch Strategy

This project targets **four active Minecraft versions** across two mapping
styles:

| Branch           | MC Version | Mappings |
| ---------------- | ---------- | -------- |
| `26.1.2_26.2`    | 26.2       | Mojang   |
| `26.1_26.1.1`    | 26.1.1     | Mojang   |
| `1.21.9_1.21.11` | 1.21.11    | Yarn     |
| `1.21_1.21.8`    | 1.21.8     | Yarn     |

**Which branch to target:**

- **New features / bug fixes** — start on the **newest Mojang-mappings branch**
  (`26.1.2_26.2`). Version-specific source files (`src/`) are **not** auto-synced,
  so you'll need to cherry-pick or manually port to each target branch.
- **Version-specific fixes** — target the affected branch directly.

### Build & Test

```bash
./gradlew build          # Full build (compile + JAR)
./gradlew runTestClient  # Launch test client (singleplayer world)
./gradlew genSources     # Decompile Minecraft sources (needed for IDE)
./gradlew eclipse        # Generate Eclipse .classpath / .project
```

The test client auto-loads a config that detects crashes.

**Before committing:** at minimum `./gradlew build` must pass. For functional
changes, also run `./gradlew runTestClient` and verify in-game.

### Code Style

**Google Java Style** via [`formatter.xml`](formatter.xml) (Eclipse format,
compatible with the VS Code
[Java extension](https://marketplace.visualstudio.com/items?itemName=redhat.java)
and Eclipse JDT).

- Indentation: spaces (not tabs)
- Line endings: LF for `.java` / `.sh`, CRLF for `.bat` (enforced by `.gitattributes`)
- Use `@formatter:off` / `@formatter:on` sparingly

### Multi-Version Compatibility

The codebase spans Mojang and Yarn mappings. Key naming differences:

| Mojang (26.x)                           | Yarn (1.21.x)                                                    |
| --------------------------------------- | ---------------------------------------------------------------- |
| `Minecraft`                             | `MinecraftClient`                                                |
| `KeyMapping` / `setDown` / `clickCount` | `KeyBinding` / `setPressed` / `timesPressed`                     |
| `hasControlDown()`                      | `isCtrlPressed()` (1.21.9+) / `Screen.hasControlDown()` (1.21.8) |
| `AbstractContainerScreen`               | `HandledScreen`                                                  |
| `hoveredSlot` / `slotClicked`           | `focusedSlot` / `onMouseClick`                                   |
| `ContainerInput.THROW`                  | `SlotActionType.THROW`                                           |
| `onClose()`                             | `close()`                                                        |
| `KeyboardHandler` / `MouseHandler`      | `Keyboard` / `Mouse`                                             |
| `KeyEvent` / `MouseButtonInfo`          | `KeyInput` / `MouseInput` (1.21.9+), `int...` (1.21.8)           |
| accesswidener namespace `official`      | accesswidener namespace `named`                                  |

> Decompiled sources per branch live in `mc-decompile-sources/<branch>/`
> (generated by `setup-jdtls.sh`). See [CLAUDE.md](CLAUDE.md) for usage.

---

## Non-Source Contributions

### Documentation

All docs live at the repository root:

| File            | Audience     | Purpose                       |
| --------------- | ------------ | ----------------------------- |
| `README.md`     | Users        | English user guide            |
| `README_CN.md`  | Users        | Simplified Chinese user guide |
| `DEVELOP.md`    | Contributors | Development setup guide       |
| `CONTRIBUTE.md` | Contributors | This file                     |
| `CHANGELOG.md`  | Users        | Release changelog             |
| `CLAUDE.md`     | Internal/AI  | Agent instructions            |

**Guidelines:**

- Keep `README.md` and `README_CN.md` in sync — changes to one should mirror
  the other.
- `CHANGELOG.md` follows [Keep a Changelog](https://keepachangelog.com/):
  `Added`, `Changed`, `Fixed`, `Removed`.
- All docs are auto-synced — commit once, the hook propagates to every branch.

### Config & Tooling

Non-build config and tooling files:

| File                               | Purpose                               |
| ---------------------------------- | ------------------------------------- |
| `.git_active_branches`             | Target branches for auto-sync         |
| `.git_sync_across_active_branches` | File patterns to auto-sync            |
| `.gitattributes`                   | Line-ending rules                     |
| `.gitignore`                       | Exclude patterns                      |
| `formatter.xml`                    | Java formatter settings (GoogleStyle) |
| `setup-jdtls.sh`                   | JDTLS / Eclipse project setup         |
| `scripts/sync-post-commit`         | post-commit hook script               |
| `scripts/post-checkout`            | post-checkout hook script             |

> All of these are auto-synced. A change to any of them in one branch
> propagates to all active branches automatically.

### CI / Workflows

| Workflow               | Trigger        | What it does                          |
| ---------------------- | -------------- | ------------------------------------- |
| `build.yml`            | push, PR       | Builds with JDK 25, uploads artifacts |
| `publish-modrinth.yml` | GitHub Release | Publishes JARs to Modrinth            |

CI workflows are auto-synced.

---

## Git Hooks

This repository uses two git hooks, both tracked in `scripts/`.

### post-commit: Cross-Branch Sync

After every commit, checks if any changed files match the patterns in
`.git_sync_across_active_branches`. If so, creates equivalent commits on every
branch listed in `.git_active_branches` (skipping the source branch).

**How it works:**

1. Reads `.git_active_branches` → target branches
2. Reads `.git_sync_across_active_branches` → file patterns to sync
3. Gets changed files from the commit (`git diff-tree`)
4. For each matching file, copies the blob to each target branch via
   `git read-tree` + `git write-tree` + `git commit-tree`
5. Synced commits append `source: <short-sha>` so you can trace the origin

**Complete list of synced files** (from `.git_sync_across_active_branches`):

```
# docs
CHANGELOG.md
CLAUDE.md
CONTRIBUTE.md
DEVELOP.md
README.md
README_CN.md

# sync config itself
.git_active_branches
.git_sync_across_active_branches
scripts/post-checkout
scripts/sync-post-commit

# repo config
.gitattributes
.gitignore

# IDE / editor
formatter.xml
setup-jdtls.sh

# CI
.github/workflows/*.yml
```

> Source files under `src/` are **not** auto-synced — they contain
> version-specific code. Port those manually.

### post-checkout: JDTLS Setup

Runs [`setup-jdtls.sh`](setup-jdtls.sh) automatically when switching branches:

1. Generates decompiled Minecraft sources (`./gradlew genSources`)
2. Generates Eclipse `.classpath` / `.project` (`./gradlew eclipse`)
3. Strips Buildship references from the Eclipse config
4. Extracts MC source JARs to `mc-decompile-sources/<branch>/` for browsing

> If you don't use Eclipse or JDTLS, this hook is harmless — it just runs a
> build step on branch switch.

### Installing the Hooks

Both hooks are tracked in `scripts/`. Hard-link them into `.git/hooks/` (so
updating the tracked file updates the hook in-place):

```bash
ln -f scripts/sync-post-commit .git/hooks/post-commit
ln -f scripts/post-checkout    .git/hooks/post-checkout
```

> Hard links work on all platforms (Linux, macOS, Windows). If you cloned with
> `--single-branch` the hooks exit silently --- no errors.

---

## Pull Request Process

1. **Fork** the repository and create a feature branch off the appropriate base
   branch (see [Branch Strategy](#branch-strategy)).
2. **Make your changes.** Keep commits focused and atomic. Follow the
   [Code Style](#code-style).
3. **Build and test.** Run `./gradlew build` at minimum. For functional
   changes, test in-game with `./gradlew runTestClient`.
4. **Update the changelog** (`CHANGELOG.md`) under an `## [Unreleased]` section
   if your change is user-visible.
5. **Open a pull request** against the base branch. Describe:
   - What the change does
   - Why it's needed
   - Which Minecraft versions were tested
6. **CI checks** must pass (`build.yml` runs automatically).
7. **Wait for review.** A maintainer will review your PR. Address feedback
   by pushing additional commits — avoid force-pushing after review starts
   unless requested.

---

## Release Workflow (maintainers)

For reference, the release process is:

1. **Develop** → build + `runTestClient` → commit
2. **Sync** → automatic via `post-commit` hook. Manual fallback: cherry-pick
   to each active branch → build + `runTestClient` per branch.
3. **⏸ STOP** — wait for confirmation before version bump
4. **Bump** → update `mod_version` in `gradle.properties` + `CHANGELOG.md`
5. **Collect** → build each branch, copy JARs to `release/`
6. **Verify** → `unzip -p <jar> fabric.mod.json` to confirm the version
7. **Release** → `git push` all branches + `gh release create`

---

## Questions?

- **Bug reports / feature requests:** [GitHub Issues](https://github.com/Prohect/BindAliasPlus/issues)
- **Mod page:** [Modrinth](https://modrinth.com/mod/bind-alias-plus)

Thank you for contributing to BindAliasPlus!
