package {{cookiecutter.package_name}}.repository;

import net.lbruun.springboot.preliquibase.PreLiquibaseAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import {{cookiecutter.package_name}}.repository.config.JpaAdapterConfig;

@SpringBootApplication
public class {{cookiecutter.domain_capitalized}}JpaAdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run({{cookiecutter.domain_capitalized}}JpaAdapterApplication.class, args);
  }

  @TestConfiguration
  @Import(JpaAdapterConfig.class)
  @ImportAutoConfiguration({PreLiquibaseAutoConfiguration.class})
  static class {{cookiecutter.domain_capitalized}}JpaTestConfig {}
}
