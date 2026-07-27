# Build

A minimal guide to building BindAliasPlus from source.

## Prerequisites

- **JDK 25** — [Microsoft OpenJDK](https://learn.microsoft.com/en-us/java/openjdk/download) or any JDK 25 distribution
- **Git**

## Quick start

```bash
git clone https://github.com/Prohect/BindAliasPlus.git
cd BindAliasPlus
./gradlew build
```

The built JAR lands in `build/libs/`.

> **Windows:** use `gradlew.bat` instead of `./gradlew`.
> ./gradlew build would format the code.

## Run the test client

```bash
./gradlew runTestClient
```

This launches Minecraft with the mod loaded and joins a test world
(`Test_26_2` on the `26.1.2_26.2` branch). Test config lives in
`run/config/bind-alias-plus.cfg`.

## Useful Gradle tasks

| Task            | What it does                                    |
| --------------- | ----------------------------------------------- |
| `build`         | Compile and package the mod JAR                 |
| `runClient`     | Launch Minecraft client with the mod            |
| `runTestClient` | Launch client into a test world                 |
| `runServer`     | Launch a dedicated server                       |
| `genSources`    | Generate decompiled Minecraft sources (for IDE) |
| `eclipse`       | Generate Eclipse project files                  |

## IDE setup

This project uses Eclipse `.classpath` / `.project` + JDTLS for editing.
A `formatter.xml` (GoogleStyle) is included — import it in your IDE to
match the project's code style.

If you switch branches, the post-checkout hook automatically regenerates
Eclipse config and decompiled sources for the new branch. See
[CONTRIBUTE.md](CONTRIBUTE.md#git-hooks) for hook installation.
