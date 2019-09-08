package com.app.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.app.utilities.ConfigurationReader;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;

public class Api_PostAnEmployeeStepDef {

    RequestSpecification request;
    int emplId;
    String url= ConfigurationReader.get("hrapp.url")+"/employees/";
    Response response;
    Map reqEmployee;

    @Given("Content type and Accept type is Json")
    public void content_type_and_Accept_type_is_Json() {
       request= given().contentType(ContentType.JSON).
                and().accept(ContentType.JSON);
    }

    @When("I post a new Employee with {string} id")
    public void i_post_a_new_Employee_with_id(String id) {
      if(id.equals("random")){
          emplId=new Random().nextInt(99999);
      }else{
          emplId=Integer.parseInt(id);
      }

        reqEmployee=new HashMap();
        reqEmployee.put("employee_id",emplId);
        reqEmployee.put("first_name","Alina");
        reqEmployee.put("last_name","Chernysova");
        reqEmployee.put("phone_number","515.123.4567");
        reqEmployee.put("email","AL"+emplId);
        reqEmployee.put("hire_date", "2003-06-17T04:00:00Z");
        reqEmployee.put("job_id", "AD_PRES");
        reqEmployee.put("salary", 21000);
        reqEmployee.put("commission_pct", null);
        reqEmployee.put("manager_id", null);
        reqEmployee.put("department_id", 90);
        response=request.body(reqEmployee).
                when().post(url);
    }

    @Then("status code is {int}")
    public void status_code_is(int statuscode) {
        Assert.assertEquals(response.statusCode(),statuscode);
    }

    @Then("Request json should contain Employee info")
    public void request_json_should_contain_Employee_info() {
        Map  postReqEmployee=response.body().as(Map.class);
        //And Request json should contain Region info
        for (Object key: reqEmployee.keySet()) {
            System.out.println(postReqEmployee.get(key)+"<>"+reqEmployee.get(key));
            Assert.assertEquals(postReqEmployee.get(key),reqEmployee.get(key));
        }
    }

    @When("I send a get request with {string} id")
    public void i_send_a_get_request_with_id(String id) {
        if(!id.equals("random")){
            emplId=Integer.parseInt(id);
        }
        response=given().accept(ContentType.JSON).when().get(url+emplId);
    }

    @Then("employee Json response data should match the posted json data")
    public void employee_Json_response_data_should_match_the_posted_json_data() {
        Map getRespMap=response.body().as(Map.class);
        for (Object key: reqEmployee.keySet()) {
            System.out.println(key+" : "+reqEmployee.get(key)+"<>"+getRespMap.get(key));
            Assert.assertEquals(getRespMap.get(key),reqEmployee.get(key));
        }
    }
}
