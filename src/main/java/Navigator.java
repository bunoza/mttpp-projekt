import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navigator {
    static void goToHomePage(WebDriver driver){
        driver.findElement(By.xpath("//img[@alt='HonorBuy Shop']")).click();
    }
}
