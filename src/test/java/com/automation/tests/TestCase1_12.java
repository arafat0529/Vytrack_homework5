package com.automation.tests;


import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalendarEventsPage;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DateTimeUtilities;
import com.automation.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test Case #1 1.Go to “https://qa1.vytrack.com/"
 * 2.Login as a store manager
 * 3.Navigate to “Activities -> Calendar Events”
 * 4.Hover on three dots “...” for “Testers meeting” calendar event
 * 5.Verify that “view”, “edit” and “delete” options are available
 */



public class TestCase1_12 extends AbstractTestBase {
    LoginPage loginPage = new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
    private WebDriver driver = Driver.getDriver();

    @Test
    public void test1(){
        test = report.createTest("test#1");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(2);
        calendarEventsPage.hoverOverThreeDots();
        BrowserUtils.wait(2);
        Assert.assertTrue(calendarEventsPage.isIconVisible("View"));
        Assert.assertTrue(calendarEventsPage.isIconVisible("Edit"));
        Assert.assertTrue(calendarEventsPage.isIconVisible("Delete"));
        test.pass("Test passed");
    }

    @Test
    public void test2() {
        test = report.createTest("test#2");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(2);
        driver.findElement(By.cssSelector("[title='Grid Settings']")).click();
        BrowserUtils.wait(2);
        List<WebElement> names = driver.findElements(By.xpath("//tbody[@class='ui-sortable']//tr"));
        List<WebElement> options = driver.findElements(By.xpath("//tbody[@class='ui-sortable']//tr//td[3]//input"));
        for (int i = 0; i <names.size() ; i++) {
            if ((!names.get(i).getText().equals("Title")) && options.get(i).isSelected()){
                options.get(i).click();
            }
        }
        test.pass("Test passed");
    }

    @Test
    public void test3() {
        test = report.createTest("test#3");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(2);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        WebElement button = driver.findElement(By.className("caret"));
        actions.moveToElement(button).pause(2000).click().build().perform();
        BrowserUtils.wait(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("[class= 'action-button dropdown-item']")).getText(), "Save And Close");
        Assert.assertEquals(driver.findElement(By.cssSelector("[class= 'main-group action-button dropdown-item']")).getText(), "Save And New");
        Assert.assertEquals(driver.findElement(By.cssSelector("[class='main-group action-button dropdown-item'][data-action='{\"route\":\"oro_calendar_event_update\",\"params\":{\"id\":\"$id\"}}']")).getText(), "Save");
        test.pass("Test passed");
    }

    @Test
    public void test4() {
        test = report.createTest("test#4");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(2);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        driver.findElement(By.cssSelector("[class='btn back icons-holder-text ']")).click();
        BrowserUtils.wait(2);
        WebElement subtitle = driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/div/div/div[1]/div/div/div/div[1]/div/h1"));
        Assert.assertTrue(subtitle.isDisplayed());
        test.pass("Test passed");
    }

    @Test
    public void test5() {
        test = report.createTest("test#5");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(2);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        String startTime = calendarEventsPage.getStartTime();
        String endTime = calendarEventsPage.getEndTime();
        Assert.assertEquals(DateTimeUtilities.getTimeDifference(startTime, endTime, "h:m a"),1);
        test.pass("Test passed");
    }

    @Test
    public void test6() {
        test = report.createTest("test#6");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.setStartTime("09:00 PM");
        BrowserUtils.wait(2);
        Assert.assertEquals(calendarEventsPage.getEndTime(),"10:00 PM");
        test.pass("Test passed");
    }


    @Test
    public void test7() {
        test = report.createTest("test#7");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnAllDayEvent();
        BrowserUtils.wait(2);
        Assert.assertFalse(calendarEventsPage.isTimesAreDisplayed());
        Assert.assertTrue(calendarEventsPage.isDatesAreDisplayed());
        test.pass("Test passed");
    }

    @Test
    public void test8() {
        test = report.createTest("test#8");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        BrowserUtils.wait(2);
        Assert.assertTrue(calendarEventsPage.isRepeatBoxSelected());
        Select select = new Select(driver.findElement(By.className("recurrence-repeats__select")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Daily");
        Assert.assertTrue(select.getOptions().size() > 1);
        test.pass("Test passed");
    }

    @Test
    public void test9() {
        test = report.createTest("test#9");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        BrowserUtils.wait(2);
        Assert.assertTrue(calendarEventsPage.isRepeatBoxSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("[type='radio'][checked='checked']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Never')]/..//input")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Summary:')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Daily every 1 day')]")).isDisplayed());
        test.pass("Test passed");
    }

    @Test
    public void test10() {
        test = report.createTest("test#10");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//span[contains(text(),'After')]/../input[@type='radio'] ")).click();
        driver.findElement(By.xpath("//span[contains(text(),'After')]/../input[@type='text'] ")).sendKeys("10",Keys.ENTER);
        BrowserUtils.wait(2);
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Summary:')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Daily every 1 day')]")).isDisplayed());
        String actual = driver.findElement(By.xpath("//span[contains(text(),'Daily every 1 day')]/following-sibling::span")).getText();
        String expected = ", end after 10 occurrences";
        Assert.assertEquals(actual, expected);
        test.pass("Test passed");
    }

    @Test
    public void test11() {
        test = report.createTest("test#11");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//span[contains(text(),'By')]/../input[@type='radio']")).click();
        driver.findElement(By.xpath("//span[@class='recurrence-subview-control__datetime-wrapper']//input[2]")).sendKeys("Nov 18, 2021",Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Summary:')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Daily every 1 day')]")).isDisplayed());
        String actual = driver.findElement(By.xpath("//span[contains(text(),'Daily every 1 day')]/following-sibling::span")).getText();
        String expected = ", end by Nov 18, 2021";
        Assert.assertEquals(actual, expected);
        test.pass("Test passed");
    }

    @Test
    public void test12() {
        test = report.createTest("test#12");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        BrowserUtils.waitForPageToLoad(10);
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(2);
        calendarEventsPage.clickOnRepeatBox();
        BrowserUtils.wait(2);
        Select select = new Select(driver.findElement(By.className("recurrence-repeats__select")));
        select.selectByVisibleText("Weekly");
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//label[@class='multi-checkbox-control__item']/input[@value='monday']")).click();
        driver.findElement(By.xpath("//label[@class='multi-checkbox-control__item']/input[@value='friday']")).click();
        BrowserUtils.wait(2);
        Assert.assertTrue(driver.findElement(By.xpath("//label[@class='multi-checkbox-control__item']/input[@value='monday']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[@class='multi-checkbox-control__item']/input[@value='friday']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Summary:')]")).isDisplayed());
        String actual = driver.findElement(By.xpath("//label[contains(text(),'Summary:')]/../following-sibling::div/div")).getText();
        String expected = "Weekly every 1 week onMonday, Friday";
        Assert.assertEquals(actual, expected);
        test.pass("Test passed");
    }
}


