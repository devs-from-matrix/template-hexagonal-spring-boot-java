# {{cookiecutter.app_title}}

## Pre-requisite

- maven >= 3.8.6
- open jdk 21

## How to build?

```
mvn clean install
```

### How to build a docker image?

```
cd bootstrap && mvn compile jib:dockerBuild
```

[More information](https://cloud.google.com/java/getting-started/jib)

## How to start ?

```
cd bootstrap && mvn spring-boot:run
```

## Formatting

This project uses [git-code-format-maven-plugin](https://github.com/Cosium/git-code-format-maven-plugin) for formatting
the code per [google style guide](https://google.github.io/styleguide/javaguide.html)

### How to format ?

`mvn git-code-format:format-code`

## Validating

This project
uses [githook-maven-plugin](https://mvnrepository.com/artifact/io.github.phillipuniverse/githook-maven-plugin) which is
a maven plugin to configure and install local git hooks by running set of commands during build.

### Command to validate formatted code

`mvn git-code-format:validate-code-format`

## Contribution guidelines

We are really glad you're reading this, because we need volunteer developers to help this project come to fruition.

Request you to please read
our [contribution guidelines](https://devs-from-matrix.github.io/basic-template-repository/#/README?id=contribution-guidelines)
