package com.cbt.tests.homework3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import static com.cbt.utilities.BrowserFactory.getDriver;

public class Testcase9_12 {
        private WebDriver driver;

        @BeforeClass
        public void setup() {
            driver = getDriver("chrome");
            driver.manage().window().maximize();
        }

        @AfterClass
        public void tearDown() {
            driver.close();
        }

        @DataProvider(name="Test Case 9-12 data")
        public Object[][] testData1(){
        return new Object[][]{  {"200", "200"},
                                {"301", "301"},
                                {"404","404"},
                                {"500", "500"}
                             };
    }

        @Test(dataProvider = "Test Case 9-12 data")
        public void testCase9_12(String linkText, String statusCode) throws Exception {
            driver.get("https://practice-cybertekschool.herokuapp.com/");
            Thread.sleep(2000);
            driver.findElement(By.linkText("Status Codes")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(linkText)).click();
            Thread.sleep(2000);
            String message = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p")).getText();
            String expected = "This page returned a " + linkText + " status code";
            Assert.assertTrue(message.contains(expected));
        }



}
