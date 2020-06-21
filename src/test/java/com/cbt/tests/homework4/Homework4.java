package com.cbt.tests.homework4;

import com.cbt.utilities.BrowserUtils;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.cbt.utilities.BrowserFactory.getDriver;
import static java.lang.Integer.parseInt;

public class Homework4 {
    private WebDriver driver;
    Random random = new Random();


    @BeforeClass
    public void setup() {
        driver = getDriver("chrome");
        driver.manage().window().maximize();

    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testCase1() throws Exception {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        Thread.sleep(2000);
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        List<WebElement> label = driver.findElements(By.xpath("//input[@type='checkbox']/following-sibling::label"));

        int counter = 0;
        while(counter < 3)
        {
            int checkboxNumber = random.nextInt(checkboxes.size());
            if(checkboxes.get(checkboxNumber).isEnabled())
            {
                checkboxes.get(checkboxNumber).click();
                System.out.println("Selected ::" + label.get(checkboxNumber).getText());
                checkboxes.get(checkboxNumber).click();
                if(label.get(checkboxNumber).getText().equals("Friday")){
                    counter++;
                }
            }
        }
    }


    @Test
    public void testCase2() throws Exception {
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(2);

        Select selectYear = new Select(driver.findElement(By.id("year")));
        Select selectMonth = new Select(driver.findElement(By.id("month")));
        Select selectDay = new Select(driver.findElement(By.id("day")));
        Assert.assertEquals(selectYear.getFirstSelectedOption().getText(), "2020");
        Assert.assertEquals(selectMonth.getFirstSelectedOption().getText(), "June");
        Assert.assertEquals(selectDay.getFirstSelectedOption().getText(), "19");
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase3() throws Exception{
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(2);
        Select selectYear = new Select(driver.findElement(By.id("year")));
        Select selectMonth = new Select(driver.findElement(By.id("month")));
        Select selectDay = new Select(driver.findElement(By.id("day")));
        int yearToSelect = random.nextInt(selectYear.getOptions().size());
        selectYear.selectByIndex(yearToSelect);
        int year = parseInt(selectYear.getFirstSelectedOption().getText());
        boolean isLeapYear;
        if(year % 4 == 0)
        {
            if( year % 100 == 0)
            {
                // year is divisible by 400, hence the year is a leap year
                if ( year % 400 == 0)
                    isLeapYear = true;
                else
                    isLeapYear = false;
            }
            else
                isLeapYear = true;
        }
        else
            isLeapYear = false;


        for (int i = 0; i < 12 ; i++) {
            LocalDate localDate = LocalDate.of(yearToSelect, i + 1, 1);
            selectMonth.selectByIndex(i);
            int actual = selectDay.getOptions().size();
            int expected = Month.from(localDate).length(isLeapYear);
            Assert.assertEquals(actual, expected);

        }

    }

    @Test
    public void testCase4() throws Exception{
        driver.get("https://www.amazon.com");
        BrowserUtils.wait(2);
        Select selectAll = new Select(driver.findElement(By.id("searchDropdownBox")));
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"nav-search-dropdown-card\"]/div/div/span")).getText(), "All");;
        List<String> originalList = new ArrayList();
        for (WebElement e : selectAll.getOptions()) {
            originalList.add(e.getText());
        }
        List<String> tempList= originalList;
        Collections.sort(tempList);
        Assert.assertEquals(tempList, originalList);
    }


    @Test
    public void testCase5() throws Exception {
        driver.get("https://www.amazon.com/gp/site-directory");
        BrowserUtils.wait(2);
        Select selectAll = new Select(driver.findElement(By.id("searchDropdownBox")));
        List<String> selectNames =  new ArrayList();
        for ( WebElement e: selectAll.getOptions()) {
            selectNames.add(e.getText());
        }

        List<WebElement> departments = driver.findElements(By.xpath("//*[@class=\"fsdDeptBox\"]/h2"));
        List<String> depNames =  new ArrayList();
        for ( WebElement e: departments) {
            depNames.add(e.getText());
        }

       boolean contains = true;
        for (int i = 0; i < depNames.size(); i++) {
            if(!selectNames.contains(depNames.get(i)))
            {
                contains = false;
            }
        }
       if(contains)
       {
           System.out.println("Every department name presents in serach options");
       }
       else {
           System.out.println("Not every department name presents in serach options");
       }
    }


    @Test
    public void testCase6() throws Exception {
        driver.get("https://www.w3schools.com/");
        BrowserUtils.wait(2);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement e: links) {
            if(e.isDisplayed())
            {
                System.out.println(e.getText() + e.getAttribute("href"));
            }
        }
    }

    @Test
    public void testCase7() throws Exception {
        driver.get("https://www.selenium.dev/documentation/en/");
        BrowserUtils.wait(2);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        Iterator<WebElement> it = links.iterator();
        String homePage = "https://www.selenium.dev/documentation/en/";
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        while(it.hasNext()) {

            url = it.next().getAttribute("href");
            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if (!url.startsWith(homePage)) {
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    System.out.println(url + " is a broken link");
                } else {
                    System.out.println(url + " is a valid link");
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCase8() throws Exception {
        driver.get("https://www.amazon.com");
        BrowserUtils.wait(2);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.className("nav-input")).click();
        BrowserUtils.wait(2);
        List<WebElement> pricesWhole = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        List<WebElement> pricesFraction = driver.findElements(By.xpath("//span[@class='a-price-fraction']"));

        List<WebElement> itemName = driver.findElements(By.cssSelector("[class='a-size-base-plus a-color-base a-text-normal']"));

        int randomNumber =  random.nextInt(itemName.size());


        WebElement randomPriceWhole = pricesWhole.get(randomNumber);
        WebElement randomPriceFraction = pricesFraction.get(randomNumber);
        WebElement randomItem = itemName.get(randomNumber);

        String expectedName = itemName.get(randomNumber).getText().trim();
        String expectedPrice = "$" + randomPriceWhole.getText().trim() + "." + randomPriceFraction.getText().trim();


        randomItem.click();
        BrowserUtils.wait(2);
        WebElement quantity = driver.findElement(By.xpath("//span[text()='Qty:']/following-sibling::span"));

        int actualQty = Integer.parseInt(quantity.getText().trim());
        Assert.assertEquals(actualQty, 1);

        String name = driver.findElement(By.id("productTitle")).getText().trim();
        Assert.assertEquals(name, expectedName);


        String price = driver.findElement(By.xpath("//*[@id=\"price_inside_buybox\"]")).getText();

        Assert.assertEquals(price, expectedPrice);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Add to Cart']")).isDisplayed());
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase9() throws Exception {
        driver.get("https://www.amazon.com");
        BrowserUtils.wait(2);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.className("nav-input")).click();
        BrowserUtils.wait(2);
        String primeItem = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[2]/div[3]/div/span/div/div/div/div/div[2]/h2/a/span")).getText();
        driver.findElement(By.xpath("//*[@id=\"p_85/2470955011\"]/span/a/div/label/i")).click();
        String primeItem2 = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[2]/div[3]/div/span/div/div/div/div/div[2]/h2/a/span")).getText();
        BrowserUtils.wait(2);
        Assert.assertEquals(primeItem2, primeItem);
    }

    @Test
    public void testCase10() throws Exception {
        driver.get("https://www.amazon.com");
        BrowserUtils.wait(2);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.className("nav-input")).click();
        BrowserUtils.wait(2);

        List <WebElement> brands = driver.findElements(By.xpath("//div[@id='brandsRefinements']/ul/*"));
        List<String> brandNames = new ArrayList<>();
        for (WebElement e: brands
             ) {
            brandNames.add(e.getText());
        }



        driver.findElement(By.xpath("//*[@id=\"p_85/2470955011\"]/span/a/div/label/i")).click();
        List <WebElement> brands2 = driver.findElements(By.xpath("//div[@id='brandsRefinements']/ul/*"));
        List<String> brandNames2 = new ArrayList<>();
        for (WebElement e: brands2
        ) {
            brandNames2.add(e.getText());
        }


        BrowserUtils.wait(2);
        System.out.println(brandNames);
        System.out.println(brandNames2);
        Assert.assertTrue(brandNames.equals(brandNames2));
    }

    @Test
    public void testCase11() throws Exception {
        driver.get("https://www.amazon.com");
        BrowserUtils.wait(2);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.className("nav-input")).click();
        BrowserUtils.wait(2);

        //3. click on Price option Under $25 on the left
        driver.findElement(By.xpath("(//div[@id='priceRefinements']//span[@class='a-size-base a-color-base'])[1]")).click();

        //4. verify that all results are cheaper than $25
        BrowserUtils.wait(10);
        List<WebElement> allPrice = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        System.out.println(allPrice.size());
        for (WebElement each : allPrice) {
            System.out.println(each.getText());
            int price = Integer.parseInt(each.getText());
            Assert.assertTrue(price < 25);
        }
    }
}



