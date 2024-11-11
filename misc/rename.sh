find . -type f -exec sed -e s/Examples/{{cookiecutter.domain_plural_capitalized}}/g -i '' '{}' ';'
find . -depth -name '*Examples*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)Examples/\1{{cookiecutter.domain_plural_capitalized}}/')"; done
find . -type f -exec sed -e s/examples/{{cookiecutter.domain_plural}}/g -i '' '{}' ';'
find . -depth -name '*examples*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)examples/\1{{cookiecutter.domain_plural}}/')"; done
find . -type f -exec sed -e s/Example/{{cookiecutter.domain_capitalized}}/g -i '' '{}' ';'
find . -depth -name '*Example*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)Example/\1{{cookiecutter.domain_capitalized}}/')"; done
find . -type f -exec sed -e s/example/{{cookiecutter.domain}}/g -i '' '{}' ';'
find . -depth -name '*example*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)example/\1{{cookiecutter.domain}}/')"; done
find . -type f -exec sed -e s/packagename/{{cookiecutter.package_name}}/g -i '' '{}' ';'
find . -depth -name '*packagename*' -print0|while IFS= read -rd '' f; do mv -i "$f" "$(echo "$f"|sed -E 's/(.*)packagename/\1{{cookiecutter.package_name}}/')"; done
find . -type f -exec sed -e s/artifactName/{{cookiecutter.artifact_id}}/g -i '' '{}' ';'
# For the following, we need to replace EXAMPLES and EXAMPLE with the domain name
find . -type f -exec sed -e s/EXAMPLES/{{cookiecutter.domain_plural_uppercase}}/g -i '' '{}' ';'
find . -type f -exec sed -e s/EXAMPLE/{{cookiecutter.domain_uppercase}}/g -i '' '{}' ';'