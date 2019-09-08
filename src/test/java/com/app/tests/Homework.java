package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Homework {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI="https://api.github.com";

    }

    @Test
    public void userTest(){
        //given()-->prepare our request

        given().pathParam("username","Annacher1998").when().get("/users/{username}").prettyPrint();


    }
}
