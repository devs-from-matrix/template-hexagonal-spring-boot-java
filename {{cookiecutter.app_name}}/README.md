# {{cookiecutter.app_title}}

## Pre-requisite

- maven >= 3.8.6
- open jdk 21

## How to build ?

```
mvn clean install
```

### How to build a docker image ?

```
cd bootstrap && mvn compile jib:dockerBuild
```

[More information](https://cloud.google.com/java/getting-started/jib)

## How to start ?

```
cd bootstrap && mvn spring-boot:run
```
