import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;


public class test {
    WebDriver driver;
    @BeforeTest
    public void setUp()
    {
        // set up the web driver automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @AfterTest
    public void tearDown()
    {
        // close the browser after the tests finish
        driver.quit();

    }
    @Test
    public void testPlayGame() {

        HomePage homePage = new HomePage(driver);

        // navigate to the homePage
        driver.get(homePage.homePageURL);
        //driver.get(homePage.gameAPIURL);


        int waitTime = 15;
        // explicit wait
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        // wait until start game button is visible
        WebElement startGameButton = wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.startGameButton));

        startGameButton.click();
        System.out.println("Clicked on the start game button");

        /*// click on the start Game button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Call the JavascriptExecutor methods
        js.executeScript("arguments[0].click();", startGameButton);*/

        // documentation page
        //===================================================================
        DocumentationPage documentationPage = new DocumentationPage(driver);

        documentationPage.clickOnCreateUserAPIDiv();
        System.out.println("Clicked on Create User API Div button");


        // wait until UserAPITryItOutButton is visible
        WebElement CreateUserAPITryItOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(documentationPage.createUserAPITryItOutButton));

        documentationPage.clickOnCreateUserAPITryItOutButton();
        System.out.println("Clicked on Create User API Try It Out Div button");

        WebElement textAreaCreateUserAPI = wait.until(ExpectedConditions.visibilityOfElementLocated(documentationPage.textAreaCreateUserAPI));

        // click on text area
        documentationPage.clickOnCreateUserTextArea();
        System.out.println("Clicked on Create User TextArea");
        // clear the text Area
        documentationPage.clearTextOfWebElement(documentationPage.textAreaCreateUserAPI);
        System.out.println("Cleared text on Create User TextArea");

        // write on the text Area
        PlayerData player =  new PlayerData();

        documentationPage.writeOnCreateUserAPITryItOutButton(player.UserData);
        System.out.println("wrote JSON data on Create User TextArea");

        // click on execute button to create player ID
        documentationPage.ClickOnButton(documentationPage.createUserAPIExecuteButton);
        System.out.println("clicked on execute button");

        // get the response text and extract the userToken
        WebElement APICreateUserResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(documentationPage.resposeOfCreateUserAPI));

        String APIcreatedUserResponse = documentationPage.GetAPIresponse(documentationPage.resposeOfCreateUserAPI);
        System.out.println("printing create API User Response\n=====================\n");
        System.out.println(APIcreatedUserResponse);

        // printing userID to test my method
        System.out.println("\n printing userToken \n");
        String userToken = documentationPage.getTokensOrIDs("userToken",APIcreatedUserResponse);
        System.out.println(userToken);

        // validate response status code is 201
        String createdUserResponseCode =  documentationPage.getResposeCode(documentationPage.resposeCodeOfCreateUserAPI);
        Assert.assertEquals(createdUserResponseCode,"201");
        System.out.println("\n actual create user status code is " + createdUserResponseCode);

       /* ===============================================================================
        // start a game:
        -------------*/

        // click on start a game div
        documentationPage.ClickOnButton(documentationPage.startOrJoinNewGameAPIDiv);

        // click on try it out button (for start a game)
        documentationPage.ClickOnButton(documentationPage.tryItOutButtonStartOrJoinGame);
        System.out.println("clicked on Try it out button to start a game");

        // clear the text Area
        documentationPage.clearTextOfWebElement(documentationPage.textAreaStartOrJoinGame);
        System.out.println("Cleared text on Create User TextArea");

       /* // save userToken to the player
        player.setUserToken(userToken); // player user token is always null, need more time to debug
        System.out.println("\n Player userToken is " +  player.userToken);*/

        String startOrJoinGameJSON = "{\n" + userToken + "\n}";


        // write the userToken on the text area
        documentationPage.writeOnWebElement(documentationPage.textAreaStartOrJoinGame,startOrJoinGameJSON);
        System.out.println("\n write userToken on the text Area");

        // click on execute button
        documentationPage.clickOnWebElement(documentationPage.executeButtonStartOrJoinGame);

        // get the response text and extract the gameToken
        // WebElement startGameAPIResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(documentationPage.resposeOfStartOrJoinGame));
        String startGameAPIResponse = documentationPage.GetAPIresponse(documentationPage.resposeOfStartOrJoinGame);
        System.out.println("\n printing start game Response\n=====================\n");
        System.out.println(startGameAPIResponse);

        // printing gameToken to test my method
        System.out.println("\n printing gameToken \n");
        String gameToken = documentationPage.getTokensOrIDs("gameToken",startGameAPIResponse);
        System.out.println(gameToken);

        // validate response code
        String resposeCodeOfStartOrJoinGame =  documentationPage.getResposeCode(documentationPage.resposeCodeStartOrJoinGame);
        Assert.assertEquals(resposeCodeOfStartOrJoinGame,"201");
        System.out.println("\n start game response actual status code is " + resposeCodeOfStartOrJoinGame);

        //===============================================================================
        // Play a game:
        //-------------

        // click on Play a game div
        documentationPage.ClickOnButton(documentationPage.playGameAPIDiv);

        // click on try it out button (for start a game)
        documentationPage.ClickOnButton(documentationPage.tryItOutButtonPlayGame);
        System.out.println("\n clicked on Try it out button to Play a game \n");

        // clear the text Area
        documentationPage.clearTextOfWebElement(documentationPage.textAreaPlayGame);
        System.out.println("Cleared text on play game TextArea");

        // write the userToken and gameToken and other JSON data on the text area
          /*{
                "userToken": "string",
                "gameToken": "string",
                "positionX": 0,
                "positionY": 0
        }*/

        String playGameJSON = "{\n" +
                userToken +
                ", \n"
                + gameToken +
                ", \n"
                +
                "positionX:" + player.positionX +
                ", \n"
                +
                "positionY:" + player.positionY +
        "\n}";

        documentationPage.writeOnWebElement(documentationPage.textAreaPlayGame,playGameJSON);
        System.out.println("\n write userToken and gameToken on the text Area\n");
        System.out.println(playGameJSON);

        // click on execute button
        documentationPage.clickOnWebElement(documentationPage.executeButtonPlayGame);

        // validate resonse code
        String resposeCodeOfPlayGame =  documentationPage.getResposeCode(documentationPage.resposeCodePlayGame);
        Assert.assertEquals(resposeCodeOfPlayGame,"201");
        System.out.println("\n play game response actual status code is " + resposeCodeOfPlayGame);


    }
}
