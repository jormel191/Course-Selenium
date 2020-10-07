package Org.tester.Intermedio;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Action_Example1 {
        static  WebDriver driver;
        public static void main(String[] args) {
            //Declarar variables
            String BaseUrl = "https://www.facebook.com/";
            String chromePath=  System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");

            driver = new ChromeDriver();
            //Navegar a la pagina
            driver.get(BaseUrl);
            driver.manage().window().maximize();


            try {
            WebElement txt = driver.findElement(By.id("email"));


                Actions builder = new Actions(driver);

                Action seriesOfAction = builder
                        .moveToElement(txt)
                        .click()
                        .keyDown(Keys.SHIFT)
                        .sendKeys("hello")
                        .keyUp(Keys.SHIFT)
                        .doubleClick()
                        .contextClick(txt)
                        .build();

                seriesOfAction.perform();
                Thread.sleep(2000);

                System.out.println("Test Paseed!");

            } catch (NoSuchElementException ne) {
                System.err.println("No se encontro el webElement " + ne.getMessage());
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            } finally {
                driver.close();
            }
        }
    }

