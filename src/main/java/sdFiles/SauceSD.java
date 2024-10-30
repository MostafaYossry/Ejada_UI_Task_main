package sdFiles;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CompletePage;
import pages.OverviewPage;
import pages.InformationPage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import configuration.ConfigurationFile;

public class SauceSD {

// declare the used variable over this class 
	public static WebDriver driver;
    				String	Item_Name_1, Item_Name_2 ;
    				double	Item_Price_1, Item_Price_2,TotalPrice ;
    
// create objects from other used classes
	ConfigurationFile 		configurationFile 	= new ConfigurationFile();
    private LoginPage 		loginPage 			= new LoginPage(driver);
    private HomePage 		homePage 			= new HomePage(driver);
    private CartPage 		cartPage 			= new CartPage(driver);
    private InformationPage informationPage 	= new InformationPage(driver);
	private OverviewPage 	overviewPage 		= new OverviewPage(driver);
	private CompletePage 	completePage 		= new CompletePage(driver);

// Block of code to run once the class is used
// I here initiate the driver based on the selection in Configuration File
	@Before
    public void setUp() {
		String Drivertype = configurationFile.Driverpath;

		if (Drivertype.contains("chromedriver")){
			System.setProperty("webdriver.chrome.driver", Drivertype );
			driver = new ChromeDriver();
			}
		
		else if (Drivertype.contains("geckodriver")){
			System.setProperty("webdriver.gecko.driver", Drivertype);
			driver = new FirefoxDriver();
			}
		
		driver.manage().window().maximize();
    }

// method to return the driver to be use same driver in each application page
	public static WebDriver getDriver() {
        return driver;
    }

	@Given("I successfully connected to the Landing page using label {string}")
	public void i_successfully_connected_to_the_landing_page_using_label(String Label) throws IOException {
        driver.get(configurationFile.Landing_URL);
//pass the used driver to the following application page
        loginPage = new LoginPage(getDriver() );
        loginPage.AcceptedUsernamesLabel(Label);
		}

