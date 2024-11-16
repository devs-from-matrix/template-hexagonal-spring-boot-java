package {{cookiecutter.package_name}}.domain;

import java.util.List;
import lombok.NonNull;
import {{cookiecutter.package_name}}.domain.exception.{{cookiecutter.domain_capitalized}}NotFoundException;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Obtain{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Request{{cookiecutter.domain_capitalized}};

public class {{cookiecutter.domain_capitalized}}Domain implements Request{{cookiecutter.domain_capitalized}} {

  private final Obtain{{cookiecutter.domain_capitalized}} obtain{{cookiecutter.domain_capitalized}};

  public {{cookiecutter.domain_capitalized}}Domain() {
    this(new Obtain{{cookiecutter.domain_capitalized}}() {});
  }

  public {{cookiecutter.domain_capitalized}}Domain(Obtain{{cookiecutter.domain_capitalized}} obtain{{cookiecutter.domain_capitalized}}) {
    this.obtain{{cookiecutter.domain_capitalized}} = obtain{{cookiecutter.domain_capitalized}};
  }

  @Override
  public List<{{cookiecutter.domain_capitalized}}> get{{cookiecutter.domain_plural_capitalized}}() {
    return obtain{{cookiecutter.domain_capitalized}}.getAll{{cookiecutter.domain_plural_capitalized}}();
  }

  @Override
  public {{cookiecutter.domain_capitalized}} get{{cookiecutter.domain_capitalized}}ByCode(@NonNull Long code) {
    var {{cookiecutter.domain}} = obtain{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code);
    return {{cookiecutter.domain}}.orElseThrow(() -> new {{cookiecutter.domain_capitalized}}NotFoundException(code));
  }
}
