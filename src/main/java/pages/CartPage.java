package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

	public WebDriver driver;
	
// Find all page elements using FindBy and useful locators
	@FindBy(xpath = "//div[@class='header_secondary_container']/span")
	WebElement YourCartLabel;
	
	@FindBy(xpath = "//div[@class='cart_item_label']/a/div")
	List<WebElement> Selected_Product_Name;

	@FindBy(id = "checkout")
	WebElement CheckoutButton;

// use same created driver over all pages
	public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
// Create methods to use the page elements
    public void CheckYourCartLabel(String Actual_Label) throws IOException {
    	Assert.assertTrue(YourCartLabel.getText().contains(Actual_Label));
		}

    public void CheckProductName(int Number, String Selected_Name) throws IOException {
    	Assert.assertTrue(Selected_Product_Name.get(Number).getText().equals(Selected_Name));
		}

    public void ClickOnCheckout() throws IOException {
    	CheckoutButton.click();
    	}
}
