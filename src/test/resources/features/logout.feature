@logout
Feature: Logout

  @smoke @regression
  Scenario: Verify Logout
    Given user is logged in
    When user clicks logout button
    Then user should be logged out