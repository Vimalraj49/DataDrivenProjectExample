package LoginTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginUsingApachePoi {

	static List<String> usernameList = new ArrayList<String>();
	static List<String> PasswordList = new ArrayList<String>();

	public void readExcel() throws IOException {

		FileInputStream excel = new FileInputStream("C:\\Users\\PC\\OneDrive\\Desktop\\TestData1.xlsx");

		Workbook workbook = new XSSFWorkbook(excel);

		Sheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator =  sheet.iterator();

		while(rowIterator.hasNext())
		{
			Row	rowValue = rowIterator.next();
			Iterator<Cell> coloumnOrCellIterator = rowValue.iterator();

			int i=2;	
			while(coloumnOrCellIterator.hasNext())
			{
				if( i%2 == 0) {
					usernameList.add(coloumnOrCellIterator.next().getStringCellValue());
				}else {
					PasswordList.add(coloumnOrCellIterator.next().getStringCellValue());
				}
				i++;
			}
		}
	}

	public void login(String uName,String pWord) {

		System.setProperty("webdriver.chrome.driver", "F:\\Selenium learning\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://practicetestautomation.com/practice-test-login/");

		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);

		WebElement loginButton = driver.findElement(By.id("submit"));
		loginButton.click();
	}
	
	public void executeTest() {
		
		for(int i=0;i <usernameList.size();i++)
		{
			login(usernameList.get(i),PasswordList.get(i));
		}		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LoginUsingApachePoi usingPoi = new LoginUsingApachePoi();
		usingPoi.readExcel();
		System.out.println("UserName"+usernameList);
		System.out.println("Password"+PasswordList);
		usingPoi.executeTest();

	}

}
