package Org.tester.reports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

//Esta clase es para tomar ScreenShot y para sacar un documento PDF
public class BaseClass {
    static WebDriver driver;
    static String chromePath = System.getProperty("webdriver.chrome.driver", "C:\\d\\chromedriver.exe");

public static WebDriver getDriver(){
    if (driver == null){
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    return driver;
}

    public static void takeScreenShot (WebDriver driver, String fileWithPath) throws IOException {
        TakesScreenshot scrShot = (TakesScreenshot)driver;
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileWithPath);
        FileUtils.copyFile(srcFile,destFile);
    }

    public static void  sendPdfReportByEmail(String from, String pass, String to, String subject, String body){
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", 25); //25, 465, 587
        props.put("mail.smt.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);
            message.setText(body);
            BodyPart objMessageBodyPart = new MimeBodyPart();
            objMessageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(objMessageBodyPart);
            objMessageBodyPart = new MimeBodyPart();

            String fileName = System.getProperty("user.dir" + "\\SeleniumIntermedio.pdf");
            //El DataSource "Debe ser de una libreria ACTIVASTION" pero no aparece
            //DataSource source = new FileDataSource(FileName);
            //objMessageBodyPart.setDataHandler(new DataHandler(source));
            objMessageBodyPart.setFileName(fileName);
            multipart.addBodyPart(objMessageBodyPart);

            message.setContent(multipart);
            Transport transport = session.getTransport("Smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            System.err.println("Problems with email addres :" + e.getMessage());
        } catch (MessagingException e) {
            System.err.println("Could not connect to SMTP host, review yout host and port" + e.getMessage());
        }
    }
}