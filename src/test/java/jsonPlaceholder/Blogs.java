package jsonPlaceholder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Blogs {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void itShouldGetPostByID_2(){
        Response response = given()
                .basePath("todos")
                .when()
                .get("2")
                .then()
                .extract()
                .response();

        // Printing the report in pretty format
        response.prettyPrint();
        // Assert to verify the request is successful by checking the status code
        Assert.assertEquals(response.getStatusCode(),200);
        // Assert to verify id 2 is not completed
        Assert.assertEquals(response.jsonPath().getString("completed"),"false");
    }

    @Test
    public void itShouldGetPostByID_4(){
        Response response = given()
                .basePath("todos")
                .when()
                .get()
                .then()
                .extract()
                .response();

        // Printing the report in pretty format
        response.prettyPrint();
        // Assert to verify the request is successful by checking the status code
        Assert.assertEquals(response.getStatusCode(),200);
        // Assert to verify id 4 is completed
        Assert.assertEquals(response.jsonPath().getString("[3].completed"),"true");
    }

    @Test
    public void verifyLat_LngIsCorrect(){
        Response response=given()
                .basePath("users")
                .when()
                .get("2")
                .then()
                .extract()
                .response();

        // Printing the report in pretty format
        response.prettyPrint();
        // Assert to verify the request is successful by checking the status code
        Assert.assertEquals(response.getStatusCode(),200);
        // Assert to verify lat for id 2 is matching to the expected requirement
        Assert.assertEquals(response.jsonPath().getString("address.geo.lat"),"-43.9509");
        // Assert to verify lng for id 2 is matching to the expected requirement
        Assert.assertEquals(response.jsonPath().getString("address.geo.lng"),"-34.4618");
    }

}
