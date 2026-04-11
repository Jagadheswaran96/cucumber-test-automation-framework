@login
Feature: Login

  @smoke @regression
  Scenario: Valid Login
    Given user is on login page
    When user enters valid credentials
    Then user should be logged in