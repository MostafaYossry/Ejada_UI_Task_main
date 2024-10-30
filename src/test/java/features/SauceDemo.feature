#Author: mos.yossry.cufe@gmail.com
#Task for Ejada Company
#Purchase Scenario

Feature: SauceDemo Shop

 # main steps to run  before all scenarios
  Background:
    Given I successfully connected to the Landing page using label "Accepted usernames"
    

# 1st scenario >> Login
# I use different data using Scenario Outline and Examples to run same TC many times with different data
  Scenario Outline: Checking the login to website feature with invalid credentials (Data in Examples)
    When I enter <Username> and <Password>
    And I Click on Login
    Then I assert the Error <MSG>
		
Examples: 
| Username 					| Password 			 | MSG 																														|
| ""	 							| "secret_sauce" | "Username is required" 																				|
| "standard_user" 	| ""  				 	 | "Password is required" 																				|
| "locked_out_user" | "secret_sauce" | "Sorry, this user has been locked out" 												|
| "Mostafa"				 	| "secret_sauce" | "Username and password do not match any user in this service" 	|

# 2nd scenario >> Purchase
  Scenario: Purchase some items
    When I add valid usrename and password "standard_user" and "secret_sauce"
    And I Click on Login
    Then I can go to Home page using label "Products"
    When I filter the product by Price High to Low
		And  I select e most expensive two products
		And  I save the Product names and Prices
		And  I click Cart Icon
		Then I assert going to Your Cart page using label "Your Cart"
		And  I asset the select products by names
		
		When I click Checkout
		Then I assert going to Your Information page using label "Your Information"
		
# 1st test case continue without First Name
		When I add my information without Firstname "LastName" and "Postal"
    Then I get an message "First Name is required"
        
# 2nd test case continue with all data
    When I add all my information "Mostafa" and "Alsaddek" and "12345"
		And  I Click Continue
		Then I assert going to Overview page using label "Overview"
		And  I assert the current URL is "https://www.saucedemo.com/checkout-step-two.html"
		And  I asset the Price Total

		When I click Finish 
		Then I assert going to Complete page using label "Complete"
		And  I assert the label "Thank you" and the message "order has been dispatched"
 