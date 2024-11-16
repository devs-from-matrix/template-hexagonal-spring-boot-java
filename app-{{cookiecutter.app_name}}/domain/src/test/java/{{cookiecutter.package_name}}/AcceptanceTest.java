package {{cookiecutter.package_name}};

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import {{cookiecutter.package_name}}.domain.{{cookiecutter.domain_capitalized}}Domain;
import {{cookiecutter.package_name}}.domain.exception.{{cookiecutter.domain_capitalized}}NotFoundException;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};
import {{cookiecutter.package_name}}.domain.port.Obtain{{cookiecutter.domain_capitalized}};

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {

  @Test
  @DisplayName("should be able to get {{cookiecutter.domain_plural}} when asked for {{cookiecutter.domain_plural}} from hard coded {{cookiecutter.domain_plural}}")
  public void get{{cookiecutter.domain_plural_capitalized}}FromHardCoded() {
    /*
       Request{{cookiecutter.domain_capitalized}}    - left side port
       {{cookiecutter.domain_capitalized}}Domain     - hexagon (domain)
       Obtain{{cookiecutter.domain_capitalized}}     - right side port
    */
    var request{{cookiecutter.domain_capitalized}} = new {{cookiecutter.domain_capitalized}}Domain(); // the {{cookiecutter.domain}} is hard coded
    var {{cookiecutter.domain_plural}} = request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_plural_capitalized}}();
    assertThat({{cookiecutter.domain_plural}})
        .hasSize(1)
        .extracting("description")
        .contains(
            "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)");
  }

  @Test
  @DisplayName("should be able to get {{cookiecutter.domain_plural}} when asked for {{cookiecutter.domain_plural}} from stub")
  public void get{{cookiecutter.domain_plural_capitalized}}FromMockedStub(@Mock Obtain{{cookiecutter.domain_capitalized}} obtain{{cookiecutter.domain_capitalized}}) {
    // Stub
    var {{cookiecutter.domain}} =
        {{cookiecutter.domain_capitalized}}.builder()
            .code(1L)
            .description(
                "I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)")
            .build();
    Mockito.lenient().when(obtain{{cookiecutter.domain_capitalized}}.getAll{{cookiecutter.domain_plural_capitalized}}()).thenReturn(List.of({{cookiecutter.domain}}));
    // hexagon
    var request{{cookiecutter.domain_capitalized}} = new {{cookiecutter.domain_capitalized}}Domain(obtain{{cookiecutter.domain_capitalized}});
    var {{cookiecutter.domain_plural}} = request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_plural_capitalized}}();
    assertThat({{cookiecutter.domain_plural}})
        .hasSize(1)
        .extracting("description")
        .contains(
            "I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)");
  }

  @Test
  @DisplayName("should be able to get {{cookiecutter.domain}} when asked for {{cookiecutter.domain}} by id from stub")
  public void get{{cookiecutter.domain_capitalized}}ByIdFromMockedStub(@Mock Obtain{{cookiecutter.domain_capitalized}} obtain{{cookiecutter.domain_capitalized}}) {
    // Given
    // Stub
    var code = 1L;
    var description =
        "I want to sleep\\r\\nSwat the flies\\r\\nSoftly, please.\\r\\n\\r\\n-- Masaoka Shiki (1867-1902)";
    var expected{{cookiecutter.domain_capitalized}} = {{cookiecutter.domain_capitalized}}.builder().code(code).description(description).build();
    Mockito.lenient()
        .when(obtain{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code))
        .thenReturn(Optional.of(expected{{cookiecutter.domain_capitalized}}));
    // When
    var request{{cookiecutter.domain_capitalized}} = new {{cookiecutter.domain_capitalized}}Domain(obtain{{cookiecutter.domain_capitalized}});
    var actual{{cookiecutter.domain_capitalized}} = request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code);
    assertThat(actual{{cookiecutter.domain_capitalized}}).isNotNull().isEqualTo(expected{{cookiecutter.domain_capitalized}});
  }

  @Test
  @DisplayName("should throw exception when asked for {{cookiecutter.domain}} by id that does not exists from stub")
  public void getExceptionWhenAsked{{cookiecutter.domain_capitalized}}ByIdThatDoesNotExist(@Mock Obtain{{cookiecutter.domain_capitalized}} obtain{{cookiecutter.domain_capitalized}}) {
    // Given
    // Stub
    var code = -1000L;
    Mockito.lenient().when(obtain{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code)).thenReturn(Optional.empty());
    // When
    var request{{cookiecutter.domain_capitalized}} = new {{cookiecutter.domain_capitalized}}Domain(obtain{{cookiecutter.domain_capitalized}});
    // Then
    assertThatThrownBy(() -> request{{cookiecutter.domain_capitalized}}.get{{cookiecutter.domain_capitalized}}ByCode(code))
        .isInstanceOf({{cookiecutter.domain_capitalized}}NotFoundException.class)
        .hasMessageContaining("{{cookiecutter.domain_capitalized}} with code " + code + " does not exist");
  }
}
