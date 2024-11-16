package {{cookiecutter.package_name}}.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class {{cookiecutter.domain_capitalized}} {

  private Long code;
  private String description;
}
