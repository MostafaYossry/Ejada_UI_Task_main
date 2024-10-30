package pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CompletePage {

	public WebDriver driver;
	
// Find all page elements using FindBy and useful locators
	@FindBy(xpath = "//div[@class='header_secondary_container']/span")
	WebElement CompleteLabel;

	@FindBy(xpath = "//div[@id='checkout_complete_container']/h2")
	WebElement ThankYou;

	@FindBy(xpath = "//div[@id='checkout_complete_container']/div")
	WebElement Message;

// use same created driver over all pages
	public CompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

// Create methods to use the page elements
	public void CheckCompleteLabel(String Actual_Label) throws IOException {
    	Assert.assertTrue(CompleteLabel.getText().contains(Actual_Label));
		}

    public void CheckThankYou(String Actual_Label) throws IOException {
    	Assert.assertTrue(ThankYou.getText().contains(Actual_Label));
		}

    public void CheckMessage(String Actual_Label) throws IOException {
    	Assert.assertTrue(Message.getText().contains(Actual_Label));
		}
}
