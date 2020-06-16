package com.cbt.tests;

import org.openqa.selenium.WebDriver;

import java.sql.Driver;

import static com.cbt.utilities.BrowserFactory.getDriver;
import static com.cbt.utilities.StringUtility.verifyEquals;

public class NavigationTests {

    public static String googleTitle = "Google";
    public static String etsyTitle = "Etsy - Shop for handmade, vintage, custom, and unique gifts for everyone";

    public static void main(String[] args) throws Exception {
        testWithChrome();
        testWithEdge();
        //testWithFirefox();

    }

    public static void testWithChrome() throws Exception {
        WebDriver driver = getDriver("chrome");
        driver.get("https://google.com");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        String title1 = driver.getTitle();
        driver.navigate().to("https://etsy.com");
        Thread.sleep(3000);
        String title2 = driver.getTitle();
        System.out.println(title2);
        driver.navigate().back();
        verifyEquals(googleTitle, title1);
        driver.navigate().forward();
        verifyEquals(etsyTitle, title2);
        driver.close();
    }
    public static void testWithFirefox() throws Exception {
        WebDriver driver = getDriver("firefox");
        driver.get("https://google.com");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        String title1 = driver.getTitle();
        driver.navigate().to("https://etsy.com");
        Thread.sleep(3000);
        String title2 = driver.getTitle();
        System.out.println(title2);
        driver.navigate().back();
        verifyEquals(googleTitle, title1);
        driver.navigate().forward();
        verifyEquals(etsyTitle, title2);
        driver.close();
    }
    public static void testWithEdge() throws Exception {
        WebDriver driver = getDriver("edge");
        driver.get("https://google.com");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        String title1 = driver.getTitle();
        driver.navigate().to("https://etsy.com");
        Thread.sleep(3000);
        String title2 = driver.getTitle();
        System.out.println(title2);
        driver.navigate().back();
        verifyEquals(googleTitle, title1);
        driver.navigate().forward();
        verifyEquals(etsyTitle, title2);
        driver.close();
    }



}
