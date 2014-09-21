package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VideoPage {
	@SuppressWarnings("unused")
	private final WebDriver driver;
	
	
	@FindBy(css ="#WikiaPageHeader > h1")
	private WebElement videoName;
	
	public VideoPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getVideoTitle(){
		return videoName.getText();
	}
}
