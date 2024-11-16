package {{cookiecutter.package_name}}.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Request{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.rest.generated.api.{{cookiecutter.domain_capitalized}}Api;
import {{cookiecutter.package_name}}.rest.generated.model.{{cookiecutter.domain_capitalized}}Info;

@RestController
public class {{cookiecutter.domain_capitalized}}Resource implements {{cookiecutter.domain_capitalized}}Api {

  private final Request{{cookiecutter.domain_capitalized}} request{{cookiecutter.domain_capitalized}};

  public {{cookiecutter.domain_capitalized}}Resource(Request{{cookiecutter.domain_capitalized}} request{{cookiecutter.domain_capitalized}}) {
    this.request{{cookiecutter.domain_capitalized}} = request{{cookiecutter.domain_capitalized}};
  }

  public ResponseEntity<{{cookiecutter.domain_capitalized}}Info> get{{cookiecutter.domain_plural_capitalized}}() {
    return ResponseEntity.ok({{cookiecutter.domain_capitalized}}Info.builder().{{cookiecutter.domain_plural}}(request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_plural_capitalized}}()).build());
  }

  public ResponseEntity<{{cookiecutter.domain_capitalized}}> get{{cookiecutter.domain_capitalized}}ByCode(@PathVariable("code") Long code) {
    return ResponseEntity.ok(request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code));
  }
}
