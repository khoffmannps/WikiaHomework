package runner;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TestManager {
	
	private static Properties prop;
	
	private static void loadProperties(){
		
		prop = new Properties();
		InputStream stream = TestManager.class.getClassLoader().getResourceAsStream(
				"wikiahomework.properties");
		try {
			prop.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("Cannot locate file \"wikiahomework.properties\"");
			
		}
	}
	public static void moveCursorOutOfTheWay(){
		try {
			Robot robot = new Robot();
			robot.mouseMove(0, 0);
		} catch (AWTException e) {
			
			System.out.println("Robot failed ! Cursor might interfere with test !");
			
		}
		
	}

	public static String getProperty(String key){
		if(prop == null){
			loadProperties();
		}
		return prop.getProperty(key);
	}
	
	public static WebDriver getDriver(){
		if(prop == null){
			loadProperties();
		}
		
		
		if(getProperty("drivertype").equals("chrome")){
			System.setProperty("webdriver.chrome.driver", getProperty("chromedriverpath"));
			return new ChromeDriver();
		}
		else {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setEnableNativeEvents(false);
			return new FirefoxDriver(profile);
		}

	}

}