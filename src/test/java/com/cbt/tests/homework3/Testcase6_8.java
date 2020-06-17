package com.cbt.tests.homework3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;

import java.security.Key;

import static com.cbt.utilities.BrowserFactory.getDriver;

public class Testcase6_8 {
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
    public void testCase6() throws Exception {
        driver.get("https://www.fakemail.net/");
        String email = driver.findElement(By.id("email")).getText();
        Thread.sleep(2000);
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("full_name")).sendKeys("Tom Hans");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("wooden_spoon")).click();
        Thread.sleep(2000);
        String message = driver.findElement(By.name("signup_message")).getText();
        Assert.assertEquals(message, "Thank you for signing up. Click the button below to return to the home page.");
        Thread.sleep(2000);
        driver.navigate().to("https://www.fakemail.net/");
        Thread.sleep(2000);
        String emailAddress = driver.findElement(By.xpath("//table[@class='table table-hover']//tbody//tr[1]//td[1]")).getText().trim();
        Assert.assertEquals(emailAddress, "do-not-reply@practice.cybertekschool.com");
        driver.findElement(By.xpath("//table[@class='table table-hover']//tbody//tr[1]")).click();
        Thread.sleep(2000);

        String emailAddress2 = driver.findElement(By.id("odesilatel")).getText();
        Assert.assertEquals(emailAddress2, "do-not-reply@practice.cybertekschool.com");

        String subject = driver.findElement(By.id("predmet")).getText();
        Assert.assertEquals(subject, "Thanks for subscribing to practice.cybertekschool.com!");
    }

    @Test
    public void testCase7() throws Exception {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(2000);
        driver.findElement(By.linkText("File Upload")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("file-upload")).sendKeys("C:\\Users\\Arafat\\Desktop\\test.txt");
        Thread.sleep(2000);
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(2000);
        String subject = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(subject, "File Uploaded!");
        String fileName = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(fileName, "test.txt");
    }

    @Test
    public void testCase8() throws Exception {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Autocomplete")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();
        Thread.sleep(2000);
        String message = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(message, "You selected: United States of America");
    }
}
