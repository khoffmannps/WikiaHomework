package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddVideoPage {

	private final WebDriver driver;

	@FindBy(id = "wpWikiaVideoAddUrl")
	private WebElement typeVideoURL;

	@FindBy(css = "form > div > input")
	private WebElement submitVideoButton;

	@FindBy(className = "msg")
	private WebElement flashMessage;

	@FindBy(css = "msg > a")
	private WebElement flashMessageLink;

	public AddVideoPage(WebDriver driver) {
		this.driver = driver;
	}

	public AllVideosPage addVideo(String URL) {
		typeVideoURL.sendKeys(URL);
		new Actions(driver).clickAndHold(submitVideoButton).release().perform();
		return PageFactory.initElements(driver, AllVideosPage.class);
	}

	public WebElement getFlashMessage() {

		return flashMessage;
	}

	public WebElement getFlashMessageLink() {
		return flashMessageLink;
	}

	public VideoPage openFlashMessageLink() {

		getFlashMessageLink().click();
		return PageFactory.initElements(driver, VideoPage.class);
	}

}
