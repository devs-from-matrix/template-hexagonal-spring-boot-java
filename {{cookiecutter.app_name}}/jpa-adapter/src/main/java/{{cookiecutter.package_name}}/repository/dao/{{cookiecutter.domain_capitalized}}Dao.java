package {{cookiecutter.package_name}}.repository.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import {{cookiecutter.package_name}}.repository.entity.{{cookiecutter.domain_capitalized}}Entity;

@Repository
public interface {{cookiecutter.domain_capitalized}}Dao
    extends JpaRepository<{{cookiecutter.domain_capitalized}}Entity, Long>, RevisionRepository<{{cookiecutter.domain_capitalized}}Entity, Long, Long> {

  Optional<{{cookiecutter.domain_capitalized}}Entity> findByCode(Long code);
}
