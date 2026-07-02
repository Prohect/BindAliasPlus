#!/bin/bash

# Generate Java src code outline using javap (reads compiled .class files).
# Requires: JDK (javap), a built project (class files under bin/).
# Usage: bash ./scripts/generate_outline.sh > README_src_outline.md

echo "# Src Outline, **READ this by MULTIPLE calls if it's too large being outlined by first call**"
echo ""

# Map class files back to source files
# bin/<module>/<pkg-path>/Foo.class → src/<module>/java/<pkg-path>/Foo.java
find bin -name "*.class" | sort | while read classfile; do
    # Extract module from class path: bin/client/... or bin/main/...
    module="${classfile#bin/}"
    module="${module%%/*}"

    # Get the package path (everything after bin/<module>/)
    pkgpath="${classfile#bin/$module/}"
    pkgpath="${pkgpath%.class}.java"

    srcpath="src/$module/java/$pkgpath"

    if [ ! -f "$srcpath" ]; then
        continue
    fi

    echo "## $srcpath"

    javap -p "$classfile" 2>/dev/null | awk '
    BEGIN { in_class = 0 }

    /^Compiled from/ { next }

    # Class/interface/enum/record header (unindented, starts with lowercase letter)
    /^[a-z]/ {
        in_class = 1
        sub(/ *\{ *\r?$/, "")
        print "- " $0
        next
    }

    !in_class { next }

    # Skip synthetic lambda / access bridges
    /lambda\$|access\$/ { next }

    # Static initializer
    /static *\{ *\};/ {
        print "  - static {}"
        next
    }

    # Closing brace
    /^\}/ { in_class = 0; next }

    # Has ( → method or constructor
    /\(/ {
        sub(/;\r?$/, "")
        gsub(/^[ \t]+/, "")
        print "  - " $0
        next
    }

    # Field: type name;  pattern
    /[A-Za-z0-9_<>\[\],.? ]+ [A-Za-z_][A-Za-z0-9_]*;/ {
        sub(/;\r?$/, "")
        gsub(/^[ \t]+/, "")
        print "  - " $0
    }
    '
    echo ""
done
