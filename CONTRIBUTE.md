# Contributing to BindAlias

Welcome! This guide covers how to set up your environment and contribute.

## Before submitting

- Your change **builds** — `./gradlew build` passes on your branch
- Your change **works** — test with `./gradlew runTestClient`
- If your change adds a feature, add a **test alias** in
  `run/config/bind-alias.cfg` so others can verify it
- If you touch files in `.git_sync_across_active_branches`, make sure
  the same logic applies across branches (mapping differences, see below)

## Branches

BindAlias targets multiple Minecraft versions simultaneously. Each
branch maps to a specific MC version range and mappings flavor:

| Branch           | MC      | Mappings |
| ---------------- | ------- | -------- |
| `26.1.2_26.2`    | 26.2    | Mojang   |
| `26.1_26.1.1`    | 26.1.1  | Mojang   |
| `1.21.9_1.21.11` | 1.21.11 | Yarn     |
| `1.21_1.21.8`    | 1.21.8  | Yarn     |

Changes to synced files (docs, CI, scripts, formatter, etc.) are
auto-mirrored across branches by the post-commit hook. Code changes must
be ported manually — see [Mapping differences](#mapping-differences).

## Mapping differences

When porting code between Mojang and Yarn branches:

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

## Project structure

```
src/
├── main/                          # Shared code (loaded on both client & server)
│   ├── java/com/github/prohect/
│   │   └── BindAlias.java     # ModInitializer entrypoint
│   └── resources/
│       ├── fabric.mod.json
│       └── bind-alias.mixins.json
│
└── client/                        # Client-only code (split source set)
    ├── java/com/github/prohect/
    │   ├── BindAliasClient.java   # Client entrypoint + alias registration
    │   ├── KeyBindingPlus.java        # Key binding record
    │   ├── KeyPressed.java            # Key-press tick tracker
    │   ├── alias/
    │   │   ├── Alias.java             # Alias interface + global registries
    │   │   ├── AliasRecord.java       # Parsed alias reference
    │   │   ├── UserAlias.java         # User-defined alias (sequences)
    │   │   ├── BuiltinAliasWithArgs.java        # Base for aliases that take args
    │   │   ├── BuiltinAliasWithBooleanArgs.java # Base for +/- boolean aliases
    │   │   ├── BuiltinAliasWithIntegerArgs.java # Base for integer-arg aliases
    │   │   ├── BuiltinAliasWithDoubleArgs.java  # Base for double-arg aliases
    │   │   ├── BuiltinAliasWithGreedyStringArgs.java # Base for string-arg aliases
    │   │   ├── BuiltinAliasWithoutArgs.java     # Base for aliases with no args
    │   │   └── builtinAlias/         # All builtin alias implementations
    │   ├── mixin/
    │   │   └── client/               # Client-side mixins
    │   └── util/
    │       └── McScreenHelper.java   # Cross-version screen access helpers
    └── resources/
        ├── bind-alias.client.mixins.json
        └── bind-alias-client.accesswidener
```

## Codebase guide

### Architecture

The core of BindAlias is a **chain of aliases**. Every alias
implements `Alias<T>` (the `run(String args)` method). When the user
presses a key, `UserAlias.run()` parses the config line into a sequence
of alias names and dispatches each one by looking it up in a global
registry (`Alias.aliasesWithArgs`, `Alias.aliasesWithoutArgs`, etc.).

### Builtin alias class hierarchy

```
Alias<T>  (interface)
├── AliasWithArgs<T>        — aliases that take arguments (e.g. `wait\20`)
│   └── BuiltinAliasWithArgs<T>  (abstract, holds builtinAliasName)
│       ├── BuiltinAliasWithBooleanArgs<T>  — +flag/-flag aliases (sneak, sprint, attack...)
│       ├── BuiltinAliasWithIntegerArgs<T>  — integer args (wait, setPerspective...)
│       ├── BuiltinAliasWithDoubleArgs<T>   — double args (yaw, pitch, setYaw, setPitch...)
│       └── BuiltinAliasWithGreedyStringArgs<T> — string args (say, localSay, sendCommand...)
│
└── AliasWithoutArgs<T>     — aliases with no arguments
    └── BuiltinAliasWithoutArgs<T>  (abstract, holds builtinAliasName)
```

### Adding a new builtin alias

1. **Create a class** in `src/client/java/com/github/prohect/alias/builtinAlias/`.
   Extend the appropriate base class:

   | If your alias...          | Extend                             |
   | ------------------------- | ---------------------------------- |
   | Takes no arguments        | `BuiltinAliasWithoutArgs`          |
   | Is a boolean toggle (+/-) | `BuiltinAliasWithBooleanArgs`      |
   | Takes an integer          | `BuiltinAliasWithIntegerArgs`      |
   | Takes a double            | `BuiltinAliasWithDoubleArgs`       |
   | Takes a string            | `BuiltinAliasWithGreedyStringArgs` |

   Example — a simple boolean alias:

   ```java
   public class SneakAlias extends BuiltinAliasWithBooleanArgs<SneakAlias> {
       public SneakAlias() { super("builtinSneak"); }

       @Override
       public SneakAlias run(String args) {
           parseArgs(args);                          // sets this.flag
           if (Alias.isUnderTextInputScreen() && flag) return this;
           KeyMapping sneakKey = Minecraft.getInstance().options.keyShift;
           sneakKey.setDown(flag);
           if (flag) sneakKey.clickCount++;
           return this;
       }
   }
   ```

2. **Register it** in `BindAliasClient.onInitializeClient()`.
   Choose the right registration method:

   | Method                                    | When to use                                                    |
   | ----------------------------------------- | -------------------------------------------------------------- |
   | `.putToAliasesWithArgs()`                 | Aliases visible in command suggestions                         |
   | `.putToAliasesWithArgs_notSuggested()`    | Aliases hidden from suggestions (internal or game-key aliases) |
   | `.putToAliasesWithoutArgs()`              | Argument-less aliases visible in suggestions                   |
   | `.putToAliasesWithoutArgs_notSuggested()` | Argument-less aliases hidden from suggestions                  |

   Call `.addToScreenBlackList()` on boolean aliases that should only
   toggle state while no screen is open (e.g. attack, use).

3. **Add user-facing shorthand** (optional) — register `UserAlias`
   wrappers for `+name`/`-name` shorthand:

   ```java
   new UserAlias("builtinSneak\\1").putToAliasesWithoutArgs("+sneak");
   new UserAlias("builtinSneak\\0").putToAliasesWithoutArgs("-sneak");
   ```

### Adding a mixin

1. Create the mixin class in `src/client/java/com/github/prohect/mixin/client/`.
2. Register it in `src/client/resources/bind-alias.client.mixins.json`
   under the `"client"` array.
3. If you need to access private Minecraft fields/methods, add entries
   to `src/client/resources/bind-alias-client.accesswidener`.

### Key files to read

| File                                                   | Why                                                                            |
| ------------------------------------------------------ | ------------------------------------------------------------------------------ |
| `Alias.java`                                           | Global registries, screen-type helpers, alias parsing                          |
| `UserAlias.java`                                       | How user-defined alias sequences are dispatched                                |
| `BindAliasClient.java`                             | Entrypoint, alias registration, command definitions, config loading            |
| `BuiltinAliasWithBooleanArgs.java`                     | Base class for +/- toggles — see `parseArgs()` and `reapplyToGameKeyMapping()` |
| `SneakAlias.java` / `SayAlias.java` / `WaitAlias.java` | Reference implementations (boolean, string, integer)                           |
| `GuiMixin.java` / `KeyboardInputMixin.java`            | Examples of how mixins integrate with the alias system                         |

## Git hooks

Two scripts in `scripts/` automate repetitive tasks. Install them:

```bash
cp scripts/post-checkout .git/hooks/post-checkout
cp scripts/post-commit  .git/hooks/post-commit
```

### post-checkout

On branch switch, regenerates decompiled Minecraft sources
(`minecraft-decompiled-sources/<branch>/`), Eclipse `.classpath` /
`.project`, and extracts MC source jars for IDE browsing.

### post-commit

After committing, syncs files listed in `.git_sync_across_active_branches`
(docs, CI config, scripts, formatter, etc.) to all branches listed in
`.git_active_branches`. The commit message appends the source SHA so you
can trace where each sync came from.

## CI

GitHub Actions builds the project on every push and PR (`build.yml`).
If your PR fails CI, check the build log — it's usually a mapping
mismatch or a missing access widener entry.
