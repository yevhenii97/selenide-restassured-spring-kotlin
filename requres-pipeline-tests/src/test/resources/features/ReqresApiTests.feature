@REQ_API
Feature: Reqres api test

  @REQ_API_01
  Scenario: api test
    Given Send request to service to get list of users
    Then Verify that list contains data
      | id | email                      | firstName | lastName | avatar                                   |
      | 7  | michael.lawson@reqres.in   | Michael   | Lawson   | https://reqres.in/img/faces/7-image.jpg  |
      | 8  | lindsay.ferguson@reqres.in | Lindsay   | Ferguson | https://reqres.in/img/faces/8-image.jpg  |
      | 9  | tobias.funke@reqres.in     | Tobias    | Funke    | https://reqres.in/img/faces/9-image.jpg  |
      | 10 | byron.fields@reqres.in     | Byron     | Fields   | https://reqres.in/img/faces/10-image.jpg |
      | 11 | george.edwards@reqres.in   | George    | Edwards  | https://reqres.in/img/faces/11-image.jpg |
      | 12 | rachel.howell@reqres.in    | Rachel    | Howell   | https://reqres.in/img/faces/12-image.jpg |

  @REQ_API_02
  Scenario: api test 2
    Given Create user with values
      | name     | job    |
      | morpheus | leader |

  @REQ_API_03
  Scenario: test db
    Given Get all cats from DB