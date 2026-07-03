#!/bin/bash

# Generate doc scaffolding from compiled .class files via javap.
# Creates docs/en-US/<source-path>/<File>.java/ with stubs for every item.
# Never overwrites existing files.
# Creates overview README.md in any directory with >=2 children.
# Requires: JDK (javap), a built project (class files under bin/).
# Usage: bash ./scripts/generate_docs.sh

set -e

DOCROOT="docs/en-US"
COMMIT_SHA=$(git --no-pager log -1 --format="%H" 2>/dev/null || echo "unknown")
FOOTER="*Documented for Commit: [${COMMIT_SHA}](https://github.com/Prohect/BindAliasPlus/tree/${COMMIT_SHA})*"

write_if_missing() {
    local target="$1"
    if [ ! -f "$target" ]; then
        cat > "$target"
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
        -v has_static_block="$has_static_block" '
    BEGIN { in_class = 0 }

    /^Compiled from/ { next }

    /^[a-z]/ {
        in_class = 1
        line = $0
        sub(/ *\{ *\r?$/, "", line)

        f = docdir "/" class_name ".md"
        if (system("test -f \"" f "\"") != 0) {
            print "# " class_name " (" srcpath ")" > f
            print "" >> f
            print "## Syntax" >> f
            print "" >> f
            print "```java" >> f
            print line >> f
            print "```" >> f
            print "" >> f
            print "## Static Initializer" >> f
            print "" >> f
            if (has_static_block == "1")
                print "_See [static-init](static-init.md)._" >> f
            else
                print "_None._" >> f
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
        }
        next
    }

    !in_class { next }
    /lambda\$|access\$/ { next }

    /static *\{ *\};/ {
        if (has_static_block == "1") {
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

        f = docdir "/" name ".md"
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
            f = docdir "/" fname ".md"
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
            }
        }
    }
    '

done

# --- Pass 2: overview README.md for parent dirs with >=2 children ---
find "$DOCROOT" -type d | sort -r | while read dir; do
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
done

echo "Done. Doc scaffolding under $DOCROOT/"
