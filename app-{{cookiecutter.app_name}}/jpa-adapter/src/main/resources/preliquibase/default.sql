CREATE SCHEMA IF NOT EXISTS ${spring.liquibase.liquibase-schema:LIQUIBASE};
CREATE SCHEMA IF NOT EXISTS ${spring.liquibase.default-schema:{{cookiecutter.domain_uppercase}}};
CREATE SCHEMA IF NOT EXISTS ${spring.jpa.properties.org.hibernate.envers:{{cookiecutter.domain_uppercase}}_AUDIT};