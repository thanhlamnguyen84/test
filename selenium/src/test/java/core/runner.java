package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class runner {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://computer-database.herokuapp.com/computers");
    }

    @AfterTest
    public void tearDown() {driver.quit();}

    @Test
    public void test1() throws Exception {
        WebElement txt_filter = driver.findElement(By.id("searchbox"));
        WebElement btn_submit = driver.findElement(By.id("searchsubmit"));
        txt_filter.sendKeys("Acer Extensa 5220");
        btn_submit.click();
        WebElement computerNameColumn = driver.findElement(By.xpath("/html/body/section/table/tbody/tr/td[1]/a"));
        Assert.assertEquals(computerNameColumn.getText(),"Acer Extensa 5220");
        Thread.sleep(5000);
    }

    @Test
    public void test2() throws Exception {
        WebElement btn_addcomputer = driver.findElement(By.id("add"));
        btn_addcomputer.click();
        WebElement title = driver.findElement(By.xpath("/html/body/section/h1"));
        Assert.assertEquals(title.getText(),"Add a computer");
        WebElement txt_name = driver.findElement(By.id("name"));
        WebElement txt_introduced = driver.findElement(By.id("introduced"));
        WebElement txt_discontinued = driver.findElement(By.id("discontinued"));
        WebElement dropDown = driver.findElement(By.id("company"));
        Select select_dropDown = new Select(dropDown);

        txt_name.sendKeys("Lam Computer");
        txt_introduced.sendKeys("2019-09-30");
        txt_discontinued.sendKeys("2019-09-30");
        select_dropDown.selectByVisibleText("RCA");

        WebElement btn_create = driver.findElement(By.xpath("/html/body/section/form/div/input"));
        btn_create.click();

        WebElement message = driver.findElement(By.xpath("/html/body/section/div[1]"));
        Assert.assertEquals(message.getText(), "Done! Computer Lam Computer has been created");

        Thread.sleep(5000);
    }



}