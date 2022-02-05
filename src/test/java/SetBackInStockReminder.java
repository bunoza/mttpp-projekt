import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SetBackInStockReminder {
    //-------------------Global Variables-----------------------------------
    public WebDriver driver;
    public String testURL = "https://www.honorbuy.com/";

    //----------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(testURL);
    }
    @Test
    public void honorbuySetBackInStockReminder() throws InterruptedException {
        driver.manage().window().maximize();
        Validator.logIn(driver);
        Navigator.goToHomePage(driver);
        driver.findElement(By.xpath("//*[@id=\"product_list\"]/div/div[1]/div[1]/div/div[1]/a")).click();
        driver.findElement(By.id("submit_subscribe")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"hi-oosn-block\"]/div/div/span")).getText(), "This email address already subscribed.");
    }
    //---------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}
