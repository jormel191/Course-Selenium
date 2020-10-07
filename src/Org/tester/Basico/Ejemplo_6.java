package Org.tester.Basico;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Ejemplo_6 {
    //instanciar un objeto de tipo webdriver
    static WebDriver driver;
    static String chromePath = System.getProperty("user.dir") + "\\d\\chromedriver.exe";

    public static void main(String[] args) {
        //Declarar variables
        String BaseUrl = "https://www.falabella.com/falabella-cl/";
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\d\\chromedriver.exe");
        //Abrir el navegador
        driver = new ChromeDriver();
        driver.get(BaseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  //Espera hasta 15 segundo para ejecutar un línea de codígo

        WebDriverWait waitVar = new WebDriverWait(driver, 10);

        try {


            driver.findElement(By.xpath("//div[@id='hamburgerMenu']")).click();

            Thread.sleep(1000);

          /*  driver.switchTo().frame("iframeResult");
            WebElement iframe = driver.findElement(By.xpath("/html/body/button"));
            waitVar.until(ExpectedConditions.elementToBeClickable(iframe));
            iframe.click();
            Thread.sleep(2000);

            waitVar.until(ExpectedConditions.alertIsPresent());
            Alert alrWindow = driver.switchTo().alert();
            String alertText = alrWindow.getText();
            System.out.println(alertText);
            alrWindow.sendKeys("Jormel figueroa");
            alrWindow.accept();

            String finalText = driver.findElement(By.id("demo")).getText();
            System.out.println(finalText.contains("Jormel") ? finalText : "Prueba fallida");  //De esta forma de hace una comparación en selenium
        */
        } catch (NoSuchElementException ne) {

            System.err.println("No se encontro el webElement " + ne.getMessage());
        } catch (NoSuchFrameException nf){
            System.err.println("No se encontro iframe" + nf.getMessage());
        }
        catch (NoAlertPresentException na ){
            System.err.println("No se encontro la alerta " + na.getMessage());
        }catch (TimeoutException te){
            System.err.println("Tiempo de espera excedido " + te.getMessage());
        } catch (WebDriverException me){
            System.err.println("Webdriver Falló" + me.getMessage());
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        } finally {
            driver.close();
        }
    }
}


