package com.app.tests;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
public class VerifyingTestBody {

    @BeforeClass
    public static void setUp9(){
        RestAssured.baseURI="https://uinames.com/api/";

    }

    @Test
    public void testBody(){

        //body("name")--> first param is locator, gets the value from the response, it will be actual value
        //second param will be expected value

        given().when().get().then().assertThat().body("name",notNullValue());
        //in the example above we are verifying that key name has value

    }

    @Test
    public void verifyRegion(){
        String region="Mexico";
       given().queryParam("region",region).when().get().then().log().body().assertThat().body("region",is(region));
    }


    @Test
    public void verifyAmountOfResults() {
        given().queryParam("amount",2).when().get().then().assertThat().body("$",hasSize(2));
    }
}
