package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class TestAssuredLoggingExample{


    @Test
    public void logggingTest(){

        //log --> provides logging options for request

        //
        given().log().all().when().get("https://uinames.com/api/");

        //LOG Response
        System.out.println("*****************************\n");
        Response response=RestAssured.get("https://uinames.com/api/");
        response.then().log().all();

    }
}
