Feature: End to End Test
I want to make End to End test for buying a product with full details

Scenario Outline: Buying a product
Given The user is in the home page
When He search for "<productName>"
And Choose buy two items
And moves to ckeckout cart and entered personal data on checkout page and place the order 
Then he can view the order and print the invoice

Examples:
|productName|
|Apple MacBook Pro 13-inch|
