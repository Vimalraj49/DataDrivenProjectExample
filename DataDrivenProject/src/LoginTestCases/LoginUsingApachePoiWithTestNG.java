package LoginTestCases;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginUsingApachePoiWithTestNG {

    WebDriver driver;
    static List<String> usernameList = new ArrayList<>();
    static List<String> passwordList = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "F:\\Selenium learning\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    
    public Object[][] loginData() throws IOException {
        readExcelData();
        int dataSize = Math.min(usernameList.size(), passwordList.size());
        Object[][] data = new Object[dataSize][2];
        for (int i = 0; i < dataSize; i++) {
            data[i][0] = usernameList.get(i);
            data[i][1] = passwordList.get(i);
        }
        return data;
    }

    public void readExcelData() throws IOException {
        FileInputStream excel = new FileInputStream("C:\\Users\\PC\\OneDrive\\Desktop\\TestData1.xlsx");
        Workbook workbook = new XSSFWorkbook(excel);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row rowValue = rowIterator.next();
            Iterator<Cell> columnOrCellIterator = rowValue.iterator();

            int i = 2;
            while (columnOrCellIterator.hasNext()) {
                if (i % 2 == 0) {
                    usernameList.add(columnOrCellIterator.next().getStringCellValue());
                } else {
                    passwordList.add(columnOrCellIterator.next().getStringCellValue());
                }
                i++;
            }
        }
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameElement = driver.findElement(By.name("username"));
        WebElement passwordElement = driver.findElement(By.name("password"));

        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();

        // Add your assertions or validation here
        // Example: Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }
}

