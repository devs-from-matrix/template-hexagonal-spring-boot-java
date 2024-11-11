package {{cookiecutter.package_name}}.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Obtain{{cookiecutter.domain_capitalized}};

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class {{cookiecutter.domain_capitalized}}JpaTest {

  @Autowired private Obtain{{cookiecutter.domain_capitalized}} obtain{{cookiecutter.domain_capitalized}};

  @Test
  @DisplayName("should start the application")
  public void startup() {
    assertThat(Boolean.TRUE).isTrue();
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName(
      "given {{cookiecutter.domain_plural}} exist in database when asked should return all {{cookiecutter.domain_plural}} from database")
  public void shouldGiveMe{{cookiecutter.domain_plural_capitalized}}WhenAskedGiven{{cookiecutter.domain_capitalized}}ExistsInDatabase() {
    // Given from @Sql
    // When
    var {{cookiecutter.domain_plural}} = obtain{{cookiecutter.domain_capitalized}}.getAll{{cookiecutter.domain_plural_capitalized}}();
    // Then
    assertThat({{cookiecutter.domain_plural}})
        .isNotNull()
        .extracting("description")
        .contains("Twinkle twinkle little star");
  }

  @Test
  @DisplayName("given no {{cookiecutter.domain_plural}} exists in database when asked should return empty")
  public void shouldGiveNo{{cookiecutter.domain_capitalized}}WhenAskedGiven{{cookiecutter.domain_plural_capitalized}}DoNotExistInDatabase() {
    // When
    var {{cookiecutter.domain_plural}} = obtain{{cookiecutter.domain_capitalized}}.getAll{{cookiecutter.domain_plural_capitalized}}();
    // Then
    assertThat({{cookiecutter.domain_plural}}).isNotNull().isEmpty();
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName(
      "given {{cookiecutter.domain_plural}} exists in database when asked for {{cookiecutter.domain}} by id should return the {{cookiecutter.domain}}")
  public void shouldGiveThe{{cookiecutter.domain_capitalized}}WhenAskedByIdGivenThat{{cookiecutter.domain_capitalized}}ByThatIdExistsInDatabase() {
    // Given from @Sql
    // When
    var {{cookiecutter.domain}} = obtain{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(1L);
    // Then
    assertThat({{cookiecutter.domain}})
        .isNotNull()
        .isNotEmpty()
        .get()
        .isEqualTo({{cookiecutter.domain_capitalized}}.builder().code(1L).description("Twinkle twinkle little star").build());
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName(
      "given {{cookiecutter.domain_plural}} exists in database when asked for {{cookiecutter.domain}} by id that does not exist should give empty")
  public void shouldGiveNo{{cookiecutter.domain_capitalized}}WhenAskedByIdGivenThat{{cookiecutter.domain_capitalized}}ByThatIdDoesNotExistInDatabase() {
    // Given from @Sql
    // When
    var {{cookiecutter.domain}} = obtain{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(-1000L);
    // Then
    assertThat({{cookiecutter.domain}}).isEmpty();
  }
}
