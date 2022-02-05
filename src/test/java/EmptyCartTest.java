import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmptyCartTest {
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
    public void honorbuyEmptyCartTest() throws InterruptedException {
        driver.manage().window().maximize();
        Validator.logIn(driver);
        Navigator.goToHomePage(driver);
        Navigator.add2ProductsToCart(driver);
        Navigator.emptyCart(driver);
        Thread.sleep(1500);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText(), "Your shopping cart is empty.");
    }
    //---------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}
