#!/bin/bash
# This script is used to rename files and directories in the example project to that of the cookiecutter template.

# Remove previous example-tryout directory if it exists
if [ -d "example-tryout" ]; then
  rm -rf "example-tryout"
fi
# Create a new example-tryout directory and copy the example project into it
mkdir "example-tryout"
rsync -a ./example/ ./example-tryout/
cd ./example-tryout

# sets the locale for all commands run in the current shell session to the "C" locale, which is the default POSIX locale. This makes programs like sed and find treat files as raw bytes, ignoring any character encoding issues. It helps avoid errors like illegal byte sequence when processing files with mixed or unknown encodings.
export LC_ALL=C

# Detect OS and set sed inline flag
if [[ "$(uname)" == "Darwin" ]]; then
  SED_INPLACE=(-i '')
else
  SED_INPLACE=(-i)
fi

find . -type f -exec sed "${SED_INPLACE[@]}" -e s/Examples/{{cookiecutter.domain_plural_capitalized}}/g '{}' ';'
find . -depth -name '*Examples*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)Examples/\1{{cookiecutter.domain_plural_capitalized}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/examples/{{cookiecutter.domain_plural}}/g '{}' ';'
find . -depth -name '*examples*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)examples/\1{{cookiecutter.domain_plural}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/Example/{{cookiecutter.domain_capitalized}}/g '{}' ';'
find . -depth -name '*Example*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)Example/\1{{cookiecutter.domain_capitalized}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/example/{{cookiecutter.domain}}/g '{}' ';'
find . -depth -name '*example*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)example/\1{{cookiecutter.domain}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/packagename/{{cookiecutter.package_name}}/g '{}' ';'
find . -depth -name '*packagename*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)packagename/\1{{cookiecutter.package_name}}/')"; done
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/artifactName/{{cookiecutter.artifact_id}}/g '{}' ';'
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/group-id/{{cookiecutter.group_id}}/g '{}' ';'
## For the following, we need to replace EXAMPLES and EXAMPLE with the domain name
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/EXAMPLES/{{cookiecutter.domain_plural_uppercase}}/g '{}' ';'
find . -type f -exec sed "${SED_INPLACE[@]}" -e s/EXAMPLE/{{cookiecutter.domain_uppercase}}/g '{}' ';'
