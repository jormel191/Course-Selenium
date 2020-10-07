package Org.tester.Basico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejemplo2_Locator {
        static  WebDriver driver;
        public static void main(String[] args) {
            //Declarar variables
            String BaseUrl = "http://live.demoguru99.com/index.php/checkout/cart/";
            String actualResult = "";
            String expectedResult = "$620.00";
            String chromePath=  System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe";

            System.setProperty("webdriver.chrome.driver", System.getProperty("C:\\d\\chromedriver.exe"));
            //Abrir el navegador
            driver = new ChromeDriver();
            driver.get(BaseUrl);
            driver.manage().window().maximize();
            driver.findElement(By.linkText("TV")).click();

            //Click add to cart
            driver.findElement(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/button/span")).click();
            //Obtener el precio del objeto 615.00

            actualResult= driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[5]/span/span")).getText();

            if (actualResult.contentEquals(expectedResult)){

                System.out.print("prueba pasada, el resultado es: "+ actualResult + "es igual a: "+ expectedResult );
            }else {

                System.out.println("prueba fallida, el resultado: "+ actualResult + "es diferente a: "+ expectedResult );
            }
            driver.close();
        }
    }
