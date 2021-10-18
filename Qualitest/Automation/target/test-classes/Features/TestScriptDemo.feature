Feature: Validate Testscriptdemo shopping cart


  Scenario: Validate that item with minimum price from the wishlist can be added to the cart
    Given application webpage is launched
    And I add 4 different products to my wish list
    When I view my wishlist table
    Then I find total 4 selected items in my Wishlist
    When I search for lowest price product and add the lowest price item to my cart
    Then I get the success message as "Product added to cart successfully"
    And I am able to verify the item in my cart





