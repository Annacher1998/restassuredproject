package com.app.test2;

import com.app.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PostAndGet {

    /*
    Given Content type and Accept type is Json . When I post a new Employee with “1234”id
Then status code is 201
And Request json should contain Region info
When I send a get request with “1234" id
Then status code is 201
And employee Json response data should match the posted json data

     */

    @Test
    public void postEmployeeGet(){
        String url= ConfigurationReader.get("hrapp.url")+"/employees/";
        Map reqEmployee=new HashMap();
        int randomId=new Random().nextInt(99999);
        reqEmployee.put("employee_id",randomId);
        reqEmployee.put("first_name","Alina");
        reqEmployee.put("last_name","Chernysova");
        reqEmployee.put("phone_number","515.123.4567");
        reqEmployee.put("email","AL"+randomId);
        reqEmployee.put("hire_date", "2003-06-17T04:00:00Z");
        reqEmployee.put("job_id", "AD_PRES");
        reqEmployee.put("salary", 21000);
        reqEmployee.put("commission_pct", null);
        reqEmployee.put("manager_id", null);
        reqEmployee.put("department_id", 90);

        Response response=given().contentType(ContentType.JSON).
                and().accept(ContentType.JSON).
                and().body(reqEmployee).
                when().post(url);

        Assert.assertEquals(response.statusCode(),201);

        Map  postReqEmployee=response.body().as(Map.class);
        //And Request json should contain Region info
        for (Object key: reqEmployee.keySet()) {
            System.out.println(postReqEmployee.get(key)+"<>"+reqEmployee.get(key));
            Assert.assertEquals(postReqEmployee.get(key),reqEmployee.get(key));
        }



        response=given().accept(ContentType.JSON).when().get(url+randomId);
        postReqEmployee=response.body().as(Map.class);
        for (Object key: reqEmployee.keySet()) {
            System.out.println(postReqEmployee.get(key)+"<>"+reqEmployee.get(key));
            Assert.assertEquals(postReqEmployee.get(key),reqEmployee.get(key));
        }

        //When I send a get request with “randomId" id
        Assert.assertEquals(response.statusCode(),200);
        Map getRespMap=response.body().as(Map.class);
        for (Object key: reqEmployee.keySet()) {
            System.out.println(key+" : "+reqEmployee.get(key)+"<>"+getRespMap.get(key));
            Assert.assertEquals(getRespMap.get(key),reqEmployee.get(key));
        }

    }
}
