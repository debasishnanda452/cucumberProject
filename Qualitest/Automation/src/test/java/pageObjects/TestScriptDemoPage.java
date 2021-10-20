package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TestScriptDemoPage extends BaseClass {

    private static final String URL = "https://testscriptdemo.com/";

    private static final By WISHLIST_PRODUCT1 = By.xpath("//ul[@class='products columns-5']//*[contains(text(),'Add to wishlist')]");

    private static final By WISHLIST_ICON = By.xpath("//div[@class='header-right col-md-3 hidden-xs']//div[@class='header-wishlist']");

    private static final By WISHLIST_PRICE = By.xpath("//tbody//td[4]");

    private static final By WISHLIST_RECORDS = By.xpath("//tbody//tr");

    private static final String ADD_TO_CART_OPTION = "(//tbody//td[4]//following-sibling::td[2])[{}]";

    private static final By CART_MESSAGE = By.xpath("//div[@class='woocommerce-message']");

    private static final By CART_MENU = By.xpath("//*[@id='blog']/div[2]/div[1]/div/div/div[3]/div[1]/div/div/a/i");

    private static final By CART = By.xpath("//tbody//tr");

    private List<Double> list = new ArrayList<>();


    public void launchApp() {
        launchURL(URL);
    }

    public void selectWishListItems(int number) throws InterruptedException {
        List<WebElement> wishlist = getElements(WISHLIST_PRODUCT1);
        for (int i = 0; i < number; i++) {
            wishlist.get(i).click();
            Thread.sleep(500);
        }
    }

    public void selectWishListMenu() {
        clickElement(WISHLIST_ICON);
    }
    
    //get the text of object
    //extract all numbers and store it in a list for a single object
    //find minimum value from above list
    //pass above value to another list
    //find minimum
    
    @SuppressWarnings("null")
	public List<Double> getAllPriceFromSingleObject(WebElement element)
    {
    	String objectText=element.getText();
    	System.out.println("object txt 1 is"+objectText);
    	objectText = objectText.replaceAll("[^.-?0-9]+", " "); 
    	System.out.println("object txt is"+objectText);
        System.out.println(Arrays.asList(objectText.trim().split(" ")));
        	List<String> list1=Arrays.asList(objectText.trim().split(" "));
    	List<Double> allPrice =  list1.stream().map(s -> Double.parseDouble(s)).collect(Collectors.toList());
    	
    	System.out.println(allPrice);
    	
    	
    	return allPrice;
    }
    
    public double findMinimuminList(List<Double> allPrice )
    {
    	double minimumPriceOfSingleElement=Collections.min(allPrice);
    	System.out.println("Minimum value is "+minimumPriceOfSingleElement);
    	return minimumPriceOfSingleElement;
    	
    }

    public void addProductToCart() {
        List<WebElement> prices = getElements(WISHLIST_PRICE);
        //List<Integer> allMinimumPrices=null;
        String[] record;
        String text = "";
        for (WebElement element : prices) {
        	List<Double> requiredallPriceFromSingleObject=getAllPriceFromSingleObject(element);
        	Double minimumValueOfSingleObject=findMinimuminList(requiredallPriceFromSingleObject);
        	list.add(minimumValueOfSingleObject);
        	
        }

        clickElement(getElement(By.xpath(ADD_TO_CART_OPTION.replace("{}",
                String.valueOf(getMinPriceIndex(list))))));
    }

    public int numberOfWishListRecords() {
        return getElements(WISHLIST_RECORDS).size();
    }

    private double getMinPriceIndex(List<Double> list2) {
        return list2.indexOf(Collections.min(list2)) + 1;
    }

    public String getCartMessage() {
        return getText(CART_MESSAGE);
    }

    public void selectCartMenu() throws InterruptedException {
        clickElement(CART_MENU);
        Thread.sleep(3000);
    }

    public boolean checkCartElement() {
        return getElement(CART).isDisplayed();
    }


}


