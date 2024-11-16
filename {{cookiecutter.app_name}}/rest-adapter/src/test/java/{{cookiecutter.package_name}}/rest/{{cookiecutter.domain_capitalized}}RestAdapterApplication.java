package {{cookiecutter.package_name}}.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import {{cookiecutter.package_name}}.domain.port.Request{{cookiecutter.domain_capitalized}};

@SpringBootApplication
@ComponentScan(basePackages = "{{cookiecutter.package_name}}")
public class {{cookiecutter.domain_capitalized}}RestAdapterApplication {

  @MockBean private Request{{cookiecutter.domain_capitalized}} request{{cookiecutter.domain_capitalized}};

  public static void main(String[] args) {
    SpringApplication.run({{cookiecutter.domain_capitalized}}RestAdapterApplication.class, args);
  }
}
