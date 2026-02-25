@logIn @ui
Feature: log in tests

  Background:
    Given user clicks on log in link

  Scenario: Successful login with valid credentials
    And user enters email
    And user fills in email and password
    When user clicks on log in button
    Then existing user is logged in successfully

  Scenario Outline: login with invalid credentials
    And user fills in email and password with "<email>" and "<password>"
    When user clicks on log in button
    Then user should see an error message:"<errorMessage>" for invalid credentials
    Examples:
      | email                  | password        | errorMessage                       |
      | invalidemail@gmail.com | password123.com | loginErrorMessage                  |
      |                        | password123     | loginNoCustomerAccountFoundMessage |
      | invalidemail@gmail.com |                 | loginErrorMessage                  |