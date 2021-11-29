@orderDev
Feature: Login to OrderDev Application and Verify Login functionality
  
   @InValidCredentials
   Scenario: To verify login functionality
    Given User is on Home page
    When User clicks on "Login" button
    And User enters email as "neeraj.sharma@gmail.com"
    And User enters password as "admin123"
    And User clicks on "Sign in" button
    Then User should not be able to login sucessfully
    
   @CreateAccount
   Scenario: To verify create user functionality
    Given User is on Home page
    When User clicks on "Login" button
    And User clicks on "Create an account" button
    And User fills the form for account creation
    And User clicks on "Create an account" button
    And Verify Account is created successfully
    
   @ValidCredentials
   Scenario: To verify login functionality
    Given User is on Home page
    When User clicks on "Login" button
    And User enters email as "neeraj.sharma@gmail.com"
    And User enters password as "Admin@123"
    And User clicks on "Sign in" button
    Then Verify User has logged in successfully
    
   @ChangePassword
   Scenario: To verify the change password functionality
    Given User is on Home page
    When User clicks on "Login" button
    And User enters email as "neeraj.sharma@gmail.com"
    And User enters password as "Admin@123"
    And User clicks on "Sign in" button
		And User clicks on "Settings" button
		And User clicks on "Change Password" button
		And User enters "Admin@123" in "Current Password" field
		And User enters "Admin@12345" in "New Password" field
		And User enters "Admin@12345" in "Confirm Password" field
		Then User clicks on "Confirm" button
		And User clicks on "Logout" button
		When User clicks on "Login" button
    And User enters email as "neeraj.sharma@gmail.com"
    And User enters password as "Admin@12345"
    And User clicks on "Sign in" button
    Then Verify User has logged in successfully
		
