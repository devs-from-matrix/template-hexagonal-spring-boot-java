package {{cookiecutter.package_name}}.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import {{cookiecutter.package_name}}.domain.exception.{{cookiecutter.domain_capitalized}}NotFoundException;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Request{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.rest.generated.model.{{cookiecutter.domain_capitalized}}Info;
import {{cookiecutter.package_name}}.rest.generated.model.ProblemDetail;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {{cookiecutter.domain_capitalized}}RestAdapterApplication.class, webEnvironment = RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class {{cookiecutter.domain_capitalized}}ResourceTest {

  private static final String LOCALHOST = "http://localhost:";
  private static final String API_URI = "/api/v1/{{cookiecutter.domain_plural}}";
  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;
  @Autowired private Request{{cookiecutter.domain_capitalized}} request{{cookiecutter.domain_capitalized}};

  @Test
  @DisplayName("should start the rest adapter application")
  public void startup() {
    assertThat(Boolean.TRUE).isTrue();
  }

  @Test
  @DisplayName("should give {{cookiecutter.domain_plural}} when asked for {{cookiecutter.domain_plural}} with the support of domain stub")
  public void obtain{{cookiecutter.domain_plural_capitalized}}FromDomainStub() {
    // Given
    var {{cookiecutter.domain}} = {{cookiecutter.domain_capitalized}}.builder().code(1L).description("Johnny Johnny Yes Papa !!").build();
    Mockito.lenient().when(request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_plural_capitalized}}()).thenReturn(List.of({{cookiecutter.domain}}));
    // When
    var url = LOCALHOST + port + API_URI;
    var responseEntity = restTemplate.getForEntity(url, {{cookiecutter.domain_capitalized}}Info.class);
    // Then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody().get{{cookiecutter.domain_plural_capitalized}}())
        .isNotEmpty()
        .extracting("description")
        .contains("Johnny Johnny Yes Papa !!");
  }

  @Test
  @DisplayName(
      "should give the {{cookiecutter.domain}} when asked for an {{cookiecutter.domain}} by code with the support of domain stub")
  public void obtain{{cookiecutter.domain_capitalized}}ByCodeFromDomainStub() {
    // Given
    var code = 1L;
    var description = "Johnny Johnny Yes Papa !!";
    var {{cookiecutter.domain}} = {{cookiecutter.domain_capitalized}}.builder().code(code).description(description).build();
    Mockito.lenient().when(request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code)).thenReturn({{cookiecutter.domain}});
    // When
    var url = LOCALHOST + port + API_URI + "/" + code;
    var responseEntity = restTemplate.getForEntity(url, {{cookiecutter.domain_capitalized}}.class);
    // Then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody()).isEqualTo({{cookiecutter.domain}});
  }

  @Test
  @DisplayName(
      "should give exception when asked for an {{cookiecutter.domain}} by code that does not exists with the support of domain stub")
  public void shouldGiveExceptionWhenAskedForAn{{cookiecutter.domain_capitalized}}ByCodeFromDomainStub() {
    // Given
    var code = -1000L;
    Mockito.lenient()
        .when(request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code))
        .thenThrow(new {{cookiecutter.domain_capitalized}}NotFoundException(code));
    // When
    var url = LOCALHOST + port + API_URI + "/" + code;
    var responseEntity = restTemplate.getForEntity(url, ProblemDetail.class);
    // Then
    var expectedProblemDetail =
        ProblemDetail.builder()
            .type("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404")
            .status(HttpStatus.NOT_FOUND.value())
            .detail("{{cookiecutter.domain_capitalized}} with code -1000 does not exist")
            .instance("/api/v1/{{cookiecutter.domain_plural}}/-1000")
            .title("{{cookiecutter.domain_capitalized}} not found")
            .build();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody())
        .usingRecursiveComparison()
        .ignoringFields("timestamp")
        .isEqualTo(expectedProblemDetail);
    assertThat(responseEntity.getBody().getTimestamp())
        .isCloseTo(LocalDateTime.now(), within(100L, ChronoUnit.SECONDS));
  }
}
