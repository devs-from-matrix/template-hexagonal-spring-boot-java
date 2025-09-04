# Instructions

## Context

- Language: Java version 21
- Framework / Libraries: Spring Boot / JUnit 5 
- Build Tool: Maven version >3.9.5
- Architecture: Clean Architecture / Hexagonal / Microservices
- Coding Style: Google Java Style Guide

## Overview

This is a template project for hexagonal-spring-boot-java. This project is generated using [cookiecutter](https://cookiecutter.readthedocs.io/en). The project follows a hexagonal architecture pattern and is built using Spring Boot and Java. 

The directory `example` contains the template project which can be modified by the contributors. The changes can be tested locally and then a pull request can be raised to merge the changes to the main branch. The project is also integrated with a CI pipeline which runs the tests and builds the project on every pull request.

The directory `{{cookiecutter.app_name}}` contains the generated project from `example` project. The project is generated using the script [generate-cookiecutter-template-from-example-project.sh](../generate-cookiecutter-template-from-example-project.sh) which is used to generate the cookiecutter template project. You should not modify this directory directly. Following are the keywords reserved for the template project and their equivalent replacements in `{{cookiecutter.app_name}}` project:

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

## 🔧 General Guidelines

- Use Java-idiomatic patterns and follow standard conventions (JavaBeans, package structure).
- Use proper access modifiers (private by default).
- Always include null checks and use Optional where appropriate.
- Prefer final for variables that don't change.
- Format using google-java-format or IDE rules.
- Favor readability, testability, and separation of concerns.
- Use functional programming constructs (streams, lambdas) where appropriate.
- Replace comments with clear, self-explanatory code.

## File Structure And Patterns

As you are only authorized to modify the `example` directory, here is a brief overview of its structure:

```
example/
├── bootstrap/               # Spring Boot application entry point and configuration
├── domain/                  # Domain logic and business rules implementation
├── domain-api/              # Domain interfaces and models (API for domain layer)
├── jpa-adapter/             # JPA persistence adapter: repositories, entities, configs
├── rest-adapter/            # REST API adapter: controllers, request/response models
├── acceptance-test/         # Cucumber-based acceptance tests
```
### domain-api

Defines the public interfaces and models for the domain layer. This includes ports i.e. contracts to interact with the domain and the external services and domain models. It should not contain any implementation details or dependencies on frameworks. This layer is purely abstract and should not depend on any other layer. Ensure that No class in this layer depends on any other layer. The `pom.xml` of this module should not have any dependencies except for testing libraries.

```
example/domain-api/
├── pom.xml                                         # Maven build configuration for the domain-api module
├── src/
│   ├── main/
│   │   └── java/
│   │       └── packagename/
│   │           └── domain/
│   │               ├── model/
│   │               │   └── Example.java             # Domain model
│   │               └── port/
│   │                   ├── ObtainExample.java       # Port for accessing external services
│   │                   └── RequestExample.java      # Port for accessing the domain
│   └── test/
│       └── java/
│           └── packagename/
│               └── domain/
│                   └── port/
│                       └── ObtainExampleTest.java   # Unit tests for domain-api interfaces
```

#### ✅ Patterns to Follow

- The domain model class name needs to be more functional and should be placed under `model` package.
- The contracts for accessing the domain and the contracts for the domain to access the outside world should be placed under `port` package.
- The name of the contract for accessing the domain should be named as `Request*` and the name of the contract for the domain to access the outside world should be named as `Obtain*`.
- Ensure all interfaces are pure abstractions, with no implementation details.
- Use default methods in interfaces only for providing simple, hard-coded stubs for testing or documentation.
- The `pom.xml` should only include dependencies for testing (e.g., JUnit, Mockito, AssertJ).

#### 🚫 Patterns to Avoid

- Do not include any implementation logic in this layer.
- Do not use framework-specific annotations (Spring, JPA, etc.) in domain models or interfaces.
- Do not depend on any other layer (no imports from domain, jpa-adapter, rest-adapter, etc.).

### domain

Implements the core business logic and rules by adhering to the contracts of `domain-api`. It contains service implementations, domain models, and business validations. This layer should not depend on any frameworks or external libraries. It should only depend on the `domain-api` layer. Ensure that No class in this layer depends on any other layer except `domain-api`. The `pom.xml` of this module should not have any dependencies except for testing libraries.

```
example/domain/
├── pom.xml                                               # Maven build configuration for the domain module
├── src/
│   ├── main/
│   │   └── java/
│   │       └── packagename/
│   │           └── domain/
│   │               ├── ExampleDomain.java                 # Implements business logic
│   │               └── exception/
│   │                   └── ExampleNotFoundException.java  # Domain specific exception
│   └── test/
│       └── java/
│           └── packagename/
│               └── AcceptanceTest.java                     # Unit tests for domain logic
```

#### ✅ Patterns to Follow

- Name domain classes as `*Domain.java`.
- Keep all code in this layer framework-agnostic
- Throw domain-specific exceptions for error cases (e.g., not found). Place domain-specific exceptions in an `exception/` subpackage and name as `*Exception.java`.
- Implement only business logic and rules; do not include persistence or framework-specific code.
- Use constructor injection for dependencies (e.g., ports from `domain-api`).
- Depend only on interfaces and models from `domain-api`.
- Keep all code framework-agnostic and library-agnostic.
- Use clear, descriptive method and class names that reflect domain concepts.
- Favor readability, separation of concerns, and testability.
- Use `slf4j` for logging every entry and exit of the methods with proper log levels. Also, log exceptions with proper log levels and logging of exception should NEVER be skipped.

#### 🚫 Patterns to Avoid

- Do not depend on any layer except `domain-api`.
- Do not use framework-specific annotations (Spring, JPA, etc.) in domain classes.
- Do not log sensitive information in domain classes.
- Do not use static utility methods for mapping or conversion.
- Do not modify domain models or interfaces from other layers in this module.
- Do not manually manage transactions or database connections.
- Do not include documentation comments except for public APIs if needed.
- Do not add business logic to test classes; keep tests focused on verification.

### jpa-adapter

Implements the persistence logic using JPA. It contains repository implementations, JPA entities, and database configurations. This layer depends on `domain-api` for repository interfaces and models. It uses liquibase to easily manage the database schema and data migrations. It also uses envers for auditing the entities.

```
example/jpa-adapter/
├── pom.xml                                           # Maven build configuration for the JPA adapter module
├── src/
│   ├── main/
│   │   └── java/
│   │       └── packagename/
│   │           └── repository/
│   │               ├── ExampleRepository.java        # Implements domain repository interface using JPA
│   │               ├── config/
│   │               │   └── JpaAdapterConfig.java     # Spring configuration for JPA repositories and entities
│   │               ├── dao/
│   │               │   └── ExampleDao.java           # Spring Data JPA DAO interface for entities
│   │               └── entity/
│   │                   ├── ExampleEntity.java        # JPA entity mapping for table
│   │                   └── EnversRevisionEntity.java # JPA entity for audit revision
│   └── test/
│       └── java/
│           └── packagename/
│               └── repository/
│                   ├── ExampleJpaTest.java          # JPA integration tests for repository logic
│                   └── ExampleJpaAdapterApplication.java # Test bootstrap for JPA adapter
```

#### ✅ Patterns to Follow

- Name repository classes as `*Repository.java`.
- Name DAO interfaces as `*Dao.java` and extend `JpaRepository` (and optionally `RevisionRepository` for auditing).
- Name JPA entity classes as `*Entity.java` and annotate with `@Entity`,`@Table`; use Lombok for boilerplate.
- Name configuration classes as `*Config.java` and annotate with `@Configuration`
- Use Dependency Injection ONLY with constructor injection and NOT via `@Autowired` on fields as it would make the code less testable.
- Repository implementations should only depend on domain models and interfaces from `domain-api`.
- Use method references and streams for mapping entities to domain models.
- Place integration tests in src/test/java/packagename/repository/ and name as `*JpaTest.java`.
- Use `@DataJpaTest` and `@ActiveProfiles("test")` for JPA integration tests.
- Use SQL scripts for test data setup in integration tests.
- Pre-liquibase script placed at `src/main/resources/preliquibase/default.sql` should be used for database schema and data migrations. The changelog files should be placed in `src/main/resources/db/changelog/` directory. 
- The changelog files should follow the naming convention `<datetimestamp-entityname>.xml`. Each changelog file should contain changesets for a single entity only and also audit tables should be created for each entity table. The audit tables should have the same structure as the entity table with additional columns for revision information. The audit tables should be named as `<entityname>_AUD`.
- Use `slf4j` for logging every entry and exit of the methods with proper log levels. Also, log exceptions with proper log levels and logging of exception should NEVER be skipped.

#### 🚫 Patterns to Avoid

- Do not include business logic in repository or entity classes.
- Do not use field injection (`@Autowired` on fields); always use constructor injection.
- Do not depend on any layer except `domain-api` and Spring Data JPA.
- Do not modify domain models or interfaces in this layer.
- Do not log sensitive information in repository classes.
- Do not use documentation comments in entity classes
- Do not manually manage transactions; rely on Spring Data JPA for transaction management.
- Do not include framework-specific annotations (other than JPA/Spring Data) in domain models.
- Do not use static utility methods for mapping; prefer instance or method references.

### rest-adapter

Implements the REST API layer. It contains controllers, request/response models, and API configurations. This layer depends on `domain-api` for service interfaces and models.  

```
example/rest-adapter/
├── pom.xml                                               # Maven build configuration for the rest-adapter module
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── packagename/
│   │   │       └── rest/
│   │   │           ├── ExampleResource.java              # REST controller implementing API endpoints
│   │   │           └── exception/
│   │   │               └── ExampleExceptionHandler.java  # Handles REST exceptions and maps to ProblemDetail
│   │   └── resources/
│   │       └── api/
│   │           └── openapi.yaml                          # OpenAPI specification for REST API
│   └── test/
│       └── java/
│           └── packagename/
│               └── rest/
│                   ├── ExampleResourceTest.java           # Integration tests for REST endpoints
│                   └── ExampleRestAdapterApplication.java # Test application bootstrap for REST adapter
```

#### ✅ Patterns to Follow

- The RestController classes should be named as `*Resource.java`.
- The `pom.xml` of this module should have dependencies only for spring-boot-starter, open api specification and testing libraries.
- This module is built with design first approach. The API specification is defined using OpenAPI (Swagger) and the code is generated using [openapi-generator-maven-plugin](https://openapi-generator.tech/docs/plugins/). The API specification file is located at `src/main/resources/api/openapi.yaml`. Any changes to the API should be made in this file and the code should be regenerated using the command `mvn clean compile`.
- The RestController classes should implement the interfaces generated by the OpenAPI generator. The request and response models should be generated by the OpenAPI generator and should not be modified manually unless absolutely necessary.
- Use `@SpringBootTest` and `@ActiveProfiles("test")` for integration tests.
- Use method references and streams for mapping domain models to response objects.
- Use Dependency Injection ONLY with constructor injection and NOT via `@Autowired` on fields as it would make the code less testable.
- Handle exceptions with `@RestControllerAdvice` annotated class and return `ResponseEntity<ProblemDetail>` with proper error code and body that adhere to `ProblemDetail` which has the properties defined in [RFC 7807](https://datatracker.ietf.org/doc/html/rfc7807).
- Use `slf4j` for logging every entry and exit of the methods with proper log levels. Also, log exceptions with proper log levels and logging of exception should NEVER be skipped.
- Use ONLY dependencies required for Spring Boot, OpenAPI, and testing in pom.xml.

#### 🚫 Patterns to Avoid

- Ensure that No class in this layer depends on any other layer except `domain-api`.
- Do not include business logic in controller or exception handler classes.
- Do not use field injection (`@Autowired` on fields); always use constructor injection.
- Do not depend on any layer except domain-api and generated OpenAPI models/interfaces.
- Do not modify generated request/response models unless absolutely necessary.
- Do not log sensitive information in controllers or exception handlers.
- Do not manually manage transactions in this layer.
- Do not use static utility methods for mapping; prefer instance or method references.
- Do not add documentation comments to controller classes; documentation should be generated from OpenAPI spec.
- Do not expose internal domain exceptions directly; always map to standardized error responses.

### acceptance-test

Contains Cucumber-based acceptance tests for the template project. It verifies the application's behavior end-to-end by simulating user scenarios defined in Gherkin feature files. Step definitions implement the test logic, interacting with the application's REST API and database to ensure all layers work together as expected. This directory helps ensure that business requirements are met and prevents regressions by running automated acceptance tests in the CI pipeline.

```
example/acceptance-test/
├── pom.xml                                             # Maven build configuration for acceptance tests
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── packagename/
│   │   │       └── cucumber/
│   │   │           ├── ExampleStepDef.java             # Step definitions for Cucumber scenarios
│   │   │           ├── SpringCucumberTestConfig.java   # Spring Boot test config for Cucumber
│   │   │           └── RunCucumberExampleTest.java # JUnit runner for Cucumber tests
│   │   └── resources/
│   │       └── features/
│   │           └── example.feature                 # Gherkin feature files
```

#### ✅ Patterns to Follow

- Name step definition classes as `*StepDef.java` and place them in the `cucumber/` package.
- Place feature files in `src/test/resources/features/` and name them as `<domain>.feature`.
- Use `@SpringBootTest` and `@ActiveProfiles("test")` for end-to-end with Spring Boot.
- Use constructor injection for dependencies in step definition classes.
- Use Cucumber hooks (`Before`, `After`) for test setup and teardown.
- Use `DataTableType` for mapping Gherkin tables to domain objects.
- Keep step definitions readable and focused on scenario actions, delegating logic to domain or adapter layers.
- Place configuration classes for Cucumber in the same package as step definitions.
- Use assertions from AssertJ or similar libraries for test verification.
- Use method references and streams for mapping and assertions.
- Keep test data setup isolated and repeatable (e.g., clean database before/after each scenario).

#### 🚫 Patterns to Avoid

- Do not add business logic to test classes; keep tests focused on verification and orchestration.
- Do not depend on any layer except domain, repository, and REST adapter for test orchestration.
- Do not log sensitive information in step definitions.
- Do not manually manage transactions or database connections in step definitions.
- Do not use static utility methods for mapping; prefer instance or method references.
- Do not modify domain models or interfaces in this layer.
- Do not add documentation comments to step definition classes; keep documentation in feature files.
- Do not use hard-coded test data outside of feature files or test setup methods.

### bootstrap

Contains the main Spring Boot application class and configuration beans to bootstrap the application. It wires together the domain and adapters. This is the entry point of the application and should not contain business logic. The utmost thing this layer should do is to import the configuration classes for the adapters and domain.

```
bootstrap/
├── pom.xml                                           # Maven build configuration for the bootstrap module
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── packagename/
│   │   │       ├── boot/
│   │   │       │   ├── ExampleApplication.java      # Main Spring Boot application entry point
│   │   │       │   └── config/
│   │   │       │       └── BootstrapConfig.java     # Configuration class wiring domain and adapters
│   │   └── resources/
│   │       └── application.yml                      # Spring Boot application configuration
│   └── test/
│       └── java/
│           └── packagename/
│               └── boot/
│                   └── ExampleApplicationTest.java  # Unit/integration tests for application startup
```

#### ✅ Patterns to Follow

- Name the main application class as `*Application.java` and place it in the `boot/` package. 
- Place configuration classes in a `config/` subpackage and name them as `*Config.java`.
- Use `@SpringBootApplication` for the main class and `@Configuration` for config classes.
- Use `@ComponentScan` to scan the base package for beans.
- Use constructor injection for dependencies in configuration classes.
- Import adapter configuration classes using `@Import`.
- Place application configuration files (e.g., `application.yml`) in `src/main/resources/`.
- Keep the bootstrap layer free of business logic; only wire together domain and adapters.
- Use only dependencies required for Spring Boot and testing in `pom.xml`.

#### 🚫 Patterns to Avoid

- Do not include business logic in the bootstrap layer.
- Do not use field injection (`@Autowired` on fields).
- Do not depend on any layer except configuration and adapter beans.
- Do not log sensitive information in bootstrap classes.
- Do not manually manage transactions or database connections.
- Do not use static utility methods for wiring beans.
- Do not modify domain models or interfaces in this layer.
- Do not add documentation comments to bootstrap classes; keep documentation in README or config files.

## Contribution Guidelines

- Follow the guideline mention in [File Structure And Patterns](#file-structure-and-patterns).
- All contributions must use conventional commit messages. See https://www.conventionalcommits.org/en/v1.0.0/ for details. Example commit message: `feat(domain): add validation for example code`
- Commit messages should always be in lower case except for proper nouns.
- Commit messages should be prefixed with the module name in parentheses. Valid module names are: `bootstrap`, `domain`, `domain-api`, `jpa-adapter`, `rest-adapter`, `acceptance-test`.
- Commit message should not exceed 74 characters in length.
- The body of the commit message should provide additional context about the change and optionally reference related issues.
- Branch name should start with `feature/`, `bugfix/`, `hotfix/`, or `chore/` followed by issue number and short description not exceeding 20 characters.
- Ensure all tests pass before submitting a pull request.
- Provide clear descriptions for pull requests.
- Keep changes focused and avoid mixing unrelated features or fixes in a single pull request.