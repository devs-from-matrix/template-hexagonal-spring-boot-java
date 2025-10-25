#!/bin/bash
# This script is used to rename files and directories in the example project to that of the cookiecutter template.

# Remove previous example-template directory if it exists
if [ -d "example-template" ]; then
  rm -rf "example-template"
fi
# Create a new example-template directory and copy the example project into it
mkdir "example-template"
rsync -a ./example/ ./example-template/
cd ./example-template

# sets the locale for all commands run in the current shell session to the "C" locale, which is the default POSIX locale. This makes programs like sed and find treat files as raw bytes, ignoring any character encoding issues. It helps avoid errors like illegal byte sequence when processing files with mixed or unknown encodings.
export LC_ALL=C

# Detect OS and set sed inline flag
if [[ "$(uname)" == "Darwin" ]]; then
  SED_INPLACE=(-i '')
else
  SED_INPLACE=(-i)
fi

find . -type f -exec sed "${SED_INPLACE[@]}" -e s/Examples/{{domain_plural_capitalized}}/g '{}' ';'
find . -depth -name '*Examples*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)Examples/\1{{domain_plural_capitalized}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/examples/{{domain_plural}}/g '{}' ';'
find . -depth -name '*examples*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)examples/\1{{domain_plural}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/Example/{{domain_capitalized}}/g '{}' ';'
find . -depth -name '*Example*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)Example/\1{{domain_capitalized}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/example/{{domain}}/g '{}' ';'
find . -depth -name '*example*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)example/\1{{domain}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/packagename/{{package_name}}/g '{}' ';'
find . -depth -name '*packagename*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)packagename/\1{{package_name}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/artifactName/{{artifact_id}}/g '{}' ';'
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/group-id/{{group_id}}/g '{}' ';'
## For the following, we need to replace EXAMPLES and EXAMPLE with the domain name
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/EXAMPLES/{{domain_plural_uppercase}}/g '{}' ';'
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/EXAMPLE/{{domain_uppercase}}/g '{}' ';'

# Rename files to add .jinja extension
# Add .jinja extension to files except those under .github
exts=(xml yml java feature sql yaml)
for ext in "${exts[@]}"; do
  find . -type d -name .github -prune -o -type f -name "*.${ext}" -print0 | while IFS= read -r -d '' file; do
    mv "$file" "$file.jinja"
  done
done
