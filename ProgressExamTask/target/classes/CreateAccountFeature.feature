Feature: Register functionality

  In order to give a login option
  as an user
  I want to be able to  and register

  Scenario: Successful registration

    Given user is on authentication page
    And submits a valid Email address
    And the user inputs valid data in all required fields
    When the user presses Register button
    Then the user is successfully registered

#    Examples:
#      | Email address              |
#      | sherfe.metkova@yopmail.com |
#      |
#      |



