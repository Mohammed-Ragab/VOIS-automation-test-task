import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DocumentationPage {
    WebDriver driver;
    int waitTime = 20;
    public DocumentationPage(WebDriver driver) {
        this.driver = driver;
    }

    String DocumentationPageURL = "https://piskvorky.jobs.cz/api/doc";

   // create user fields
    By createUserAPIDiv = By.xpath("//div[@id=\"operations-User-post_api_v1_user\"]//div[@class=\"opblock-summary-description\"]");
    By createUserAPITryItOutButton = By.xpath("//div[@class=\"opblock-section-header\"]//button[@class=\"btn try-out__btn\"]");
    By textAreaCreateUserAPI = By.xpath("//textarea[@class=\"body-param__text\"]");
    By createUserAPIExecuteButton = By.xpath("(//div[@class=\"opblock-tag-section is-open\"])[1]//button[@class=\"btn execute opblock-control__btn\"]");

    By resposeOfCreateUserAPI = By.xpath("//pre[@class=\" microlight\"]");
    By resposeCodeOfCreateUserAPI = By.xpath("//*[@id=\"operations-User-post_api_v1_user\"]//table[@class=\"responses-table live-responses-table\"]//td[@class=\"response-col_status\"]");

    // start or join game fields
     By startOrJoinNewGameAPIDiv = By.xpath("//div[@id=\"operations-Game-post_api_v1_connect\"]//div[@class=\"opblock-summary-description\"]");
     By textAreaStartOrJoinGame = By.xpath("(//textarea[@class=\"body-param__text\"])[2]");
     By tryItOutButtonStartOrJoinGame = By.xpath("//div[@class=\"try-out\"]//button[@class=\"btn try-out__btn\"]");
     By executeButtonStartOrJoinGame = By.xpath("(//div[@class=\"execute-wrapper\"])[1]//button[@class=\"btn execute opblock-control__btn\"]");
     By resposeOfStartOrJoinGame = By.xpath("//div[@id=\"operations-Game-post_api_v1_connect\"]//pre[@class=\" microlight\"]");
     By resposeCodeStartOrJoinGame = By.xpath("//*[@id=\"operations-Game-post_api_v1_connect\"]//table[@class=\"responses-table live-responses-table\"]//td[@class=\"response-col_status\"]");

    // play game fields
    By playGameAPIDiv = By.xpath("//div[@id=\"operations-Game-post_api_v1_play\"]//div[@class=\"opblock-summary-description\"]");
    By textAreaPlayGame = By.xpath("(//textarea[@class=\"body-param__text\"])[3]");
    By tryItOutButtonPlayGame = By.xpath("//div[@id=\"operations-Game-post_api_v1_play\"]//button[@class=\"btn try-out__btn\"]");
    By executeButtonPlayGame = By.xpath("//div[@id=\"operations-Game-post_api_v1_play\"]//button[@class=\"btn execute opblock-control__btn\"]");
    By resposeCodePlayGame = By.xpath("(//*[@id=\"operations-Game-post_api_v1_play\"]//table//td[@class=\"response-col_status\"])[1]");


    public void clickOnCreateUserAPIDiv()
    {
        driver.findElement(createUserAPIDiv).click();
    }
    public void clickOnWebElement(By webElement)
    {
        // explicit wait
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        // wait until start game button is visible
        WebElement foundWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        foundWebElement.click();

       // driver.findElement(webElement).click();
    }
    public void clickOnCreateUserTextArea()
    {
        driver.findElement(textAreaCreateUserAPI).click();
    }

    public void clickOnCreateUserAPITryItOutButton()
    {
        driver.findElement(createUserAPITryItOutButton).click();
    }

    public void clearTextOfWebElement(By webElement)
    {
        // explicit wait
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        // wait until start game button is visible
        WebElement foundWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        foundWebElement.clear();
        //driver.findElement(webElement).clear();
    }

    public void clearText()
    {
        driver.findElement(textAreaCreateUserAPI).clear();
    }


    public void writeOnCreateUserAPITryItOutButton(String text)
    {
        driver.findElement(textAreaCreateUserAPI).sendKeys(text);
    }
    public void writeOnWebElement(By webElement,String text)
    {
        driver.findElement(webElement).sendKeys(text);
    }

    public void ClickOnButton(By webElement)
    {

        // explicit wait
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        // wait until start game button is visible
        WebElement foundWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        foundWebElement.click();
        //driver.findElement(webElement).click();
    }

    public String GetAPIresponse(By webElement)
    {
        // explicit wait
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        // wait until start game button is visible
        WebElement foundWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        String text = foundWebElement.getText();
        // String text = driver.findElement(webElement).getText();
        return text;
    }

  /*  public boolean useRegex(final String input) {
        // Compile regular expression
         Pattern pattern = Pattern.compile("\".*\"", Pattern.CASE_INSENSITIVE);
        // Match regex against input
         Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }*/
    public String getTokensOrIDs(String tokenName , String text)
    {
       /* the regular expression
        "userId":\s".{1,}"
        "userToken":\s".{1,}"
        */

        String RegularExpressionPattern = ""; // the matching string must start with "userId:" followed by a space character then followed by " then followed by at least 1 character with any sequence and must end with "

        if (tokenName == "userId")
         {
             RegularExpressionPattern = "\"" + tokenName + "\":\\s\".{1,}\"";
         }
        else if (tokenName == "userToken")
        {
            RegularExpressionPattern = "\"" + tokenName + "\":\\s\".{1,}\"";

        }
        else if (tokenName == "gameToken")
        {
            RegularExpressionPattern = "\"" + tokenName + "\":\\s\".{1,}\"";

        }
        // set regular expression pattern
        Pattern pattern = Pattern.compile(RegularExpressionPattern);

        Matcher matcher = pattern.matcher(text);

        // Get the current matcher state
        MatchResult result = matcher.toMatchResult();
        //System.out.println("\n Current Matcher: " + result +"\n");

        String matchingText = "";
        while (matcher.find())
        {
            //System.out.println(matcher.group());
            matchingText = matcher.group();
        }
        // Get the group matched using group() method
        return matchingText;



    }


    public  String getResposeCode(By webElement)
    {
        // explicit wait
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        // wait until start game button is visible
        WebElement foundWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        String statusCode = foundWebElement.getText();
        return statusCode;
    }



}
