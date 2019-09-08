package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class AccessTokenExample {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI="https://cybertek-reservation-api-qa.herokuapp.com/";
    }

    @Test
    public void getTokentest(){
        Response response=given().log().all().param("email","teacherva5@gmail.com")
                .param("password","maxpayne").get("sign");

               response.then().log().all().assertThat().statusCode(200);
       // System.out.println("response= "+response.jsonPath().getBoolean("accessToken"));

        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDY0IiwiYXVkIjoidGVhY2hlciJ9.japWZkmQ3ExAJA6HxXEMSm92h5ZfYARx1Ci8sSiLgzM";

String accessToken=response.jsonPath().get("accessToken");
        System.out.println("response= "+accessToken);



        //we are passing our token as a part of the request header
        RestAssured.given().header("Authorization",token).
                get("api/campuses/my").then().log().all().assertThat().statusCode(200);


    }
}
