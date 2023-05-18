package weather;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class WeatherApi {

    @BeforeClass
    public void setUp(){}

    @Test
    public void it_gets_weather(){
        ValidatableResponse validatableResponse = given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .basePath("weather")
                .queryParam("lat", "44.34")
                .queryParam("lon", "10.99")
                .queryParam("appid","eb7001b716712299346ba820f2318577")
                .when()
                .get()
                .then();

        validatableResponse.assertThat()
                .statusCode(200)
                .body("main",hasKey("feels_like"))
                .body("name", Is.is("Zocca"));



    }

    @Test
    public void alternate_way_to_get() {
        RequestSpecification requestSpecification = given();
        requestSpecification.baseUri("https://api.openweathermap.org/data/2.5");
        requestSpecification.basePath("weather");
        requestSpecification.queryParam("lat", "44.34");
        requestSpecification.queryParam("lon", "10.99");
        requestSpecification.queryParam("appid", "eb7001b716712299346ba820f2318577");

        ValidatableResponse vr = requestSpecification.when()
                .get()
                .then();

        vr.assertThat().statusCode(200);
        vr.assertThat().body("main", hasKey("feels_like"));
        vr.assertThat().body("name", Is.is("Zocca"));
    }

}
