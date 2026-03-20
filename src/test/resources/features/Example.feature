Feature: Database Query Example

  Scenario: Fetch user by ID
    Given I have a user ID
    When I execute the query get_user_by_id
    Then I should see the user's details in the result