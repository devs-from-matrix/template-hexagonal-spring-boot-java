@{{cookiecutter.domain_capitalized}}
Feature: User would like to get {{cookiecutter.domain_plural}}
  Background:
    Given the following {{cookiecutter.domain_plural}} exists in the library
      | code | description                 |
      | 1    | Twinkle twinkle little star |
      | 2    | Johnny Johnny Yes Papa      |

  Scenario: User should be able to get all {{cookiecutter.domain_plural}}
    When user requests for all {{cookiecutter.domain_plural}}
    Then the user gets the following {{cookiecutter.domain_plural}}
      | code | description                 |
      | 1    | Twinkle twinkle little star |
      | 2    | Johnny Johnny Yes Papa      |

  Scenario: User should be able to get {{cookiecutter.domain_plural}} by code
    When user requests for {{cookiecutter.domain_plural}} by code "1"
    Then the user gets the following {{cookiecutter.domain_plural}}
      | code | description                 |
      | 1    | Twinkle twinkle little star |

  Scenario: User should get an appropriate NOT FOUND message while trying get {{cookiecutter.domain_plural}} by an invalid code
    When user requests for {{cookiecutter.domain_plural}} by id "10000" that does not exists
    Then the user gets an exception "{{cookiecutter.domain_capitalized}} with code 10000 does not exist"