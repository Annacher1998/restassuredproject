package com.app.tests;

import io.restassured.RestAssured;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class QueryParamsExample {

    @BeforeClass
    public static void setUp(){

        RestAssured.baseURI="https://uinames.com/api";

    }

    @Test
    public void getAmount(){
        given().queryParam("amount","2").queryParam("gender","female").when().get().prettyPrint();
    }

    @Test
    public void testRegion(){
        given().queryParam("region","Colombia").when().get().prettyPrint();
    }

    @Test
    public void amountAndRegionTest(){
        given().queryParam("amount","2").queryParam("region","Colombia").when().get().prettyPrint();
    }

  
}
