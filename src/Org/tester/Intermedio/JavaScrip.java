package Org.tester.Intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class JavaScrip {
    private WebDriver driver;
    String expectResult = null;
    String Actualresult = null;
    String baseURL = "http://newtours.demoaut.com/";
    private JavascriptExecutor js;
    String pageLoadStatus = "";

    private boolean highLight(WebElement element){
        js = (JavascriptExecutor)driver;
        for (int iCnt = 0; iCnt<3; iCnt++){
            try{
                js.executeScript("arguments[0].setAttribute ('style', 'background:red')",element); //Sirve para que se muestre de color rojo lo que se selecciona
                Thread.sleep(1000);
                js.executeScript("arguments[0].setAttribute ('style', 'background:')",element);
        }catch (Exception e){
                System.err.println("JavaScript | Method : highLight | Exception desc:" + e.getMessage());
                return false;
            }
        }
        return true;
    }
    private boolean scrollWindow () {
        try {
            js = (JavascriptExecutor) driver;
            //Scroll up (0,-250) / down (0,250)
            js.executeScript("window.scrollby(0.250)");

        } catch (Exception e) {
            System.err.println("JavaScript | Method : scrollWindow | Exception desc:" + e.getMessage());
            return false;
        }
        return true;
    }
        private boolean waitForPageToLoad() {
            try {
                do {
                    js = (JavascriptExecutor) driver;
                    pageLoadStatus = (String) js.executeAsyncScript("return document.readyState");
                } while (!pageLoadStatus.equals("complete"));
            } catch (Exception e) {
                System.err.println("JavaScript | Method : waitForPageToLoad | Exception desc:" + e.getMessage());
                return false;
            }
            return true;
        }

            @BeforeTest
            public void launchBrowser () {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(baseURL);
                waitForPageToLoad();
            }
            @AfterTest
            public void tearDown(){
                driver.quit();
            }

            @Test(priority = 0)
            public void goToRegisterPage () {
            WebElement lnkRegister = driver.findElement(By.linkText("REGISTER"));
            Assert.assertTrue(highLight(lnkRegister));
            js.executeScript("arguments[0].click();", lnkRegister); //Se hará un click con javaScript para la interacción
            expectResult = "Register: Mercury Tours";
            Actualresult = driver.getTitle();
            Assert.assertEquals(Actualresult,expectResult);
            Assert.assertFalse(scrollWindow());
                }
                @Test(priority = 1)
                public void registerAnUser () {
                    try {
                        WebElement txtFirstName = driver.findElement(By.name("firstName"));
                        highLight(txtFirstName);
                        txtFirstName.sendKeys("Jormel");

                        WebElement ddlCountry = driver.findElement(By.name("country"));
                        highLight(ddlCountry);
                        new Select(ddlCountry).selectByVisibleText("AUSTRIA");

                        highLight(driver.findElement(By.id("email")));
                        driver.findElement(By.id("email")).sendKeys("jormel17@gmail.com");

                        highLight(driver.findElement(By.name("password")));
                        driver.findElement(By.name("password")).sendKeys("123");
                        WebElement txtConfirmpass = driver.findElement(By.name("confirmPassword"));
                        highLight(txtConfirmpass);
                        txtConfirmpass.sendKeys("123");
                        txtConfirmpass.submit();

                        Assert.assertFalse(waitForPageToLoad());
                        highLight(driver.findElement(By.xpath("//*[contains(text(), 'Note')]")));


                    } finally {

                    }
                    }
                }
