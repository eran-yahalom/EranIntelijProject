@register @ui
Feature: User can register a new account

  Background:
    Given user generates random user and password
    And user clicks on register link

  @ui
  Scenario: Register success flow
    When user enters gender "male"
    And user enters first and last name
    And user enters email
    And user enters password and confirm password
    And user clicks on register button
    Then a success message is displayed
    When user clicks on continue button
    Then the user should be logged in

  @ui
  Scenario: Fail to register with existing user
    When user enters gender "male"
    And user enters first and last name
    And user enters existing email
    And user enters password and confirm password
    And user clicks on register button
    Then a error message should be displayed for existing email

  @ui
  Scenario: Fail to register with non matching password and confirm password
    When user enters gender "male"
    And user enters first and last name
    And user enters existing email
    And user enters non matching password and confirm password
    And user clicks on register button
    Then a error message should be displayed for non matching passwords

  @ui
  Scenario: Fail to register with empty fields
    When user clicks on register button
    Then a error message should be displayed for all empty fields



