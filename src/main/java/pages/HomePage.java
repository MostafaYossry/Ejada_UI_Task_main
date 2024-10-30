package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {

	public WebDriver driver;
	
// Find all page elements using FindBy and useful locators
	@FindBy(xpath = "//div[@class='header_secondary_container']/span")
	WebElement ProductLabel;

	@FindBy(xpath = "//div[@id='header_container']/div[2]/div/span/select")
	WebElement Filter;

	@FindBy(xpath = "//div[@id='header_container']/div[2]/div/span/select/option[4]")
	WebElement SelectHighPrice;
	
	@FindBy(xpath = "//div[@id='inventory_container']/div/div/div/div/div[2]/div[2]/button")
	List<WebElement> AddToCartButtons;

	@FindBy(xpath = "//div[@id='inventory_container']/div/div/div/div/div[2]/div[1]/a")
	List<WebElement> ItemName;

	
	@FindBy(xpath = "//div[@id='inventory_container']/div/div/div/div/div[2]/div[2]/div")
	List<WebElement> ItemPrice;

	@FindBy(xpath = "//div[@id='shopping_cart_container']/a")
	WebElement AddToCartIcon;

// use same created driver over all pages
	public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

// Create methods to use the page elements
    public void CheckProductLabel(String Actual_Label) throws IOException {
    	Assert.assertTrue(ProductLabel.getText().contains(Actual_Label));
		}
    
    public void SelectFilter () throws IOException {
    	Filter.click();
    	SelectHighPrice.click();    	
    }
    
    public void SelectMostExpensive (int number) throws IOException {
    	AddToCartButtons.get(number).click();
    }
    
    public String getItemName (int number) throws IOException {
    	return ItemName.get(number).getText();
    	}

    public String getItemPrice (int number) throws IOException {
    	return ItemPrice.get(number).getText();
    	}

    public void ClickOnCart () throws IOException {
    	AddToCartIcon.click();
    }
}