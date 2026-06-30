#!/bin/bash
# setup-jdtls.sh — Prepare JDTLS with navigable Minecraft sources for the current branch
# Run once per branch. Sources are cached in mc-decompile-sources/<branch>/ for fast switching.

set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
WIN_DIR="$(cygpath -m "$SCRIPT_DIR" 2>/dev/null || echo "$SCRIPT_DIR")"
cd "$SCRIPT_DIR"

BRANCH="$(git branch --show-current)"
echo "=== Branch: $BRANCH ==="

SRC_DIR="mc-decompile-sources/$BRANCH"

# Step 1: Generate decompiled Minecraft sources (if not already cached)
if [ -d "$SRC_DIR" ] && [ "$(find "$SRC_DIR" -name '*.java' 2>/dev/null | wc -l)" -gt 0 ]; then
    echo "[1/5] Sources already cached: $SRC_DIR ($(find "$SRC_DIR" -name '*.java' | wc -l) files)"
else
    echo "[1/5] Generating Minecraft sources..."
    ./gradlew clean
    ./gradlew genSources --no-daemon --quiet
fi

# Step 2: Generate Eclipse .classpath + .project
echo "[2/5] Generating Eclipse config..."
./gradlew eclipse --no-daemon --quiet

# Step 3: Strip Buildship from .project and .classpath
#         Buildship triggers Gradle import, which overwrites our source paths.
echo "[3/5] Stripping Buildship references..."
sed -i '/gradleclasspathcontainer/d' .classpath
sed -i '/gradleprojectnature/d' .project
sed -i '/<buildCommand>/{:a;N;/<\/buildCommand>/!ba;/gradleprojectbuilder/d}' .project

# Step 4: Extract MC source jars (skip if already cached for this branch)
if [ -d "$SRC_DIR" ] && [ "$(find "$SRC_DIR" -name '*.java' | wc -l)" -gt 0 ]; then
    echo "[4/5] Sources already cached: $SRC_DIR ($(find "$SRC_DIR" -name '*.java' | wc -l) files)"
else
    echo "[4/5] Extracting Minecraft sources → $SRC_DIR ..."
    rm -rf "$SRC_DIR"
    mkdir -p "$SRC_DIR"
    find .gradle/loom-cache/minecraftMaven -name "*-sources.jar" | while read srcjar; do
        echo "  extracting: $srcjar"
        unzip -o -q "$srcjar" -d "$SRC_DIR"/
    done
    FILE_COUNT=$(find "$SRC_DIR" -name '*.java' | wc -l)
    echo "  done: $FILE_COUNT files"
    if [ "$FILE_COUNT" -eq 0 ]; then
        echo "ERROR: No Java sources extracted. Check that genSources succeeded." >&2
        exit 1
    fi
fi

# Step 5: Point classpath sourcepath to the branch-specific extracted directory
echo "[5/5] Updating source paths..."
sed -i 's|sourcepath="[^"]*\.gradle/loom-cache/minecraftMaven/[^"]*-sources\.jar"|sourcepath="'"$WIN_DIR"'/'"$SRC_DIR"'"|g' .classpath
sed -i 's|sourcepath="[^"]*mc-decompile-sources[^"]*"|sourcepath="'"$WIN_DIR"'/'"$SRC_DIR"'"|g' .classpath

echo ""
echo "Done. Restart Zed (lsp: restart) to pick up changes."
