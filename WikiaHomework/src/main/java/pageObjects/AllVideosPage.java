package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllVideosPage {

	private final WebDriver driver;
	
	@FindBy(className = "msg")
	private WebElement flashMessage;

	@FindBy(css = ".msg > a")
	private WebElement flashMessageLink;
	
	public AllVideosPage(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean isFlashMessageDisplayed(){
		return 	flashMessage.isDisplayed();
	}
	
	public String getFlashMessageText(){
		return flashMessage.getText();
	}
	
	public String getFlashMessageLinkiText(){
		return flashMessageLink.getText();
	}

	
	public VideoPage openFlashMessageLink() {

		flashMessageLink.click();
		return PageFactory.initElements(driver, VideoPage.class);
	}
}
