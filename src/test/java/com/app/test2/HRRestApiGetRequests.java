package com.app.test2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;


public class HRRestApiGetRequests {


    /*
    todo when i send a get request to rest url http://52.90.67.68:1000/ords/hr/employees
    then response status should be 200
     */
    @Test
    public void simpleGet(){

        when().get("http://52.90.67.68:1000/ords/hr/employees").then().statusCode(200);

    }

    /*todo when i send a get request to rest url http://52.90.67.68:1000/ords/hr/countries
    then response status should be 200
    I should see json response

     */
    @Test
    public void printResponse(){

        when().get("http://52.90.67.68:1000/ords/hr/countries").body().prettyPrint();
    }

    /*
    When I send a GET request to REST Api Url
http://52.90.67.68:1000/ords/hr/countries/AU
Then response status should be 200
And Country name should be "Australia" for country id "AU"
in Json response body
     */
    @Test
    public void getWithHeaders(){

        with().accept(ContentType.JSON).when().get("http://52.90.67.68:1000/ords/hr/countries/AU").then().statusCode(200);
    }
    /*
    When I send a GET request to REST Api Url
http://52.90.67.68:1000/ords/hr/employees/1234
Then status code is 404
     */

    @Test
    public void employees(){
        Response response=when().get("http://52.90.67.68:1000/ords/hr/employees/1234");

        Assert.assertEquals(response.statusCode(),404);
        Assert.assertTrue(response.asString().contains("Not Found"));
        response.prettyPrint();

    }
    /*
    When I send a GET request to REST Api Url
http://52.90.67.68:1000/ords/hr/employees/100
and accept type is json

Then status code is 200
and response body is json
     */
    @Test
    public void verifyContentTypeWithAssertThat(){

     given().accept(ContentType.JSON).
             when().
             get("http://52.90.67.68:1000/ords/hr/employees/100").
             then().
             assertThat().statusCode(200).and().contentType(ContentType.JSON);

    }

    /*
    When I send a GET request to REST Api Url
http://52.90.67.68:1000/ords/hr/employees/100
and accept type is json
first name should be Steven
Then status code is 200
and response body is json
and employee_id 100
     */

    @Test
    public void verifyFirstName() throws URISyntaxException {
        URI uri=new URI("http://52.90.67.68:1000/ords/hr/employees/100");
        given().accept(ContentType.JSON).when().get(uri).then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                and().assertThat().body("first_name", Matchers.equalTo("Steven")).
                and().assertThat().body("employee_id",Matchers.equalTo(100));
    }
}
