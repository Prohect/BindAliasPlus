#!/bin/bash
# setup-jdtls.sh — Prepare JDTLS with navigable Minecraft sources for the current branch
# Run once per branch. Sources are cached in mc-sources/<branch>/ for fast switching.

set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
WIN_DIR="$(cygpath -m "$SCRIPT_DIR" 2>/dev/null || echo "$SCRIPT_DIR")"
cd "$SCRIPT_DIR"

BRANCH="$(git branch --show-current)"
echo "=== Branch: $BRANCH ==="

SRC_DIR="mc-sources/$BRANCH"

# Step 1: Generate decompiled Minecraft sources (if not already done)
echo "[1/4] Generating Minecraft sources..."
./gradlew genSources --no-daemon --quiet

# Step 2: Generate Eclipse .classpath with all dependencies
echo "[2/4] Generating Eclipse classpath..."
./gradlew eclipseClasspath --no-daemon --quiet

# Step 3: Remove gradleclasspathcontainer (JDTLS can't resolve it)
echo "[3/4] Fixing .classpath..."
sed -i '/gradleclasspathcontainer/d' .classpath

# Step 4: Extract MC source jars (skip if already cached for this branch)
if [ -d "$SRC_DIR" ]; then
    echo "[4/4] Sources already cached: $SRC_DIR ($(find "$SRC_DIR" -name '*.java' | wc -l) files)"
else
    echo "[4/4] Extracting Minecraft sources → $SRC_DIR ..."
    mkdir -p "$SRC_DIR"
    find .gradle/loom-cache/minecraftMaven -name "*-sources.jar" | while read srcjar; do
        echo "  extracting: $srcjar"
        unzip -o -q "$srcjar" -d "$SRC_DIR"/
    done
    echo "  done: $(find "$SRC_DIR" -name '*.java' | wc -l) files"
fi

# Point classpath sourcepath to the branch-specific extracted directory
sed -i 's|sourcepath="[^"]*\.gradle/loom-cache/minecraftMaven/[^"]*-sources\.jar"|sourcepath="'"$WIN_DIR"'/'"$SRC_DIR"'"|g' .classpath
sed -i 's|sourcepath="[^"]*mc-sources[^"]*"|sourcepath="'"$WIN_DIR"'/'"$SRC_DIR"'"|g' .classpath

echo ""
echo "Done. Restart Zed (lsp: restart) to pick up changes."
