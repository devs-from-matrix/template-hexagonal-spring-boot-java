package {{cookiecutter.package_name}}.domain.port;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};

public interface Obtain{{cookiecutter.domain_capitalized}} {

  default List<{{cookiecutter.domain_capitalized}}> getAll{{cookiecutter.domain_plural_capitalized}}() {
    {{cookiecutter.domain_capitalized}} {{cookiecutter.domain}} =
        {{cookiecutter.domain_capitalized}}.builder()
            .code(1L)
            .description(
                "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)")
            .build();
    return List.of({{cookiecutter.domain}});
  }

  default Optional<{{cookiecutter.domain_capitalized}}> get{{cookiecutter.domain_capitalized}}ByCode(@NonNull Long code) {
    return Optional.of(
        {{cookiecutter.domain_capitalized}}.builder()
            .code(1L)
            .description(
                "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)")
            .build());
  }
}
