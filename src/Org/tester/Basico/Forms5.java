package Org.tester.Basico;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Forms5 {
    //instanciar un objeto de tipo webdriver
    static WebDriver driver;
    public static void main(String[] args) {

        //Declarar variables
        String BaseUrl = "http://newtours.demoaut.com/";
        String chromePath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");

        //Abrir el navegador
        driver = new ChromeDriver();
        driver.get(BaseUrl);
        driver.manage().window().maximize();

        try {
            driver.findElement(By.linkText("REGISTER")).click();

            WebElement txtFirstName= driver.findElement(By.xpath("//input[@name=\"firstName\"]"));
            txtFirstName.sendKeys("Pedro perez");
            Thread.sleep(2000);
            txtFirstName.clear();
            Thread.sleep(2000);
            txtFirstName.sendKeys("Jormel");
            driver.findElement(By.name("address1")).sendKeys("jormel17@gmail.com");

            Select drpcountry = new Select(driver.findElement(By.name("country")));
            drpcountry.selectByValue("221");
            Thread.sleep(2000);
            //Si fuera por visible text se usaria este script
            //drpcountry.selectByVisibleText("Venezuela");

            driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys("Jormel17@gmail.com");
            driver.findElement(By.name("password")).sendKeys("123456");


            WebElement txtConfirmPass= driver.findElement(By.name("confirmPassword"));
            txtConfirmPass.sendKeys("123456");
            txtConfirmPass.submit();

            System.out.println("Prueba exitosa " + driver.findElement(By.xpath("//*[contains(text(), 'Note:')]")).getText());

        }catch (NoSuchElementException ne) {
            System.err.println("No se encontro el webElement " + ne.getMessage());
        } catch (WebDriverException me){
            System.err.println("Webdriver Falló" + me.getMessage());
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }

        finally {
            driver.close();
        }
    }
}


