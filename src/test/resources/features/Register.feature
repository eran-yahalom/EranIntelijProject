@register @ui
Feature: User Registration

  Background:
    Given user clicks on register link

  @ui
  Scenario: Register success flow
    And user enters gender "male"
    And user enters first and last name
    And user enters email
    And user enters password and confirm password
    And user clicks on register button
    And user should see the registration success message
    When user clicks on continue button
    Then new user is logged in successfully

  @ui
  Scenario: Register with existing driver will result in fail to register
    And user enters gender "male"
    And user enters first and last name
    And user enters existing email
    And user enters password and confirm password
    When user clicks on register button
    Then user should see an error message for existing email

  @ui
  Scenario: Register with non matching password and confirm password will result in fail to register
    And user enters gender "male"
    And user enters first and last name
    And user enters existing email
    And user enters non matching password and confirm password
    When user clicks on register button
    Then user should see an error message for non matching passwords

  @ui
  Scenario: Register without entering details will result in fail to register
    When user clicks on register button
    Then user should see an error message for all empty fields



