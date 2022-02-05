import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Validator {

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
