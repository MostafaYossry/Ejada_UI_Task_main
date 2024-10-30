package pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OverviewPage {
	public WebDriver driver;

// Find all page elements using FindBy and useful locators
	@FindBy(xpath = "//div[@class='header_secondary_container']/span")
	WebElement OverViewLabel;
	
	@FindBy(xpath = "//div[@id='checkout_summary_container']/div/div[2]/div[6]")
	WebElement TotalPrices;

	@FindBy(id = "finish")
	WebElement FinishButton;

// use same created driver over all pages
	public OverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
// Create methods to use the page elements
   public void CheckOverViewLabel(String Actual_Label) throws IOException {
    	Assert.assertTrue(OverViewLabel.getText().contains(Actual_Label));
		}

	public void CheckURL (String ExpectedURL) throws IOException 
	{
        Assert.assertTrue(driver.getCurrentUrl().equals(ExpectedURL));
	}

	public void CheckTotalPriceBeforeTAX(String Actual_Label) throws IOException {
    	Assert.assertTrue(TotalPrices.getText().contains(Actual_Label));
    }

    public void ClickOnFinish() throws IOException {
    	FinishButton.click();
    	}
}