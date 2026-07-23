#!/bin/bash

# Generate doc scaffolding from compiled .class files via javap.
# Creates docs/en-US/<source-path>/<File>.java/ with stubs for every item.
# Never overwrites existing files.
# Creates overview README.md in any directory with >=2 children.
# Detects orphaned doc files whose source items no longer exist.
# Requires: JDK (javap), a built project (class files under bin/).
# Usage: bash ./scripts/generate_docs.sh

set -e

DOCROOT="docs/en-US"
COMMIT_SHA=$(git --no-pager log -1 --format="%H" 2>/dev/null || echo "unknown")
FOOTER="*Documented for Commit: [${COMMIT_SHA}](https://github.com/Prohect/BindAliasPlus/tree/${COMMIT_SHA})*"

CREATED_LOG=$(mktemp)
trap 'rm -f "$CREATED_LOG"' EXIT
created_count=0

# --- Pre-check: ensure bin/ is up to date ---
if [ ! -d bin ] || [ -z "$(find bin -name '*.class' -print -quit 2>/dev/null)" ]; then
    echo "ERROR: No .class files found under bin/. Run './gradlew build' first." >&2
    exit 1
fi
# Check 1: timestamp — source newer than classes (branch switch touched files)
newest_src=$(find src -name '*.java' -printf '%T@ %p\n' 2>/dev/null | sort -rn | head -1 | cut -d' ' -f1)
newest_class=$(find bin -name '*.class' -printf '%T@ %p\n' 2>/dev/null | sort -rn | head -1 | cut -d' ' -f1)
if [ -n "$newest_src" ] && [ -n "$newest_class" ]; then
    if [ "${newest_src%.*}" -gt "${newest_class%.*}" ] 2>/dev/null; then
        echo "WARNING: Source files are newer than compiled classes. Run './gradlew build' to ensure docs match current branch." >&2
    fi
fi

write_if_missing() {
    local target="$1"
    if [ ! -f "$target" ]; then
        cat > "$target"
        echo "+ $target" >> "$CREATED_LOG"
        created_count=$((created_count + 1))
    fi
}

# --- Pass 1: source file doc stubs ---
find bin -name "*.class" | sort | while read classfile; do
    module="${classfile#bin/}"
    module="${module%%/*}"

    pkgpath="${classfile#bin/$module/}"
    pkgpath="${pkgpath%.class}.java"

    srcpath="src/$module/java/$pkgpath"
    [ -f "$srcpath" ] || continue

    docdir="$DOCROOT/$srcpath"
    docdir="${docdir%.java}.java"
    mkdir -p "$docdir"

    class_name=$(basename "$srcpath" .java)
    expected_file="$docdir/.expected"

    if grep -qP '^\s*static\s*\{' "$srcpath" 2>/dev/null; then
        has_static_block=1
    else
        has_static_block=0
    fi

    # --- README.md ---
    write_if_missing "$docdir/README.md" << EOF
# $class_name

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|

## See Also

| Item | Description |
|------|-------------|

