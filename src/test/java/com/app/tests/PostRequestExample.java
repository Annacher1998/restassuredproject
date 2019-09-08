package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;



import static io.restassured.RestAssured.given;
public class PostRequestExample {



String accessToken;
        @BeforeClass
        public static void setUp() {
            RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
        }

    @Test
    public void createTeam(){


        String token=BookITRestUtility.getTeacherToken();
        RestAssured.given().
                header("Autorization",token).
                queryParam("campus-location","VA").queryParam("batch-number","8").
                queryParam("team-name","Livertak").post("/api/teams/team").
                then().log().all().statusCode(201);


    }



}
