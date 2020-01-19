Feature: Login functionality

  Scenario: Successful login

    Given user is on authentication page
    And enters already registered Email address and password in Login section
    And the user presses Login button
    Then the user is successfully logged in