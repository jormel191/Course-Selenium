package Org.tester.Intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNG {
    String baseURL = "http://newtours.demoaut.com/";
    WebDriver driver;
    String ExpectedResult = "";
    String ActualResult = "";
    String chromePath= System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";

    @BeforeTest
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void verifyHomePageTitle(){
        ExpectedResult = "Welcome: Mercury tours";
        ActualResult= driver.getTitle();
        Assert.assertEquals(ActualResult, ExpectedResult  );
    }

    @AfterMethod
    public void goBackToHomePage(){
        driver.findElement(By.linkText("Home")).click();
    }

    @AfterTest
    public void goforward(){
        driver.quit();
    }

    @Test (priority = 1)//para elegir cual quiero que se ejecute primero
    public void register(){
        driver.findElement(By.linkText("REGISTER")).click();
        ExpectedResult = "Register: Mercury tours";
        ActualResult= driver.getTitle();
        Assert.assertEquals(ActualResult, ExpectedResult) ;
    }
    @Test (priority = 0, enabled = false) //si utilizo este metodo <enabled = false> este metoo no se ejecutara
    public void support (){
        driver.findElement(By.linkText("SUPPORT")).click();
        ExpectedResult = "Under Construction: Mercury tours";
        ActualResult= driver.getTitle();

        Assert.assertEquals(ActualResult,ExpectedResult);
    }
}
