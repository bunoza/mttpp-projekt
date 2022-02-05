import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToWishListTest {
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
    public void honorbuyAddToWishListTest() throws InterruptedException {
        driver.manage().window().maximize();
        Validator.logIn(driver);
        Navigator.goToHomePage(driver);
        driver.findElement(By.xpath("//nav[@id='cbp-hrmenu']/ul/li[4]/a/span")).click();
        driver.findElement(By.xpath("//div[@id='subcategories']/div/div/div[2]/div/div/a/h3")).click();
        driver.findElement(By.id("selectPrductSort")).click();
        new Select(driver.findElement(By.id("selectPrductSort"))).selectByVisibleText("In-stock first");
        driver.findElement(By.xpath("//img[@alt='NILLKIN Flip Wallet Leather Case for Xiaomi 11 Lite']")).click();
        String name = driver.findElement(By.xpath("//*[@id=\"pb-left-column\"]/h1/span")).getText();
        driver.findElement(By.xpath("//a[@id='wishlist_button_nopop']/i")).click();
        driver.navigate().to("https://www.honorbuy.com/module/blockwishlist/mywishlist");
        driver.findElement(By.linkText("View")).click();
        Thread.sleep(1000);
        String nameWishlist = driver.findElement(By.xpath("//*[@id=\"s_title\"]")).getText();
        Assert.assertEquals(nameWishlist, name);
    }
    //---------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}
