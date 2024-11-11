package {{cookiecutter.package_name}}.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import {{cookiecutter.package_name}}.domain.model.{{cookiecutter.domain_capitalized}};

@Table(name = "T_{{cookiecutter.domain_uppercase}}")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class {{cookiecutter.domain_capitalized}}Entity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_{{cookiecutter.domain_uppercase}}")
  @SequenceGenerator(
      name = "SEQ_T_{{cookiecutter.domain_uppercase}}",
      sequenceName = "SEQ_T_{{cookiecutter.domain_uppercase}}",
      allocationSize = 1,
      initialValue = 1)
  @Column(name = "TECH_ID")
  private Long techId;

  @Column(name = "CODE")
  private Long code;

  @Column(name = "DESCRIPTION")
  private String description;

  public {{cookiecutter.domain_capitalized}} toModel() {
    return {{cookiecutter.domain_capitalized}}.builder().code(code).description(description).build();
  }
}
