package com.cbt.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static com.cbt.utilities.BrowserFactory.getDriver;

public class TitleVerification {
    public static void main(String[] args) throws Exception {
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/",
                                            "http://practice.cybertekschool.com/dropdown",
                                            "http://practice.cybertekschool.com/login");
        String expectedTitle = "Practice";


        WebDriver driver = getDriver("chrome");
        for (int i = 0; i < urls.size() ; i++) {
            driver.get(urls.get(i));
            driver.manage().window().maximize();
            Thread.sleep(3000);
            String actualTitle = driver.getTitle();
            //Assert.assertEquals(expectedTitle, actualTitle);
            if(expectedTitle.equals(actualTitle))
            {
                System.out.println("Title is same");
            }
            else {
                System.out.println("ERROR!");
            }
            //Assert.assertTrue(driver.getCurrentUrl().startsWith("http://practice.cybertekschool.com/"));
            if(driver.getCurrentUrl().startsWith("http://practice.cybertekschool.com/"))
            {
                System.out.println("URL starts with http://practice.cybertekschool.com/ ");
            }
            else {
                System.out.println("ERROR!");
            }
        }
        driver.close();

    }
}
