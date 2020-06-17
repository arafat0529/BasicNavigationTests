package com.cbt.tests.homework3;



import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.cbt.utilities.BrowserFactory.getDriver;

public class Testcase1_5 {
    private WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = getDriver("chrome");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
       driver.close();
    }

    @Test
    public void testCase1() throws Exception {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Registration Form")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("birthday")).sendKeys("wrong_dob");
        String warningMessage = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]")).getText();
        Assert.assertEquals(warningMessage, "The date of birth is not valid");
    }

    @Test
    public void testCase2() throws Exception {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Registration Form")).click();
        Thread.sleep(3000);

        String language1 = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[1]/label")).getText();
        Assert.assertEquals(language1, "C++");

        String language2 = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[2]/label")).getText();
        Assert.assertEquals(language2, "Java");

        String language3 = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[3]/label")).getText();
        Assert.assertEquals(language3, "JavaScript");
    }

    @Test
    public void testCase3() throws Exception {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Registration Form")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("firstname")).sendKeys("a");
        String warningMessage = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[1]/div/small[2]")).getText();
        Assert.assertEquals(warningMessage, "first name must be more than 2 and less than 64 characters long");
    }

    @Test
    public void testCase4() throws Exception {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Registration Form")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("lastname")).sendKeys("a");
        String warningMessage = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[2]/div/small[2]")).getText();
        Assert.assertEquals(warningMessage, "The last name must be more than 2 and less than 64 characters long");
    }

    @Test
    public void testCase5() throws Exception {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Registration Form")).click();
        Thread.sleep(3000);

        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Snow");
        driver.findElement(By.name("username")).sendKeys("jonsnow");
        driver.findElement(By.name("email")).sendKeys("jsnow@gmail.com");
        driver.findElement(By.name("password")).sendKeys("youknownothing");
        driver.findElement(By.name("phone")).sendKeys("925-252-9881");
        List<WebElement> genders = driver.findElements(By.name("gender"));
        genders.get(0).click();
        driver.findElement(By.name("birthday")).sendKeys("01/01/2007");
        Select select1 = new Select(driver.findElement(By.name("department")));
        select1.selectByVisibleText("Department of Agriculture");
        Select select2 = new Select(driver.findElement(By.name("job_title")));
        select2.selectByVisibleText("SDET");
        driver.findElement(By.id("inlineCheckbox2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("wooden_spoon")).click();
        Thread.sleep(3000);
        String message = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/p")).getText();
        Assert.assertEquals(message, "You've successfully completed registration!");
    }

}
