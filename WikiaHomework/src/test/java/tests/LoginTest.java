package tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.MainPage;
import runner.TestManager;

public class LoginTest {

	private WebDriver driver;
	private SoftAssert softAssert;

	@BeforeTest
	public void setUp() {
		TestManager.moveCursorOutOfTheWay();
		driver = TestManager.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testLoginToWikiaHomework() {

		// Go to WikiaHomework and verify if redirected to the correct page
		MainPage page = PageFactory.initElements(driver, MainPage.class);
		softAssert.assertEquals(driver.getCurrentUrl(),
				TestManager.getProperty("redirectedFromWikiaMainURL"));

		// Hover mouse over login button and verify if login dropdown is
		// displayed
		page.hoverOverLogin();
		softAssert.assertTrue(page.isUserLoginDropdownExpanded(),
				"\n\nLogin dropdown is not visible!");

		// enter username and password, click login button and verify that user
		// is logged in and correct username is displayed
		page.loginToWikia();
		Assert.assertTrue(page.isLoggedIn(), "\nLogin failed");
		softAssert.assertEquals(page.getLogin(),
				TestManager.getProperty("login"),
				"\n\nInvalid username displayed:\n" + page.getLogin()
						+ "\ninstead of:\n" + TestManager.getProperty("login"));
		softAssert.assertAll();
	}

}
