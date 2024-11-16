package {{cookiecutter.package_name}}.cucumber;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import {{cookiecutter.package_name}}.boot.{{cookiecutter.domain_capitalized}}Application;

@SpringBootTest(classes = {{cookiecutter.domain_capitalized}}Application.class, webEnvironment = RANDOM_PORT)
@CucumberContextConfiguration
@ActiveProfiles("test")
public class SpringCucumberTestConfig {}
