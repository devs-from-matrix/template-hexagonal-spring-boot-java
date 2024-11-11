# Template project for hexagonal-spring-boot-java

This is a template project for hexagonal-spring-boot-java. This project is generated using [cookiecutter](https://cookiecutter.readthedocs.io/en).

## Pre-requisites

- [cookiecutter](https://cookiecutter.readthedocs.io/en/stable/README.html#installation)

## How to build ?

```
cookiecutter . --config-file=test-config.yml --no-input
```

## How to contribute ?

- The folder `{{cookiecutter.app_name}}` hosts the template project.
- You can check the various patterns used in [cookiecutter.json](cookiecutter.json)
- For testing it locally, you can use the following steps:
    ```shell
    cookiecutter . --config-file=test-config.yml --no-input
    ```
    - The generated project will be in the folder `cart-service`
- You need to raise a PR to the main branch with the changes. The CI automatically runs the tests and checks the build status by creating the project referencing the default configuration i.e. `--config-file=test-config.yml`.
