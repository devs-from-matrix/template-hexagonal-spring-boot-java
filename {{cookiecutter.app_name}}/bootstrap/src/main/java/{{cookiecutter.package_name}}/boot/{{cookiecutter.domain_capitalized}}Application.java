package {{cookiecutter.package_name}}.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "{{cookiecutter.package_name}}")
public class {{cookiecutter.domain_capitalized}}Application {

  public static void main(String[] args) {
    SpringApplication.run({{cookiecutter.domain_capitalized}}Application.class, args);
  }
}
