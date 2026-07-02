# Contributing to BindAliasPlus

Welcome! This guide covers how to contribute to this project — source code,
documentation, config files, and everything in between.

## Table of Contents

- [Quick Start](#quick-start)
- [Repository Structure](#repository-structure)
- [Source Code Contributions](#source-code-contributions)
  - [Branch Strategy](#branch-strategy)
  - [Build & Test](#build--test)
  - [Code Style](#code-style)
  - [Multi-Version Compatibility](#multi-version-compatibility)
- [Non-Source Contributions](#non-source-contributions)
  - [Documentation](#documentation)
  - [Config Files](#config-files)
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

# 2. Install the sync hook (see Git Hooks below)
cp scripts/sync-post-commit .git/hooks/post-commit

# 3. Build
./gradlew build

# 4. Run the test client
./gradlew runTestClient
```

> **Requires:** JDK 25, Fabric Loader. The project uses Gradle with the
> [Fabric Loom](https://fabricmc.net/wiki/documentation:fabric_loom) plugin.

---

## Repository Structure

```
BindAliasPlus/
├── src/
│   ├── client/java/        # Client-side mod code (mixins, aliases, keybindings)
│   │   └── com/github/prohect/
│   │       ├── alias/              # Alias base classes
│   │       ├── alias/builtinAlias/ # Built-in alias implementations
│   │       ├── mixin/client/       # Mixins
│   │       └── util/               # Utilities
│   ├── client/resources/   # Client resources (access widener, fabric.mod.json)
│   ├── main/java/          # Main entrypoint (shared across environments)
│   └── main/resources/     # Main resources
├── scripts/
│   └── sync-post-commit    # Git hook script (copy to .git/hooks/post-commit)
├── .github/workflows/      # CI (build + Modrinth publish)
├── .git_active_branches    # Branches that receive automatic syncs
├── .git_sync_across_active_branches  # File patterns to auto-sync
├── build.gradle            # Build configuration
├── gradle.properties       # Mod version, MC version, dependency versions
├── formatter.xml           # Eclipse/VS Code Java formatter config (GoogleStyle)
├── CHANGELOG.md            # Release changelog
├── DEVELOP.md              # Development setup (detailed)
├── CLAUDE.md               # Agent instructions (internal)
├── README.md / README_CN.md
└── LICENSE                 # CC0-1.0
```

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
  (`26.1.2_26.2`). The [post-commit hook](#post-commit-cross-branch-sync) will
  automatically mirror your changes to the other active branches for
  non-version-specific files.
- **Version-specific fixes** — target the affected branch directly. For example,
  a 1.21.8-only issue goes to `1.21_1.21.8`.

> When in doubt, work on the newest branch and let the sync hook propagate
> changes. If a change doesn't apply cleanly to another branch, you'll need to
> handle conflicts manually.

### Build & Test

```bash
# Full build (compile + test + JAR)
./gradlew build

# Launch the test client (loads a singleplayer world for manual testing)
./gradlew runTestClient

# Generate decompiled Minecraft sources (needed for IDE setup)
./gradlew genSources

# Generate Eclipse project files (needed for JDTLS / Eclipse-based editors)
./gradlew eclipse
```

The test client runs with `--quickPlaySingleplayer Test_26_2` (branch-specific).
After a crash, the auto-loaded config file detects the crash and reports it.

**Before committing:** at minimum, run `./gradlew build` to confirm compilation
passes. For functional changes, also run `./gradlew runTestClient` and verify
the behavior in-game.

### Code Style

This project uses **Google Java Style**. The formatter config is in
[`formatter.xml`](formatter.xml) (Eclipse format, compatible with VS Code
[Java extension](https://marketplace.visualstudio.com/items?itemName=redhat.java)
and Eclipse JDT).

- Indentation: spaces (not tabs)
- Line endings: LF for `.java` / `.sh`, CRLF for `.bat`
- Use `@formatter:off` / `@formatter:on` sparingly where auto-formatting
  produces poor results

### Multi-Version Compatibility

Since the codebase spans Mojang and Yarn mappings, be aware of these naming
differences:

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

> When syncing a change across branches, verify that mappings are correct for
> each target. The `mc-decompile-sources/` directory (per-branch) is available
> for reference — see [CLAUDE.md](CLAUDE.md) for usage.

---

## Non-Source Contributions

### Documentation

Documentation files live at the repository root:

| File            | Audience     | Purpose                              |
| --------------- | ------------ | ------------------------------------ |
| `README.md`     | Users        | English user guide                   |
| `README_CN.md`  | Users        | Simplified Chinese user guide        |
| `DEVELOP.md`    | Contributors | Development setup guide              |
| `CONTRIBUTE.md` | Contributors | This file — contribution guide       |
| `CHANGELOG.md`  | Users        | Release changelog (per version)      |
| `CLAUDE.md`     | Internal/AI  | Agent instructions (not user-facing) |

**Guidelines:**

- Keep `README.md` and `README_CN.md` in sync — changes to one should be
  mirrored to the other.
- `CHANGELOG.md` entries follow [Keep a Changelog](https://keepachangelog.com/)
  conventions: `Added`, `Changed`, `Fixed`, `Removed`.
- Documentation files are **auto-synced** across all active branches (see
  `.git_sync_across_active_branches`). You only need to commit documentation
  changes once on your working branch.

### Config Files

Non-code config files that anyone can contribute to:

| File                               | Purpose                                      |
| ---------------------------------- | -------------------------------------------- |
| `.git_active_branches`             | List of branches receiving auto-syncs        |
| `.git_sync_across_active_branches` | File patterns to auto-sync after each commit |
| `.gitattributes`                   | Line-ending rules for cross-platform dev     |
| `.gitignore`                       | Files excluded from version control          |
| `formatter.xml`                    | Eclipse/VS Code Java formatter settings      |
| `.github/workflows/*.yml`          | CI/CD workflows                              |

> All of these (except `.gitignore`) are **auto-synced** files. A change to any
> of them in one branch propagates to all active branches automatically.

### CI / Workflows

- **`build.yml`** — runs on every push and PR. Builds the project with JDK 25
  and uploads artifacts.
- **`publish-modrinth.yml`** — triggered by GitHub Releases. Downloads JARs
  from the release and publishes them to Modrinth via `mc-publish`.

Changes to CI workflows are auto-synced across branches.

---

## Git Hooks

This repository uses two git hooks.

### post-commit: Cross-Branch Sync

The most important hook. After every commit, it checks if any changed files
match the patterns in `.git_sync_across_active_branches`. If so, it creates
equivalent commits on every branch listed in `.git_active_branches` (skipping
the source branch).

**How it works:**

1. Reads `.git_active_branches` → list of target branches
2. Reads `.git_sync_across_active_branches` → list of file patterns to sync
3. Gets the list of changed files from the commit (`git diff-tree`)
4. For each changed file that matches a sync pattern, copies the blob to each
   target branch via `git read-tree` + `git write-tree` + `git commit-tree`
5. The synced commit message appends `source: <short-sha>` so you can trace the
   origin

**Currently synced files** (as defined in `.git_sync_across_active_branches`):

- `CHANGELOG.md`, `CLAUDE.md`, `CONTRIBUTE.md`, `DEVELOP.md`, `README.md`, `README_CN.md`
- `.git_active_branches`, `.git_sync_across_active_branches`
- `scripts/post-checkout`, `scripts/sync-post-commit`
- `.gitignore`
- `.github/workflows/*.yml`

> Files not in this list (e.g., Java source in `src/`) are **not** auto-synced.
> Version-specific source changes must be cherry-picked manually.

### post-checkout: JDTLS Setup

Automatically runs [`setup-jdtls.sh`](setup-jdtls.sh) when switching branches.
The hook script lives at [`scripts/post-checkout`](scripts/post-checkout).

This hook:

1. Generates decompiled Minecraft sources (via `./gradlew genSources`)
2. Generates Eclipse `.classpath` / `.project` (via `./gradlew eclipse`)
3. Strips Buildship references from the Eclipse config
4. Extracts Minecraft source JARs to `mc-decompile-sources/<branch>/` for
   agent-assisted browsing

> If you don't use Eclipse or JDTLS, this hook is harmless — it just runs a
> build step on branch switch.

### Installing the Hooks

Both hooks are tracked in `scripts/`. Install by linking or copying into
`.git/hooks/`:

**Linux / macOS:**

```bash
ln -sfr scripts/sync-post-commit .git/hooks/post-commit
ln -sfr scripts/post-checkout    .git/hooks/post-checkout
```

**Windows (MSYS2 / Git Bash):**

```bash
MSYS=winsymlinks:nativestrict ln -sfr scripts/sync-post-commit .git/hooks/post-commit
MSYS=winsymlinks:nativestrict ln -sfr scripts/post-checkout    .git/hooks/post-checkout
```

**Windows (cmd / PowerShell, copy instead of link):**

```cmd
copy scripts/sync-post-commit .git/hooks/post-commit
copy scripts/post-checkout    .git/hooks/post-checkout
```

> If you cloned with `--single-branch` or the config files are missing, the
> hooks exit silently --- no errors.

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
- **Discussion:** [GitHub Discussions](https://github.com/Prohect/BindAliasPlus/discussions) (if enabled)
- **Mod page:** [Modrinth](https://modrinth.com/mod/bind-alias-plus)

Thank you for contributing to BindAliasPlus!
