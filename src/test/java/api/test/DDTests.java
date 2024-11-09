package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;

import org.testng.annotations.Test;

public class DDTests {
    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUsers(String userID,String userName,String fName,String lName,String userEmail,String pwd,String ph)
    {
        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response= UserEndPoints2.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testGetUsersByName(String username)
    {
        Response response= UserEndPoints2.readUser(username);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUsersByName(String username)
    {
        Response response= UserEndPoints2.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
