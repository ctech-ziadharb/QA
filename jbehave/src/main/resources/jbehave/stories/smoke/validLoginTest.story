
Scenario: testing the dashboard login feature

Given I am on the dashboard
When I enter the username ziadh
And I enter the password password
And I click the login button
Then I should see the dashboard home page
