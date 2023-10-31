package LoginTestCases;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Loginusingjxl {
	WebDriver driver;
	String data[][] = null;
	
	@DataProvider(name = "loginData")
	public String [][] loginDataProvider() throws BiffException, IOException {
		
		data = getExcelData();//"testData" will be returned here and stored in "data"(both are 2D arrays)
		return data;		  //store "data" will be returns to the "test" method "loginCheck" as username and password string's  
	}
	
	public String[][] getExcelData() throws BiffException, IOException {
		
		FileInputStream excel = new FileInputStream("C:\\Users\\PC\\OneDrive\\Desktop\\TestData.xla");//file location given
		
		Workbook workbook = Workbook.getWorkbook(excel);//Workbook given
		
		Sheet sheet = workbook.getSheet(0);//sheet given
		
		int rowCount =  sheet.getRows();	
		int coloumnCount = sheet.getColumns();
		
		String testData[][] = new String [rowCount-1][coloumnCount];
		
		//Excel structure will be like(j,i)
		//2D array structure will be like(i,j)
		
		for(int i=1; i<rowCount; i++)
		{
			for(int j=0; j<coloumnCount; j++)
			{
				testData [i-1][j] = sheet.getCell(j, i).getContents();//[i-1]-->[1-1]-->[0] and [j]-->[0] which gives testdata [0][0] as 1st pos in 2d array
			}
		}
		return testData;
	}
	
	@BeforeTest
	public void beforeTest() {
		
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium learning\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	@Test(dataProvider = "loginData")
	public void loginCheck(String uName,String pWord) {
		
		
		driver.get("https://practicetestautomation.com/practice-test-login/");
		
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);
		
		WebElement loginButton = driver.findElement(By.id("submit"));
		loginButton.click();
		
	}

}
