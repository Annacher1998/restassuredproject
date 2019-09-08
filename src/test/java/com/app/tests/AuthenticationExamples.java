package com.app.tests;

import io.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
public class AuthenticationExamples {

    @Test
    public void usingApiKey(){
        RestAssured.given().log().all().queryParam("t","Kung fury").
                queryParam("apikey","a9faab96").when().get("https://www.omdbapi.com/").then().log().all().
                assertThat().statusCode(200);

        
    }

    @Test
    public void basicAutentification401Validation(){
        //https://the-internet.herokuapp.com/basic_auth
        //when we try to hit and end point without being autorized
        //we can 401 not autorized error

      RestAssured.get("https://the-internet.herokuapp.com/basic_auth").then().assertThat().statusCode(401);
    }

    @Test
    public void basicAutentification(){
        //auth--> provides different types of authentication
        //based--> authentication using usernmae and password
        RestAssured.given().auth().basic("admin","admin" ).when().get("https://the-internet.herokuapp.com/basic_auth").
                then().assertThat().statusCode(200);

        //
    }

    @Test
    public void xmlResponceExample(){
//https://parabank.parasoft.com/parabank/services/bank/customers/12212
//this is not an autorization example
        //this is method to show request which returns xml body
        RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/12212").then().log().all().assertThat().statusCode(200);

    }
    @Test
    public void basicPreemptiveAuthentication(){
        //auth--> provides different types of authentication
        //based--> authentication using usernmae and password
        RestAssured.given().auth().preemptive().basic("admin","admin" ).when().get("https://the-internet.herokuapp.com/basic_auth").
                then().assertThat().statusCode(200);
    }
}
