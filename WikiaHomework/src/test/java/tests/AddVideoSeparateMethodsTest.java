package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AddVideoPage;
import pageObjects.AllVideosPage;
import pageObjects.MainPage;
import pageObjects.VideoPage;
import runner.TestManager;

public class AddVideoSeparateMethodsTest {

	private WebDriver driver;
	private MainPage mainpage;
	private AddVideoPage addvideopage;
	private AllVideosPage allvideospage;
	private String flashMessageFilename;
	private VideoPage videopage;
	
	@BeforeClass
	public void setUp(){
		TestManager.moveCursorOutOfTheWay();
		driver = TestManager.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		mainpage = PageFactory.initElements(driver, MainPage.class)
				.loginToWikia();
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
	@Test(priority=1)
	public void redirectToWikiaMain(){
		driver.get(TestManager.getProperty("wikiaMainURL"));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(
				driver.getCurrentUrl(),
				TestManager.getProperty("redirectedFromWikiaMainURL"),
				"\nRedirected to wrong page !");
	}
	@Test(priority=2)
	public void expandContributeDropdown(){
		mainpage.clickContributeButton();
		Assert.assertTrue(mainpage.isContributeDropdownExpanded(),
				"\n\nContribute dropdown is not visible!");
	}
	
	@Test(priority=3)
	public void clickAddVideo(){
		addvideopage = mainpage.clickGoToAddVideoPage();
		Assert.assertEquals(driver.getCurrentUrl(),
				TestManager.getProperty("wikiaVideoAddPageURL"));
	}
	@Test(priority=4)
	public void addVideo(){
		//pastes video URL and clicks "add" button
		allvideospage = addvideopage.addVideo(TestManager.getProperty("videoToAddURL"));
		// finds if flash message is displayed
		Assert.assertTrue(allvideospage.isFlashMessageDisplayed(),
				"\n\nElement with flash message is missing!");
		// gets "File:FILENAME" string from flash message
		flashMessageFilename = allvideospage.getFlashMessageLinkiText();
		// verifies if flash message displayed is correct
		Assert.assertEquals(
				allvideospage.getFlashMessageText(),
				"Video page " + flashMessageFilename + " succesfully added.",
				"\n\nWrong flashmessage displayed!");
	}
	@Test(priority = 5)
		public void clickFlashMessage(){
		videopage = allvideospage.openFlashMessageLink();
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://testhomework.wikia.com/wiki/" + flashMessageFilename,
				"\n\nRedirected to wrong page !");
	}
	@Test(priority=6)
		public void verifyVideoTitle(){
		String actual = ("File:" + videopage.getVideoTitle()).replace(" ", "_");
		String expected = flashMessageFilename.replace(" ", "_");
		Assert.assertEquals(actual, expected,
				"\n\nWrong filename displayed !");
	}
	
	
	
}
