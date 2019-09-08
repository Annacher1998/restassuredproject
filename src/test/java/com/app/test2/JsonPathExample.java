package com.app.test2;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.app.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonPathExample {

    /*
    Given accept type is json
    when i send a get request to rest url:
    http://52.90.67.68:1000/ords/hr/regions
    then status code is 200
    and response content should be json
    and 4 regions should be returned
    and
     */


    @Test
    public void getRegions(){
        given().accept(ContentType.JSON).when().get(ConfigurationReader.get("hrapp.url")+"/regions").then().assertThat().
                statusCode(200).
                and().contentType(ContentType.JSON).assertThat().
                and().assertThat().body("items.region_id",hasSize(4)).
                and().assertThat().body("items.region_name",hasItem("Americas"))
        .and().assertThat().body("items.region_name",hasItems("Americas","Asia"));
    }
/*
Given accept type is json
    when i send a get request to rest url:
    http://52.90.67.68:1000/ords/hr/employees
    then status code is 200
    and response content should be json
    and 100 param data should be returned
    and
 */


@Test
    public void employeesTable(){

    given().accept(ContentType.JSON).and().params("limit",100).when().get(ConfigurationReader.get("hrapp.url")+"/employees").then().assertThat()
            .statusCode(200).and().contentType(ContentType.JSON).assertThat().body("items.employee_id",hasSize(100));
}
/*
Given and params is limit 100
and path param is 110
when i send a get request to rest url:
    http://52.90.67.68:1000/ords/hr/employees
    then status code is 200
    and response content should be json
    and 100 param data should be returned
    and first name John
 */

@Test
    public void testWithPathParam(){

given().contentType(ContentType.JSON).and().params("limit",100).and().pathParam("employee_id",110).when().
        get(ConfigurationReader.get("hrapp.url")+"/employees/{employee_id}").then().assertThat().statusCode(200).
        and().assertThat().contentType(ContentType.JSON).
        and().assertThat().body("employee_id",equalTo(110)).
        and().assertThat().body("first_name",equalTo("John"));;




}

@Test
    public void testWithJsonPath(){

    Map<String,Integer> rParamMap=new HashMap<String, Integer>();
    rParamMap.put("limit",100);
    Response response=given().accept(ContentType.JSON).
            and().params(rParamMap).
            and().pathParams("employee_id",177).
            when().get(ConfigurationReader.get("hrapp.url")+"/employees/{employee_id}");

    JsonPath jsonPath=response.jsonPath();
    System.out.println(jsonPath.getString("employee_id"));
    System.out.println(jsonPath.getString("last_name"));
    System.out.println(jsonPath.getString("links[0].href"));

    List<String> listOfHref=jsonPath.getList("links.href");
    System.out.println(listOfHref);


}
/*

    Given and params is limit 100

    when i send a get request to rest url:
    http://52.90.67.68:1000/ords/hr/employees
    then status code is 200
    and response content should be json
    all employee data should be return


 */
@Test
    public void testJsonPathWithList(){
    Map<String,Integer> rParamMap=new HashMap<String, Integer>();
    rParamMap.put("limit",100);
    Response response=given().accept(ContentType.JSON).
            and().params(rParamMap).when().get(ConfigurationReader.get("hrapp.url")+"/employees");

    Assert.assertEquals(response.statusCode(),200);
    JsonPath jsonPath=response.jsonPath();
   // JsonPath jsonPath=new JsonPath(new File(FilePath.json))
   // JsonPath jsonPath=new JsonPath(response.asString());
    //get all empl id an arrayList

    List<Integer> emplId=jsonPath.getList("items.employee_id");
    System.out.println(emplId);
    //assert there are 100 emp ids
   Assert.assertEquals(emplId.size(),100);

   //get all first names from postman
    List<String> firstNameList=jsonPath.getList("items.first_name");
    Assert.assertTrue(firstNameList.contains("John"));
    //get all emails and assign into arrayList
    List<String> email=jsonPath.getList("items.email");
    System.out.println(email);

    //get all emp ids greater than 150
  List<String> empList=jsonPath.getList("items.findAll{it.employee_id>150}employee_id");
    System.out.println(empList);

    //get all empl last names , whose salary is more than 7000
    List<String>  listOfLastNames=jsonPath.getList("items.findAll{it.salary>7000}last_name");
    System.out.println(listOfLastNames);


    JsonPath jsonPath1=new JsonPath(new File(("/Users/annachernyshova/Desktop/employees.json")));
    List<String> email1=jsonPath1.getList("items.email");
    System.out.println(email1);
}

}
