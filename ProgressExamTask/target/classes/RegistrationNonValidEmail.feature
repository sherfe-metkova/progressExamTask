Feature: Register functionality

  Scenario: Not successful registration

    Given user is on authentication page
    And enters invalid Email address in Create account section
      | Non Valid Email              |
      | James.Jones1m11h2a |
    Then the user clicks Submit button
    And error message is shown