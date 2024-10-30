package pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class InformationPage {

	public WebDriver driver;
	
// Find all page elements using FindBy and useful locators
	@FindBy(xpath = "//div[@class='header_secondary_container']/span")
	WebElement YourInformationLabel;

	@FindBy(id = "first-name")
	WebElement FirstName;

	@FindBy(id = "last-name")
	WebElement LastName;

	@FindBy(id = "postal-code")
	WebElement Postal;

	@FindBy(id = "continue")
	WebElement ContinueButton;

	@FindBy(xpath = "//div[@class='checkout_info']/div[4]/h3")
	WebElement Information_Error_MSG;
	
// use same created driver over all pages
	public InformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

// Create methods to use the page elements
	public void CheckYourInformationLabel(String Actual_Label) throws IOException {
    	Assert.assertTrue(YourInformationLabel.getText().contains(Actual_Label));
		}

    public void SetFirstName(String first) throws IOException {
    	FirstName.sendKeys(first);
    	}

    public void SetLastName(String last) throws IOException {
    	LastName.sendKeys(last);
    	}
    
    public void SetPostal(String postal) throws IOException {
    	Postal.sendKeys(postal);
    	}

    public void ClickOnContinue() throws IOException {
    	ContinueButton.click();
    	}
    
	public void CheckErrorMSG(String Actual_Label) throws IOException {
    	Assert.assertTrue(Information_Error_MSG.getText().contains(Actual_Label));
		}
}
