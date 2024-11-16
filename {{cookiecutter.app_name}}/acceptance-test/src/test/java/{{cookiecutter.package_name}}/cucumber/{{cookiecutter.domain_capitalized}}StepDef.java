package {{cookiecutter.package_name}}.cucumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.cucumber.java8.HookNoArgsBody;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.repository.dao.{{cookiecutter.domain_capitalized}}Dao;
import {{cookiecutter.package_name}}.repository.entity.{{cookiecutter.domain_capitalized}}Entity;
import {{cookiecutter.package_name}}.rest.generated.model.{{cookiecutter.domain_capitalized}}Info;
import {{cookiecutter.package_name}}.rest.generated.model.ProblemDetail;

public class {{cookiecutter.domain_capitalized}}StepDef implements En {

  private static final String LOCALHOST = "http://localhost:";
  private static final String API_URI = "/api/v1/{{cookiecutter.domain_plural}}";
  @LocalServerPort private int port;
  private ResponseEntity responseEntity;

  public {{cookiecutter.domain_capitalized}}StepDef(TestRestTemplate restTemplate, {{cookiecutter.domain_capitalized}}Dao {{cookiecutter.domain}}Dao) {

    DataTableType(
        (Map<String, String> row) ->
            {{cookiecutter.domain_capitalized}}.builder()
                .code(Long.parseLong(row.get("code")))
                .description(row.get("description"))
                .build());
    DataTableType(
        (Map<String, String> row) ->
            {{cookiecutter.domain_capitalized}}Entity.builder()
                .code(Long.parseLong(row.get("code")))
                .description(row.get("description"))
                .build());

    Before((HookNoArgsBody) {{cookiecutter.domain}}Dao::deleteAll);
    After((HookNoArgsBody) {{cookiecutter.domain}}Dao::deleteAll);

    Given(
        "the following {{cookiecutter.domain_plural}} exists in the library",
        (DataTable dataTable) -> {
          List<{{cookiecutter.domain_capitalized}}Entity> poems = dataTable.asList({{cookiecutter.domain_capitalized}}Entity.class);
          {{cookiecutter.domain}}Dao.saveAll(poems);
        });

    When(
        "user requests for all {{cookiecutter.domain_plural}}",
        () -> {
          String url = LOCALHOST + port + API_URI;
          responseEntity = restTemplate.getForEntity(url, {{cookiecutter.domain_capitalized}}Info.class);
        });

    When(
        "user requests for {{cookiecutter.domain_plural}} by code {string}",
        (String code) -> {
          String url = LOCALHOST + port + API_URI + "/" + code;
          responseEntity = restTemplate.getForEntity(url, {{cookiecutter.domain_capitalized}}.class);
        });

    When(
        "user requests for {{cookiecutter.domain_plural}} by id {string} that does not exists",
        (String code) -> {
          String url = LOCALHOST + port + API_URI + "/" + code;
          responseEntity = restTemplate.getForEntity(url, ProblemDetail.class);
        });

    Then(
        "the user gets an exception {string}",
        (String exception) -> {
          assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
          var actualResponse = (ProblemDetail) responseEntity.getBody();
          var expectedProblemDetail =
              ProblemDetail.builder()
                  .type("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404")
                  .status(HttpStatus.NOT_FOUND.value())
                  .detail("{{cookiecutter.domain_capitalized}} with code 10000 does not exist")
                  .instance("/api/v1/{{cookiecutter.domain_plural}}/10000")
                  .title("{{cookiecutter.domain_capitalized}} not found")
                  .build();
          assertThat(actualResponse).isNotNull();
          assertThat(actualResponse)
              .usingRecursiveComparison()
              .ignoringFields("timestamp")
              .isEqualTo(expectedProblemDetail);
          assertThat(actualResponse.getTimestamp())
              .isCloseTo(LocalDateTime.now(), within(100L, ChronoUnit.SECONDS));
        });

    Then(
        "the user gets the following {{cookiecutter.domain_plural}}",
        (DataTable dataTable) -> {
          List<{{cookiecutter.domain_capitalized}}> expected{{cookiecutter.domain_plural_capitalized}} = dataTable.asList({{cookiecutter.domain_capitalized}}.class);
          assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
          Object body = responseEntity.getBody();
          assertThat(body).isNotNull();
          if (body instanceof {{cookiecutter.domain_capitalized}}Info) {
            assertThat((({{cookiecutter.domain_capitalized}}Info) body).get{{cookiecutter.domain_plural_capitalized}}())
                .isNotEmpty()
                .extracting("description")
                .containsAll(
                    expected{{cookiecutter.domain_plural_capitalized}}.stream()
                        .map({{cookiecutter.domain_capitalized}}::getDescription)
                        .collect(Collectors.toList()));
          } else if (body instanceof {{cookiecutter.domain_capitalized}}) {
            assertThat(body)
                .isNotNull()
                .extracting("description")
                .isEqualTo(expected{{cookiecutter.domain_plural_capitalized}}.get(0).getDescription());
          }
        });
  }
}
