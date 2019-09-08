package com.app.test2;
import static org.hamcrest.Matchers.*;

import com.app.tests.SimpleRestDemo;
import com.app.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
public class RegionsExample {

    /*
    Given Content type is Json
And Limit is 10
When I send request to Rest API url:
http://34.223.219.142:1212/ords/hr/regions
Then I should see following data:
1 Europe
2 Americas
3 Asia
4 Middle East and Africa

     */

    @Test
    public void testRegions(){


        Response response=given().contentType(ContentType.JSON).and().params("limit",10).when().
                get(ConfigurationReader.get("hrapp.url")+"/regions");
        Assert.assertEquals(response.statusCode(),200);
        JsonPath jsonPath=response.jsonPath();
        jsonPath.getInt("items[0].region_id");
        jsonPath.getString("items[0].region_name");

//deserialize json to List<Map>
        List<Map> listOfRegions=jsonPath.getList("items",Map.class);
        Map<Integer,String> expectedRegions=new HashMap<Integer, String>();
        expectedRegions.put(1,"Europe");
        expectedRegions.put(2,"Americas");
        expectedRegions.put(3,"Asia");
        expectedRegions.put(4,"Middle East and Africa");

        for(Integer regionId:expectedRegions.keySet()){
            System.out.println("Looking for region: "+regionId);
            for (Map map:listOfRegions) {
                if(map.get("region_id")==regionId){
                    Assert.assertEquals(map.get("region_name"),expectedRegions.get(regionId));
                }
            }
        }


    }

    @Test
    public void testRegionsV2(){
        Response response=given().contentType(ContentType.JSON).and().params("limit",10).when().
                get(ConfigurationReader.get("hrapp.url")+"/regions");
        Assert.assertEquals(response.statusCode(),200);
        JsonPath jsonPath=response.jsonPath();

        List<String> testingData= Arrays.asList("1 Europe","2 Americas","3 Asia","4 Middle East and Africa");

        List<String> regionsNames=new ArrayList<String>();

        for(Object item:jsonPath.getList("items")){
            regionsNames.add(((HashMap) item).get("region_id").toString()+" "+
                    ((HashMap) item).get("region_name").toString());
        }
        Assert.assertTrue(regionsNames.containsAll(testingData));
    }
}
