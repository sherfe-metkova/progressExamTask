Feature: Register functionality

  Scenario: Registration without inputting a password

    Given user is on authentication page
    And enters a valid Email address in Create account section
      | email                           |
      | Jamses.Jsoness1m11h2a@gmail.com |
    Then the user is on create account page after submitting the email
    And the user inputs below data in the account creation fields
      | Gender | FirstName | LastName | Password | Day | Month   | Year | Address       | City      | State | Code  | Phone        |
      | Male   | James     | Jones    | -        | 12  | January | 1989 | W Central Ave | Nashville | 3     | 67112 | +23555554444 |
    When the user presses Register button
    Then user is presented with error message