	@When("I enter {string} and {string}")
	public void i_enter_and(String username, String password) throws IOException {
        loginPage = new LoginPage(getDriver() );
        loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@Then("I assert the Error {string}")
	public void i_assert_the_error(String ActualMSG) throws IOException {
        loginPage = new LoginPage(getDriver() );
        loginPage.CheckErrorMSG(ActualMSG);
	}

	@When("I add valid usrename and password {string} and {string}")
	public void i_add_valid_usrename_and_password_and(String username, String password) throws IOException {
        loginPage = new LoginPage(getDriver() );
        loginPage.enterUsername(username);
		loginPage.enterPassword(password);
  		}

	@When("I Click on Login")
	public void i_click_on_login() throws IOException {
		loginPage = new LoginPage(getDriver() );
		loginPage.clickLoginButton();
        }

	@Then("I can go to Home page using label {string}")
	public void i_can_go_to_home_page_using_label(String Label) throws IOException {
		homePage = new HomePage(getDriver() );
		homePage.CheckProductLabel(Label); 
		}

	@When("I filter the product by Price High to Low")
	public void i_filter_the_product_by_price_high_to_low() throws IOException {
		homePage = new HomePage(getDriver() );
		homePage.SelectFilter();
		}

	@When("I select e most expensive two products")
	public void i_select_e_most_expensive_two_products() throws IOException {
		homePage = new HomePage(getDriver() );
		homePage.SelectMostExpensive(0);
		homePage.SelectMostExpensive(1);
		}

	@When("I save the Product names and Prices")
	public void i_save_the_product_names_and_prices() throws IOException {
		homePage = new HomePage(getDriver() );

		// Save Name of the selected products
		Item_Name_1 = homePage.getItemName(0);
		Item_Name_2 = homePage.getItemName(1);
		
		// Save Price of the selected products
		Item_Price_1 = Double.parseDouble(homePage.getItemPrice(0).replace("$", ""));
		Item_Price_2 = Double.parseDouble(homePage.getItemPrice(1).replace("$", ""));
		TotalPrice = Item_Price_1 +Item_Price_2 ;
		
		// Show Name and Pricrs of the selected products
		System.out.println("Item Name is " + Item_Name_1 + ", and item price is " + Item_Price_1);
		System.out.println("Item Name is " + Item_Name_2 + ", and item price is " + Item_Price_2);
		System.out.println("Total Price is  " + TotalPrice);
		}

	@When("I click Cart Icon")
	public void i_click_cart_icon() throws IOException {
		homePage = new HomePage(getDriver() );
		homePage.ClickOnCart();
		}

	@Then("I assert going to Your Cart page using label {string}")
	public void i_assert_going_to_your_cart_page_using_label(String Label) throws IOException {
		cartPage = new CartPage(getDriver() );
		cartPage.CheckYourCartLabel(Label);
		}

	@Then("I asset the select products by names")
	public void i_asset_the_select_products_by_names() throws IOException {
		cartPage = new CartPage(getDriver() );
		cartPage.CheckProductName(0,Item_Name_1);
		cartPage.CheckProductName(1,Item_Name_2);
		}

	@When("I click Checkout")
	public void i_click_checkout() throws IOException {
		cartPage = new CartPage(getDriver() );
		cartPage.ClickOnCheckout();
		}

	@Then("I assert going to Your Information page using label {string}")
	public void i_assert_going_to_your_information_page_using_label(String Actual_Label) throws IOException {
		informationPage = new InformationPage(getDriver() );
		informationPage.CheckYourInformationLabel(Actual_Label);
		}

	@When("I add my information without Firstname {string} and {string}")
	public void i_add_my_information_without_firstname_and(String LastName, String Postal) throws IOException {
		informationPage = new InformationPage(getDriver() );
		informationPage.SetLastName(LastName);
		informationPage.SetPostal(Postal);
		informationPage.ClickOnContinue();
 	}

	@Then("I get an message {string}")
	public void i_get_an_message(String ErrorMSG) throws IOException {
		informationPage = new InformationPage(getDriver() );
		informationPage.CheckErrorMSG(ErrorMSG);
	}

	@When("I add all my information {string} and {string} and {string}")
	public void i_add_all_my_information_and_and(String FirstName, String LastName, String Postal) throws IOException {
		informationPage = new InformationPage(getDriver() );
		informationPage.SetFirstName(FirstName);
		informationPage.SetLastName(LastName);
		informationPage.SetPostal(Postal);
		}

	@When("I Click Continue")
	public void i_click_continue() throws IOException {
		informationPage = new InformationPage(getDriver() );
		informationPage.ClickOnContinue();
		}

	@Then("I assert going to Overview page using label {string}")
	public void i_assert_going_to_overview_page_using_label(String Actual_Label) throws IOException {
		overviewPage = new OverviewPage(getDriver() );
		overviewPage.CheckOverViewLabel(Actual_Label);
		}

	@Then("I assert the current URL is {string}")
	public void i_assert_the_current_url_is(String ExpectedURL) throws IOException {
		overviewPage = new OverviewPage(getDriver() );
		overviewPage.CheckURL(ExpectedURL);
		}

	@Then("I asset the Price Total")
	public void i_asset_the_price_total() throws IOException {
		overviewPage = new OverviewPage(getDriver() );
		overviewPage.CheckTotalPriceBeforeTAX(Double.toString(TotalPrice));
		}

	@When("I click Finish")
	public void i_click_finish() throws IOException {
		overviewPage = new OverviewPage(getDriver() );
		overviewPage.ClickOnFinish();
		}

	@Then("I assert going to Complete page using label {string}")
	public void i_assert_going_to_complete_page_using_label(String Actual_Label) throws IOException {
		completePage = new CompletePage(getDriver() );
		completePage.CheckCompleteLabel(Actual_Label);
		}

	@Then("I assert the label {string} and the message {string}")
	public void i_assert_the_label_and_the_message(String ThankYou, String Message) throws IOException {
		completePage = new CompletePage(getDriver() );
		completePage.CheckThankYou(ThankYou);
		completePage.CheckMessage(Message);
		}
	
	// Block of code to run once the class is used after finishing the test
	// I here wait some seconds for you to notice the TC is finished the close the driver
	@After
    public void tearDown() throws InterruptedException {
        if (driver != null) {
    		TimeUnit.SECONDS.sleep(3);
            driver.quit();
            }
        }
}