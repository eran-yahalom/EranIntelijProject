@petStoreSwaggerAPI

Feature: Pet Store Swagger API

  Scenario Outline: Create user
    When API creates a random user
    Then API response should be successful with status code "200" and type "unknown"
    Examples:
      | run |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |

  Scenario: Get user by user name
    When API request user details with user name
    Then API get user details response should be successful with status code "200"

  Scenario: Update user by user name
    When API updates user details for user
    Then API update user details response should be successful with status code "200"

  Scenario: Delete user by user name
    When API deletes user with user name
    Then API delete user response should be successful with status code "200"


