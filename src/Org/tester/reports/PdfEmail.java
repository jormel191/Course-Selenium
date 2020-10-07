package Org.tester.reports;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//Esta clase es para tomar ScreenShot y para sacar un documento PDF
@Listeners(JyperionListener.class)
public class PdfEmail extends BaseClass {
    WebDriver driver = getDriver();

    @Test
    public void testOne(){
        driver.get("https://www.google.com/");
        Assert.assertTrue( false);

    }
    @Test
    public void tesTwo(){
        driver.get("https://www.facebook.com/");
        Assert.assertTrue( true);
    }
    @Test
    public void testThree(){
        driver.get("https://titaniumsolutions.org/");
        Assert.assertTrue(false);
    }
    @AfterTest
    public void CloseeBrowser(){
        driver.quit();
    }
    @AfterSuite
    public void senEmail(){
        sendPdfReportByEmail("jormel17@gmail.com", "Meljorr2323775", "jormel17@gmail.com", "Email", "este es una prueba");
    }
}
