import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

   WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    String homePageURL = "https://piskvorky.jobs.cz/";
    String gameAPIURL = "https://piskvorky.jobs.cz/api/doc";

    // represents the <a> HTML element of the button
    By startGameButton = By.xpath("//div[@class=\"col-md-8 col-sm-12\"]//a[@href=\"/api/doc\"]");


    public void clickOnStartGameButton()
    {
      driver.findElement(startGameButton).click();
    }

}
