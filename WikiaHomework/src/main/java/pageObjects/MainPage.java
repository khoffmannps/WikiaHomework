package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.How;
import runner.TestManager;

public class MainPage {

	private final WebDriver driver;

	public MainPage(WebDriver driver) {
		this.driver = driver;
		driver.get(TestManager.getProperty("wikiaMainURL"));
	}
	
	@FindBy(className = "ajaxLogin")
	private WebElement openLoginForm;

	@FindBy(how = How.CSS, using = "div.buttons > nav")
	private WebElement contributeButton;

	@FindBy(css = ".AccountNavigation > li > a")
	private WebElement displayedUsername;

	@FindBy(name="username")
	private WebElement usernameInput;

	@FindBy(name="password")
	private WebElement passwordInput;

	@FindBy(className = "login-button")
	private WebElement loginButton;
	
	@FindBy(id = "UserLoginDropdown")
	private WebElement userLoginDropdown;
	
	@FindBy(linkText = "Add a Video")
	private WebElement addVideoMenuOption;
	
	public MainPage hoverOverLogin() {
		
		new Actions(driver).moveToElement(openLoginForm).perform();
		return this;
	}

	public MainPage loginToWikia() {
		
		hoverOverLogin();
		usernameInput.clear();
		usernameInput.sendKeys(TestManager.getProperty("login"));
		passwordInput.clear();
		passwordInput.sendKeys(TestManager.getProperty("password"));
		new Actions(driver).moveToElement(loginButton).clickAndHold().release().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(displayedUsername, "Log in")));
		return this;
	}

	public boolean isLoggedIn() {
		if (displayedUsername.getText().equals("Log in")) {
			return false;
		} else
			return true;
	}
	
	public boolean isUserLoginDropdownExpanded(){

		if(userLoginDropdown.isDisplayed()){
			return true;
		}
		return false;
	}
	
	public boolean isContributeDropdownExpanded(){
		if(addVideoMenuOption.isDisplayed()){
			return true;
		}
		else return false;
	}
	
	public MainPage clickContributeButton() {

		contributeButton.click();
		new Actions(driver).moveToElement(contributeButton).perform();
		return this;
	}
	
	public String getLogin() {
		return displayedUsername.getText();
	}

	public AddVideoPage clickGoToAddVideoPage() {
		if(! contributeButton.isDisplayed()){
			clickContributeButton();
		}
		addVideoMenuOption.click();

		return PageFactory.initElements(driver, AddVideoPage.class);
	}
	
}
