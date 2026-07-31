BindAlias\minecraft-decompiled-sources is in .gitignore,
search inside:

```bash
cd minecraft-decompiled-sources/<branch>/ && grep <args>
# cd to a more specific path if you understand the file tree structure
```

```bash
./gradlew build --no-daemon
```
build the project would format codes

check `build_test_sync_release_README.md` on need of test game client or release

## Parallel sub-agents: pitfall

**Never spawn multiple sub-agents that write to the same working tree concurrently, especially across git branches.**

- Each agent shares the same filesystem. Switching branches in one agent leaves stale build artifacts, untracked files, or modified working-tree state that leaks into the next agent's checkout.
- Git checkout between branches can silently keep uncommitted files from one branch on another — cross-contaminating source, config, and build output.
- Gradle daemons (or any long-lived build tool process) cache per-branch state. Running builds across branches without `--stop` reuses stale mappings, compiled classes, or access-widener remappings from the previous branch.

**Correct pattern**: one agent does one branch at a time, end-to-end (checkout → clean build → test → collect artifacts), with `--stop` and `rm -rf build/libs` between branches.
