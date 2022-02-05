import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogInTest {

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
    public void honorbuyLogInTest() throws InterruptedException {
        driver.manage().window().maximize();
        logIn(driver);
    }
    //---------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }

    static void logIn(WebDriver driver){
        Credentials credentials = new Credentials("x", "y");

        try {
            File file = new File("C:\\Users\\Domagoj\\Desktop\\credentials.txt");
            Scanner myReader = new Scanner(file);
            String[] input = new String[2];
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input = data.split(",");
            }
            myReader.close();
            credentials = new Credentials(input[0], input[1]);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(credentials.getEmail());
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys(credentials.getPassword());
        driver.findElement(By.id("SubmitLogin")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"header_user_info\"]/div[3]/a/span")).getText(), "Domagoj Bunoza");
    }

}
