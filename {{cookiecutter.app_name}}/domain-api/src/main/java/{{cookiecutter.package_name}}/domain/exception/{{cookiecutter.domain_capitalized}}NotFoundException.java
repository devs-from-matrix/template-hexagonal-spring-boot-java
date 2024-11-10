package {{cookiecutter.package_name}}.domain.exception;

public class {{cookiecutter.domain_capitalized}}NotFoundException extends RuntimeException {

  public {{cookiecutter.domain_capitalized}}NotFoundException(Long id) {
    super("{{cookiecutter.domain_capitalized}} with code " + id + " does not exist");
  }
}
