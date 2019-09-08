package com.app.test2;
import static io.restassured.RestAssured.*;

import com.app.utilities.ConfigurationReader;
import com.google.common.collect.Maps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonExample {


    @Test
    public void testWithJsonToHashMap(){


        Response response=given().accept(ContentType.JSON).when().get(ConfigurationReader.get("hrapp.url")+"/employees/120");


        HashMap<String, String> map=response.as(HashMap.class);
        System.out.println(map.keySet());
        System.out.println(map.values());

        Assert.assertEquals(map.get("employee_id"),120);
        Assert.assertEquals(map.get("job_id"),"ST_MAN");


    }
    @Test
    public void convertJsonToListOfMaps(){
        Response response=given().accept(ContentType.JSON).when().get(ConfigurationReader.get("hrapp.url")+"/departments");


        //convert response that contains dep info into List of maps

        //List<Map<String,Object>> listOfMaps=response.as(ArrayList.class);
        List<Map> listOfMaps=response.jsonPath().getList("items",Map.class);

        System.out.println(listOfMaps.get(0));

//{department_id=10, manager_id=200, department_name=Administration,
// links=[{rel=self, href=http://52.90.67.68:1000/ords/hr/departments/10}], location_id=1700}

//assert that frist department name is Administration

        Assert.assertEquals(listOfMaps.get(0).get("department_name"),"Administration");
    }



}
