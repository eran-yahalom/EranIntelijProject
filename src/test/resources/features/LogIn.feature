@logIn @ui
Feature: Log in and logout actions

  Background:
    Given user clicks on log in link

  Scenario: Successful login with valid credentials
    When user enters email
    And user fills in email and password
    And user clicks on log in button
    Then the user is logged in successfully

  Scenario Outline: Login with invalid credentials
    When user fills in email and password with "<email>" and "<password>"
    And user clicks on log in button
    Then error message "<errorMessage>" is displayed for invalid credentials

    Examples:
      | email                  | password        | errorMessage      |
      | invalidemail@gmail.com | password123.com | loginErrorMessage|
      |                        | password123     | loginNoCustomerAccountFoundMessage |
      | invalidemail@gmail.com |                 | loginErrorMessage                  |

  Scenario: Logout after successful login
    Given the user is logged in
    When user clicks on log out link
    Then the user is logged out successfully