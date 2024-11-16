package {{cookiecutter.package_name}}.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Obtain{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.repository.dao.{{cookiecutter.domain_capitalized}}Dao;
import {{cookiecutter.package_name}}.repository.entity.{{cookiecutter.domain_capitalized}}Entity;

public class {{cookiecutter.domain_capitalized}}Repository implements Obtain{{cookiecutter.domain_capitalized}} {

  private final {{cookiecutter.domain_capitalized}}Dao {{cookiecutter.domain}}Dao;

  public {{cookiecutter.domain_capitalized}}Repository({{cookiecutter.domain_capitalized}}Dao {{cookiecutter.domain}}Dao) {
    this.{{cookiecutter.domain}}Dao = {{cookiecutter.domain}}Dao;
  }

  @Override
  public List<{{cookiecutter.domain_capitalized}}> getAll{{cookiecutter.domain_plural_capitalized}}() {
    return {{cookiecutter.domain}}Dao.findAll().stream().map({{cookiecutter.domain_capitalized}}Entity::toModel).collect(Collectors.toList());
  }

  @Override
  public Optional<{{cookiecutter.domain_capitalized}}> get{{cookiecutter.domain_capitalized}}ByCode(Long code) {
    var {{cookiecutter.domain}}Entity = {{cookiecutter.domain}}Dao.findByCode(code);
    return {{cookiecutter.domain}}Entity.map({{cookiecutter.domain_capitalized}}Entity::toModel);
  }
}
