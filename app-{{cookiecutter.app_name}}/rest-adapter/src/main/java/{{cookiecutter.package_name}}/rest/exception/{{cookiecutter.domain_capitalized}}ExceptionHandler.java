package {{cookiecutter.package_name}}.rest.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import {{cookiecutter.package_name}}.domain.exception.{{cookiecutter.domain_capitalized}}NotFoundException;
import {{cookiecutter.package_name}}.rest.generated.model.ProblemDetail;

@RestControllerAdvice(basePackages = {"{{cookiecutter.package_name}}"})
public class {{cookiecutter.domain_capitalized}}ExceptionHandler {

  @ExceptionHandler(value = {{cookiecutter.domain_capitalized}}NotFoundException.class)
  public final ResponseEntity<ProblemDetail> handle{{cookiecutter.domain_capitalized}}NotFoundException(
      final Exception exception, final WebRequest request) {
    var problem =
        ProblemDetail.builder()
            .type("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404")
            .status(HttpStatus.NOT_FOUND.value())
            .title("{{cookiecutter.domain_capitalized}} not found")
            .detail(exception.getMessage())
            .instance(((ServletWebRequest) request).getRequest().getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
  }
}
