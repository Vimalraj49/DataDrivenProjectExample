package LoginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login {
	
	String  data [][] = {
			{"student","admin1"},
			{"Admin","Password123"},
			{"Admin","admin1"},
			{"student","Password123"}			
	};
	
	@DataProvider(name = "loginData")
	public String [][] loginDataProvider() {
		return data;//return username and password set from 2D data varaiable for each iteration to the below test case
	}
	
	@Test(dataProvider = "loginData")
	public void loginCheck(String uName,String pWord) {
		
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium learning\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);
		
		WebElement loginButton = driver.findElement(By.id("submit"));
		loginButton.click();
		driver.quit();
	}

}
