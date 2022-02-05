import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navigator {
    static void goToHomePage(WebDriver driver){
        driver.findElement(By.xpath("//img[@alt='HonorBuy Shop']")).click();
    }

    static void add2ProductsToCart(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"product_list\"]/div/div[2]/div[1]/div/div[1]/a")).click();
        driver.findElement(By.xpath("//div[@id='quantity_wanted_p']/div/a[2]")).click();
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.xpath("//div[@id='shopping_cart']/a/span")).click();
    }

    static void emptyCart(WebDriver driver){
        driver.findElement(By.id("2047_19687_0_3178870")).click();
    }
}
