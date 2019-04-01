package test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.GenericTest;
import utils.ResourceProvider;


public class GetTest extends GenericTest {

    ResourceProvider resourceProvider = new ResourceProvider();

    @BeforeTest
    public void beforeTest(){
        RestAssured.baseURI = config.getProperty("HOST");
    }

    @Test
    public void getCurrentWeatherDataTest(){

        Response response = RestAssured.given().
                param("lat",config.getProperty("latitude")).
                param("lon",config.getProperty("longitude")).
                param("key",config.getProperty("KEY")).
                when().
                get(resourceProvider.provideCurrentWeatherDataResource()).
                then().assertThat().statusCode(200).and().
                extract().response();

        JsonPath result = convertRawToJson(response);
        log.info("The place you are looking for is: " + result.get("data[0].state_code"));
    }

    @Test
    public void getHourlyForecastTest(){

        Response response = RestAssured.given().
                param("postal_code",config.getProperty("postalcode")).
                param("key",config.getProperty("KEY")).
                when().
                get(resourceProvider.provideHourlyForecastResource()).
                then().assertThat().statusCode(200).and().
                extract().response();

        JsonPath result = convertRawToJson(response);

        int size = result.get("data.size()");
        for (int index = 0; index < size; index++) {
            log.info("Location no.: " +index);
            log.info("Location no.: " + result.get("data["+index+"].timestamp_utc"));
            log.info("Hourly Forecast is: " + result.get("data["+index+"].weather.description"));
        }
    }
}
