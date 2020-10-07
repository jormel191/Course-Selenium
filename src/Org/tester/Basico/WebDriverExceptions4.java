package Org.tester.Basico;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverExceptions4 {
    static WebDriver driver;
    public static void main(String[] args) {

        try {

            //instanciar un objeto de tipo webdriver

            //Declarar variables
            String BaseUrl = "http://live.demoguru99.com/index.php/checkout/cart/";
            String actualResult = "";
            String expectedResult = "$615.00";
            String chromePath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");

            //Indicar la localización del archivo chromedriver.exe en la propiedad webdriver.chrome
            //System.getproperty ("user.dir") = C: \..\..\..\SeleniumWebDriver
            //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");

            //Abrir el navegador
            driver = new ChromeDriver();
            //Navegar a la pagina
            driver.get(BaseUrl);
            driver.manage().window().maximize();

            //click en TV
            WebElement lnkTV = driver.findElement(By.linkText("TV"));
            lnkTV.click();

            //Click add to cart
            WebElement BtnAddtoCart = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/button/span/span"));
            BtnAddtoCart.click();
            //Obtener el precio del objeto 615.00

            WebElement lblSubTotal = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[5]/span/span"));
            actualResult = lblSubTotal.getText();

            if (actualResult.contentEquals(expectedResult)) {

                System.out.print("prueba pasada, el resultado es: " + actualResult + " es igual a: " + expectedResult);
            } else {

                System.out.println("prueba fallida, el resultado: " + actualResult + " es diferente a: " + expectedResult);
            }

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


