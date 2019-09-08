package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class PathParamExample {


    @BeforeClass
    public static void setUpClass(){
        RestAssured.baseURI="https://api.got.show/api/";
    }

    @Test
    public void getCityNameTest(){

//statment where we prepare the request
        given().
                //adding param name with value
                pathParam(
                        "name","Braavos").
                //send the request
                when().

                get("map/cities/{name}").prettyPrint();
    }

    @Test
    public void getCharacterByName(){
        given().pathParam("name","Pyp").when().get("map/characters/{name}").prettyPrint();
    }

    @Test
    public void getCharacterById(){
        given().pathParam("id","5cc0743504e71a0010b84f3d").when().get("map/characters/byId/{id}").prettyPrint();
    }



}
