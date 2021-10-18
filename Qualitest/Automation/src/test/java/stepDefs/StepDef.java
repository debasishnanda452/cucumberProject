package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.TestScriptDemoPage;

import static org.assertj.core.api.Assertions.assertThat;


public class StepDef {

    TestScriptDemoPage testScriptDemoPage;

    @Given("application webpage is launched")
    public void applicationWebpageIsLaunched() {
        testScriptDemoPage = new TestScriptDemoPage();
        testScriptDemoPage.launchApp();
    }


    @Given("I add {int} different products to my wish list")
    public void iAddFourDifferentProductsToMyWishList(int number) throws InterruptedException {
        testScriptDemoPage.selectWishListItems(number);
    }

    @When("I view my wishlist table")
    public void iViewMyWishlistTable() {
        testScriptDemoPage.selectWishListMenu();
    }

    @When("I search for lowest price product and add the lowest price item to my cart")
    public void iSearchForLowestPriceProductAndAddTheLowestPriceItemToMyCart() throws InterruptedException {
        testScriptDemoPage.addProductToCart();
    }

    @Then("I find total {int} selected items in my Wishlist")
    public void iFindTotalFourSelectedItemsInMyWishlist(int records) {
        assertThat(testScriptDemoPage.numberOfWishListRecords()).isEqualTo(records);
    }

    @Then("I am able to verify the item in my cart")
    public void iAmAbleToVerifyTheItemInMyCart() throws InterruptedException {
        testScriptDemoPage.selectCartMenu();
        assertThat(testScriptDemoPage.checkCartElement()).isTrue();
    }

    @And("I get the success message as {string}")
    public void iGetTheSuccessMessageAs(String message) {
        assertThat(testScriptDemoPage.getCartMessage()).isEqualTo(message);
    }
}
