package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseURIDemo

{
@BeforeClass
public static void setUpClass(){
    RestAssured.baseURI="https://api.got.show/api/";
}
    @Test
    public void culturesTest(){

        //baseURI--> used to save the base url for all resources
        //when we actually make request , we only provide the path specific resource


        Response response=RestAssured.get("/map/characters");
        response.prettyPrint();

        Assert.assertTrue(response.asString().contains("Ironborn"));
    }
    @Test
public void citiesTest(){

    

    Response response=RestAssured.get("map/cities/");
    response.prettyPrint();

    Assert.assertTrue(response.asString().contains("White Harbor"));
}

}
