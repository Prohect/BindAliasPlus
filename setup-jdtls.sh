#!/bin/bash
# setup-jdtls.sh — Prepare JDTLS for the current branch
# Sources extracted to mc-decompile-sources/<branch>/ for agent browsing.
# JDTLS uses loom-cache -sources.jar directly (more reliable than directory sourcepath).

set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

BRANCH="$(git branch --show-current)"
echo "=== Branch: $BRANCH ==="

SRC_DIR="mc-decompile-sources/$BRANCH"

# Step 1: Generate decompiled Minecraft sources (if not already cached)
if [ -d "$SRC_DIR" ] && [ "$(find "$SRC_DIR" -name '*.java' 2>/dev/null | wc -l)" -gt 0 ]; then
    echo "[1/4] Sources already cached: $SRC_DIR ($(find "$SRC_DIR" -name '*.java' | wc -l) files)"
else
    echo "[1/4] Generating Minecraft sources..."
    ./gradlew clean
    ./gradlew genSources --no-daemon --quiet
fi

# Step 2: Generate Eclipse .classpath + .project
echo "[2/4] Generating Eclipse config..."
./gradlew eclipse --no-daemon --quiet

# Step 3: Strip Buildship from .project and .classpath
echo "[3/4] Stripping Buildship references..."
sed -i '/gradleclasspathcontainer/d' .classpath
sed -i '/gradleprojectnature/d' .project
sed -i '/<buildCommand>/{:a;N;/<\/buildCommand>/!ba;/gradleprojectbuilder/d}' .project

# Step 4: Extract MC source jars to mc-decompile-sources/ for agent browsing
#         Extracts only the JARs that .classpath references (avoids stale Loom generations).
#         (JDTLS uses the -sources.jar directly via .classpath — more reliable)
if [ -d "$SRC_DIR" ] && [ "$(find "$SRC_DIR" -name '*.java' | wc -l)" -gt 0 ]; then
    echo "[4/4] Sources already cached: $SRC_DIR ($(find "$SRC_DIR" -name '*.java' | wc -l) files)"
else
    echo "[4/4] Extracting Minecraft sources → $SRC_DIR ..."
    rm -rf "$SRC_DIR"
    mkdir -p "$SRC_DIR"
    grep -o 'sourcepath="[^"]*loom-cache[^"]*-sources\.jar"' .classpath | sed 's/sourcepath="//;s/"$//' | sort -u | while read srcjar; do
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

echo ""
echo "Done."

