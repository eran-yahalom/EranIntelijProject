@postmanApi
Feature: Reqres API

  Scenario: Get all reqres
    When API request all users details
    Then API response should be successful with status code 200
    And number of users in the response should be 12

  Scenario Outline: Get single reqres
    When API request user details with id "<id>"
    Then API response user mail should be "<email>"
    And API response user first name should be "<first_name>" and last name should be "<last_name>"
    Examples:
      | id | email                  | first_name | last_name |
      | 1  | george.bluth@reqres.in | George     | Bluth     |
      | 2  | janet.weaver@reqres.in | Janet      | Weaver    |

  Scenario: user login with valid credentials
    When API request user login with username "eve.holt@reqres.in" and password "cityslicka"
    Then API LOGIN response should be successful

  Scenario: update user details
    Given API updated user details with name "eran yahalom" and job "jobby" for user with id "2"
    Then API UPDATE response should be successful with updated name "eran yahalom" and job "jobby"

  Scenario: delete user
    When API deletes user details with id "2"
    Then API DELETE response should be successful with status code 204