$FOOTER
EOF

    # --- Per-item stubs ---
    javap -p "$classfile" 2>/dev/null | awk \
        -v docdir="$docdir" \
        -v footer="$FOOTER" \
        -v srcpath="$srcpath" \
        -v class_name="$class_name" \
        -v has_static_block="$has_static_block" \
        -v expected_file="$expected_file" \
        -v created_log="$CREATED_LOG" '
    BEGIN { in_class = 0 }

    /^Compiled from/ { next }

    function note_expected(fname) {
        print fname >> expected_file
    }

    function write_if_missing(fname, content) {
        f = docdir "/" fname
        if (system("test -f \"" f "\"") != 0) {
            print content > f
            close(f)
            system("echo \"+ " f "\" >> \"" created_log "\"")
        }
        note_expected(fname)
    }

    /^[a-z]/ {
        in_class = 1
        line = $0
        sub(/ *\{ *\r?$/, "", line)

        write_if_missing(class_name ".md",
            "# " class_name " (" srcpath ")\n\n" \
            "## Syntax\n\n" \
            "```java\n" line "\n```\n\n" \
            "## Static Initializer\n\n" \
            (has_static_block == "1" ? "_See [static-init](static-init.md)._\n\n" : "_None._\n\n") \
            "## Remarks\n\n" \
            "## See Also\n\n" \
            "| Item | Description |\n" \
            "|------|-------------|\n\n" \
            footer "\n")
        next
    }

    !in_class { next }
    /lambda\$|access\$/ { next }

    /static *\{ *\};/ {
        if (has_static_block == "1") {
            note_expected("static-init.md")
            f = docdir "/static-init.md"
            if (system("test -f \"" f "\"") != 0) {
                print "# static-init (" srcpath ")" > f
                print "" >> f
                print "## Remarks" >> f
                print "" >> f
                print "Executed once when the class is loaded. See source for content." >> f
                print "" >> f
                print footer >> f
                close(f)
                system("echo \"+ " f "\" >> \"" created_log "\"")
            }
        }
        next
    }

    /^\}/ { in_class = 0; next }

    /\(/ {
        line = $0
        sub(/;\r?$/, "", line)
        gsub(/^[ \t]+/, "", line)

        if (match(line, /\(/)) {
            before = substr(line, 1, RSTART - 1)
            n = split(before, parts, /[ \t]+/)
            name = parts[n]
            sub(/<.*/, "", name)
        } else { next }

        if (name ~ /\./ || name == class_name) next

        fname = name ".md"
        note_expected(fname)
        f = docdir "/" fname
        if (system("test -f \"" f "\"") != 0) {
            print "# " name " method (" srcpath ")" > f
            print "" >> f
            print "## Syntax" >> f
            print "" >> f
            print "```java" >> f
            print line >> f
            print "```" >> f
            print "" >> f
            print "## Parameters" >> f
            print "" >> f
            print "| Name | Type | Description |" >> f
            print "|------|------|-------------|" >> f
            print "" >> f
            print "## Remarks" >> f
            print "" >> f
            print "## See Also" >> f
            print "" >> f
            print "| Item | Description |" >> f
            print "|------|-------------|" >> f
            print "" >> f
            print footer >> f
            close(f)
            system("echo \"+ " f "\" >> \"" created_log "\"")
        }
        next
    }

    /[A-Za-z0-9_<>\[\],.? ]+ [A-Za-z_][A-Za-z0-9_]*;/ {
        line = $0
        sub(/;\r?$/, "", line)
        gsub(/^[ \t]+/, "", line)

        n = split(line, parts, /[ \t]+/)
        fname = parts[n]

        if (line ~ /^(public |protected )/) {
            fname_md = fname ".md"
            note_expected(fname_md)
            f = docdir "/" fname_md
            if (system("test -f \"" f "\"") != 0) {
                print "# " fname " field (" srcpath ")" > f
                print "" >> f
                print "## Syntax" >> f
                print "" >> f
                print "```java" >> f
                print line >> f
                print "```" >> f
                print "" >> f
                print "## Remarks" >> f
                print "" >> f
                print footer >> f
                close(f)
                system("echo \"+ " f "\" >> \"" created_log "\"")
            }
        }
    }
    '

done

# --- Pass 2: overview README.md for parent dirs with >=2 children ---
while IFS= read -r dir; do
    child_dirs=$(find "$dir" -maxdepth 1 -type d -not -path "$dir" | sort)
    count=$(echo "$child_dirs" | grep -c '' 2>/dev/null || echo 0)

    [ "$count" -ge 2 ] || continue
    echo "$dir" | grep -q '\.java$' && continue

    overview="$dir/README.md"
    [ -f "$overview" ] && continue

    dirname=$(basename "$dir")
    {
        echo "# $dirname"
        echo ""
        echo "## Contents"
        echo ""
        echo "| Name | Description |"
        echo "|------|-------------|"
        echo "$child_dirs" | while read child; do
            child_name=$(basename "$child")
            if [ -d "$child" ] && [ -f "$child/README.md" ]; then
                echo "| [$child_name]($child_name/README.md) | |"
            elif [ -d "$child" ]; then
                echo "| $child_name/ | |"
            fi
        done
        echo ""
        echo "$FOOTER"
    } > "$overview"
    echo "  overview: $overview"
    echo "+ $overview" >> "$CREATED_LOG"
    created_count=$((created_count + 1))
done < <(find "$DOCROOT" -type d | sort -r)

# --- Report created files ---
total_created=$(wc -l < "$CREATED_LOG" 2>/dev/null || echo 0)
if [ "$total_created" -gt 0 ]; then
    echo ""
    echo "=== Created $total_created file(s) that need edits ==="
    cat "$CREATED_LOG"
fi

# --- Pass 3: orphan detection ---
echo ""
echo "=== Checking for orphaned doc files ==="
orphan_count=0

# 3a: orphaned docdirs - source file no longer exists
while IFS= read -r docdir; do
    rel="${docdir#$DOCROOT/}"
    srcfile="${rel%.java}.java"
    if [ ! -f "$srcfile" ]; then
        echo "  ORPHAN DIR: $docdir (source $srcfile deleted)"
        orphan_count=$((orphan_count + 1))
    fi
done < <(find "$DOCROOT" -type d -name '*.java' 2>/dev/null)

# 3b: orphaned .md files - item removed from source but doc remains
while IFS= read -r docdir; do
    expected_file="$docdir/.expected"
    [ -f "$expected_file" ] || continue

    rel="${docdir#$DOCROOT/}"
    srcfile="${rel%.java}.java"
    [ -f "$srcfile" ] || continue

    while IFS= read -r md; do
        fname=$(basename "$md")
        if ! grep -qxF "$fname" "$expected_file" 2>/dev/null; then
            echo "  ORPHAN FILE: $md (item no longer in $srcfile)"
            orphan_count=$((orphan_count + 1))
        fi
    done < <(find "$docdir" -maxdepth 1 -name '*.md' ! -name 'README.md')
done < <(find "$DOCROOT" -type d -name '*.java' 2>/dev/null)

# 3c: clean up .expected files
find "$DOCROOT" -name '.expected' -delete 2>/dev/null || true

if [ "$orphan_count" -gt 0 ]; then
    echo ""
    echo "!!! Found $orphan_count orphaned doc file(s) - review and delete if stale"
else
    echo "  No orphaned doc files found."
fi

# Final summary
echo ""
echo "Done. Doc scaffolding under $DOCROOT/"
