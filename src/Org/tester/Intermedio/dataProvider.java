package Org.tester.Intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class dataProvider {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @AfterTest
    public void Teardowm(){

        driver.quit();
    }

    @DataProvider(name = "SearchProvider")
    public Object[][] getDataFromDataProvider(){
        return new Object[][]{
                {"Fernando", "Google"}, //M = 1 y N = 1   1x1
                {"Luis", "Yahoo"},
                {"Sara", "Gmail"},
                {"Lorean", "Amazon"},
        };
    }

        @Test(dataProvider = "SearchProvider")
        public void testMetod(String tester, String search) throws InterruptedException {
            WebElement searchText = driver.findElement(By.name("q"));
            searchText.sendKeys(search);
            System.out.println("Welcome ->> " + tester + " your search is --> " + search);
            Thread.sleep(3000);

            String testValue = searchText.getAttribute("value");
            System.out.println("test value is -->> " + testValue + " and is equal to " + search);
            searchText.clear();

            Assert.assertTrue(testValue.equals(search));


        }
}
