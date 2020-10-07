package Org.tester.Intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ParameterByMethod {
    WebDriver driver;

    @BeforeTest (groups = {"A", "B"})
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @AfterTest (groups = {"A"})
    public void Teardowm() {

        driver.quit();
    }

    @Test(dataProvider = "SearchProvider", groups = "A")
    public void testMetodA(String tester, String searchKey) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(searchKey);
        System.out.println("Welcome ->> " + tester + " your search is --> " + searchKey);
        Thread.sleep(3000);

        String testValue = searchText.getAttribute("value");
        System.out.println("test value is -->> " + testValue + " and is equal to " + searchKey);
        searchText.clear();

        Assert.assertFalse(testValue.equals(searchKey));
    }

    @Test(dataProvider = "SearchProvider", groups = "B")
    public void testMetodB(String tester, String searchKey) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(searchKey);
        System.out.println("Welcome ->> " + tester + " your search is --> " + searchKey);
        Thread.sleep(3000);

        String testValue = searchText.getAttribute("value");
        System.out.println("test value is -->> " + testValue + " and is equal to " + searchKey);
        searchText.clear();

        Assert.assertTrue(testValue.equals(searchKey));
    }

    @DataProvider(name = "SearchProvider")
    public Object[][] getDataFromDataProvider(ITestContext c) {
        Object[][] groupArray = null;
        for (String group: c.getIncludedGroups()){
            if(group.equals("A")){
                groupArray = new Object[][]{
                        {"Isabel", "google"},
                        {"Ernesto", "Yahoo"},
                        {"Gabriela", "Amazon"},
                        {"Pedro", "Gmail"}
                };
                break; //Para que salga del ciclo
            }else if (group.equals("B")){
                groupArray = new Object[][]{
                        {"Mexico"},
                        {"Canada"},
                        {"Rusia"},
                        {"Japon"}
                };
                break;
            }
        }
        return groupArray;
    }
}
