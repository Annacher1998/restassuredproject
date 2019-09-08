package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class SimpleRestDemo {

@Test
    public void test1(){
        //get--> method fro getting request , accepts the resource url as parameter
    //response --> represents the response that came back from server based on out request
       Response response = RestAssured.get("https://uinames.com/api/");

//prints the response
       response.prettyPrint();
       //asString()--> converting the response to String
       String resString=response.asString();
    Assert.assertTrue(resString.contains("name"));
//statusCode()--> returns the response code
    int code=response.statusCode();
    Assert.assertEquals(200,code);




    }

    @Test
    public void headersTest(){
    Response response=RestAssured.get("https://uinames.com/api/");
//returns the headers of the response
        System.out.println(response.headers());

        String contentType=response.header("Content-Type");
        System.out.println(contentType);
        Assert.assertEquals("application/json; charset=utf-8",contentType);
    }

    @Test
    public void statusLineTest(){
        Response response=RestAssured.get("https://uinames.com/api/");

//statusLine()--> returns all info in the status line of the response
        String statusLIne=response.statusLine();
        System.out.println(statusLIne);

        Assert.assertTrue(statusLIne.contains("200"));



    }
}
