package RegistrationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {

    private WebDriver webDriver;


    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://qa-complexapp.onrender.com/");
    }



    @Test
    public void validRegistration() throws InterruptedException {


        WebElement userName=webDriver.findElement(By.xpath("//input[@name='username' and @id='username-register']"));
        userName.sendKeys("Test2107181216");

        WebElement email=webDriver.findElement(By.xpath("//input[@name='email' and @id='email-register']"));
        email.sendKeys("test2101202316@gmail.com");

        WebElement password=webDriver.findElement(By.xpath("//input[@name='password' and @id='password-register']"));
        password.sendKeys("Test12345678");

        Thread.sleep(3000);

        WebElement button=webDriver.findElement(By.xpath(".//button[@type='submit']"));
        button.submit();


        Thread.sleep(3000);
        WebElement signOut=webDriver.findElement(By.xpath("//button[text()='Sign Out']"));
        String textButton=signOut.getText();

        Assert.assertEquals("Test passed","Sign Out",textButton);

    }


    @Test
    public void notValidEmailRegistration() throws InterruptedException {


        WebElement userName=webDriver.findElement(By.xpath("//input[@name='username' and @id='username-register']"));
        userName.sendKeys("Test210718126");

        WebElement email=webDriver.findElement(By.xpath("//input[@name='email' and @id='email-register']"));
        email.sendKeys("test210120236");

        WebElement password=webDriver.findElement(By.xpath("//input[@name='password' and @id='password-register']"));
        password.sendKeys("Test12345678");

        Thread.sleep(3000);

        WebElement button=webDriver.findElement(By.xpath(".//button[@type='submit']"));
        button.submit();


        Thread.sleep(3000);


        String error=webDriver.findElement(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"))
                .getText();


        Assert.assertEquals("Email not valid","You must provide a valid email address.",error);
        Assert.assertTrue(button.isDisplayed());

    }



    @Test
    public void notValidPasswordRegistration() throws InterruptedException {


        WebElement userName=webDriver.findElement(By.xpath("//input[@name='username' and @id='username-register']"));
        userName.sendKeys("Test218126");

        WebElement email=webDriver.findElement(By.xpath("//input[@name='email' and @id='email-register']"));
        email.sendKeys("test120236@gmail.com");

        WebElement password=webDriver.findElement(By.xpath("//input[@name='password' and @id='password-register']"));
        password.sendKeys("Test1234567");

        Thread.sleep(3000);

        WebElement button=webDriver.findElement(By.xpath(".//button[@type='submit']"));
        button.submit();


        Thread.sleep(3000);


        String error=webDriver.findElement(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"))
                .getText();

        Assert.assertEquals("Email not valid","Password must be at least 12 characters.",error);
        Assert.assertTrue(button.isDisplayed());

    }


    @Test
    public void UserNameAlreadyRegistered() throws InterruptedException {


        WebElement userName=webDriver.findElement(By.xpath("//input[@name='username' and @id='username-register']"));
        userName.sendKeys("Test210718128");

        WebElement email=webDriver.findElement(By.xpath("//input[@name='email' and @id='email-register']"));
        email.sendKeys("test120236@gmail.com");

        WebElement password=webDriver.findElement(By.xpath("//input[@name='password' and @id='password-register']"));
        password.sendKeys("Test123456788");

        Thread.sleep(3000);

        WebElement button=webDriver.findElement(By.xpath(".//button[@type='submit']"));
        button.submit();


        Thread.sleep(3000);


        String error=webDriver.findElement(By.xpath("//div[@class='alert alert-danger small']"))
                .getText();



        Assert.assertEquals("This username is already taken.","This username is already taken.",error);
        //Assert.assertTrue(button.isDisplayed());

    }

    @Test
    public void notValidUsername() throws InterruptedException {


        WebElement userName=webDriver.findElement(By.xpath("//input[@name='username' and @id='username-register']"));
        userName.sendKeys("+380999999999");

        WebElement email=webDriver.findElement(By.xpath("//input[@name='email' and @id='email-register']"));
        email.sendKeys("test120236@gmail.com");

        WebElement password=webDriver.findElement(By.xpath("//input[@name='password' and @id='password-register']"));
        password.sendKeys("Test123456788");

        Thread.sleep(3000);

        WebElement button=webDriver.findElement(By.xpath(".//button[@type='submit']"));
        button.submit();


        Thread.sleep(3000);


        String error=webDriver.findElement(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"))
                .getText();



        Assert.assertEquals("Username can only contain letters and numbers.","Username can only contain letters and numbers.",error);
        Assert.assertTrue(button.isDisplayed());
    }


    @Test
    public void emptyField() throws InterruptedException {



        WebElement button=webDriver.findElement(By.xpath(".//button[@type='submit']"));
        button.submit();



        Thread.sleep(3000);



        String errorUsername=webDriver.findElement(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Username must be at least 3 characters.']"))
                .getText();

        String errorEmail=webDriver.findElement(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='You must provide a valid email address.']"))
                .getText();

        String errorPassword=webDriver.findElement(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Password must be at least 12 characters.']"))
                .getText();



        Assert.assertEquals("Test failed","Username must be at least 3 characters.",errorUsername);

        Assert.assertEquals("Test failed","You must provide a valid email address.",errorEmail);

        Assert.assertEquals("Test failed","Password must be at least 12 characters.",errorPassword);


        Assert.assertTrue(button.isDisplayed());
    }




    @After
    public void end(){
    webDriver.quit();

    }







}
