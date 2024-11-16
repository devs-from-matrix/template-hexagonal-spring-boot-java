package {{cookiecutter.package_name}}.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import {{cookiecutter.package_name}}.domain.{{cookiecutter.domain_capitalized}}Domain;
import {{cookiecutter.package_name}}.domain.port.Obtain{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Request{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.repository.config.JpaAdapterConfig;

@Configuration
@Import(JpaAdapterConfig.class)
public class BootstrapConfig {

  @Bean
  public Request{{cookiecutter.domain_capitalized}} getRequest{{cookiecutter.domain_capitalized}}(Obtain{{cookiecutter.domain_capitalized}} obtain{{cookiecutter.domain_capitalized}}) {
    return new {{cookiecutter.domain_capitalized}}Domain(obtain{{cookiecutter.domain_capitalized}});
  }
}
