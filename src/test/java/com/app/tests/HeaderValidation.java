package com.app.tests;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class HeaderValidation {

    @BeforeClass
    public static void setUp9(){
        RestAssured.baseURI="https://uinames.com/api/";

    }

    @Test
    public void headerTest(){
        given().when().get().then().log().headers().header("Content-Type","application/json; charset=utf-8");
        //getting the value of the header given in first param
        //and verifies against the value in the second param



    }



}
