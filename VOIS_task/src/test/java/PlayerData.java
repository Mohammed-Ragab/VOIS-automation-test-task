import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;


public class PlayerData {


    String nickName = "\"" + RandomStringUtils.randomAlphanumeric(10) + "\"";
    String email = "\"" + RandomStringUtils.randomAlphanumeric(10) + "@gmail.com" + "\"";

    String UserData =
            "{\n" +
                    "\"nickname\":" + nickName + "," + "\n\"email\":" + email + "\n}";

    public String userToken = "";

    int positionX = generateRandomNumber(1,30);
    int positionY = generateRandomNumber(1,30);
    String startOrJoinGameJSON = "{\n" + userToken + "\n}";

    public void setUserToken(String userToken) {
     this.userToken = this.userToken + userToken;

    }

    public int generateRandomNumber(int min , int max)
    {
        int min_val = min;
        int max_val = max;
        Random rand = new Random();
        int randomNum = min_val + rand.nextInt((max_val - min_val) + 1);
        return  randomNum;
    }
}
