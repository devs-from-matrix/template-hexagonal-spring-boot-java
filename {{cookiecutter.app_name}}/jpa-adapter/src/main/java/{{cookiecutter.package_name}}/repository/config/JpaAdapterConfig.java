package {{cookiecutter.package_name}}.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import {{cookiecutter.package_name}}.domain.port.Obtain{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.repository.{{cookiecutter.domain_capitalized}}Repository;
import {{cookiecutter.package_name}}.repository.dao.{{cookiecutter.domain_capitalized}}Dao;

@Configuration
@EntityScan("{{cookiecutter.package_name}}.repository.entity")
@EnableJpaRepositories(
    basePackages = "{{cookiecutter.package_name}}.repository.dao",
    repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class JpaAdapterConfig {

  @Bean
  public Obtain{{cookiecutter.domain_capitalized}} get{{cookiecutter.domain_capitalized}}Repository({{cookiecutter.domain_capitalized}}Dao {{cookiecutter.domain}}Dao) {
    return new {{cookiecutter.domain_capitalized}}Repository({{cookiecutter.domain}}Dao);
  }
}
