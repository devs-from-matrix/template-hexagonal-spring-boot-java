package {{cookiecutter.package_name}}.domain.port;

import java.util.List;
import lombok.NonNull;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};

public interface Request{{cookiecutter.domain_capitalized}} {

  List<{{cookiecutter.domain_capitalized}}> get{{cookiecutter.domain_plural_capitalized}}();

  {{cookiecutter.domain_capitalized}} get{{cookiecutter.domain_capitalized}}ByCode(@NonNull Long code);
}
