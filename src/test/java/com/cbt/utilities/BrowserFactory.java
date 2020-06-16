package com.cbt.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BrowserFactory {

    private static WebDriver driver;


    public static WebDriver getDriver(String browserName){

        String opSystem = System.getProperty("os.name").toLowerCase();
        /*
        * this code work on windows os, if using mac please change the code below accordingly
        * */
        if(opSystem.toLowerCase().contains("win") && browserName.contains("safari")){
            driver = null;
        }
        else
        {
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().browserVersion("44.18362.449.0").setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }

        return driver;
    }


}
