# Template project for hexagonal-spring-boot-java

This is a template project for hexagonal-spring-boot-java. This project is generated using [cookiecutter](https://cookiecutter.readthedocs.io/en).

## Pre-requisites

- [cookiecutter](https://cookiecutter.readthedocs.io/en/stable/README.html#installation)
- Jdk 21
- Maven >3.9.5

## How to contribute ?

```mermaid
flowchart LR
        A(["User"])
        A --> B["Updates 'example' project"]
        B --> C["Raise PR to main branch"]
        C --> D["CI pipeline"]
        D --> E
        E(["Merged to main"])
```

### example

This directory contains the maven hexagonal-spring-boot-java template project. This will be used to generate template project for cookiecutter. This project is used for the contributors to make their changes and test it locally. 

Following are the keywords reserved for the template project and their equivalent replacements in {{cookiecutter.app_name}} project:

| Keyword      | Replacement                                 |
|--------------|---------------------------------------------|
| Examples     | {{cookiecutter.domain_plural_capitalized}}  |
| examples     | {{cookiecutter.domain_plural}}              |
| Example      | {{cookiecutter.domain_capitalized}}         |
| example      | {{cookiecutter.domain}}                     |
| packagename  | {{cookiecutter.package_name}}               |
| artifactName | {{cookiecutter.artifact_id}}                |
| group-id     | {{cookiecutter.group_id}}                   |
| EXAMPLES     | {{cookiecutter.domain_plural_uppercase}}    |
| EXAMPLE      | {{cookiecutter.domain_uppercase}}           |

Refer script [generate-cookiecutter-template-from-example-project.sh](generate-cookiecutter-template-from-example-project.sh) which is used to generate the cookiecutter template project. This script is also being used by [`.github/workflows/ci.yaml`](.github/workflows/ci.yaml) pipeline too.

### {{cookiecutter.app_name}}

> NOTE: DO NOT MODIFY THIS DIRECTORY DIRECTLY

This directory contains the generated project from `example` project. The project is generated using the script [generate-cookiecutter-template-from-example-project.sh](generate-cookiecutter-template-from-example-project.sh) which is used to generate the cookiecutter template project.
