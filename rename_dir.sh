#!/bin/bash
# Dry-run: show planned renames
find . -depth -type d -name '{{cookiecutter.*' -print0 | \
  while IFS= read -r -d '' d; do
    new="$(dirname "$d")/$(basename "$d" | sed 's/^{{cookiecutter\./{{/')"
    printf '%s -> %s\n' "$d" "$new"
  done

## Apply changes: actually rename (run only after verifying dry-run)
find . -depth -type d -name '{{cookiecutter.*' -print0 | \
  while IFS= read -r -d '' d; do
    new="$(dirname "$d")/$(basename "$d" | sed 's/^{{cookiecutter\./{{/')"
    if [ -e "$new" ]; then
      printf 'Skipping %s: target exists %s\n' "$d" "$new" >&2
      continue
    fi
    mv -- "$d" "$new" && printf 'Renamed %s -> %s\n' "$d" "$new"
  done
