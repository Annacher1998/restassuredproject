package com.app.test2;

import com.app.pojos.Country;
import com.app.pojos.CountryResponse;
import com.app.pojos.Region;
import com.app.pojos.RegionResponse;
import com.app.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class PostExample {


    /*
    POST SCENARIO:
given content type is Json
And Accept type is Json
When I send POST request to
http://34.223.219.142:1212/ords/hr/regions
with request body :
{
"region_id" : 5,
"region_name" : "murodil's region"
}
Then status code should be 200
And response body should match request body

{"region_id" : 5,"region_name" : "murodil's region"}
     */

    @Test
    public void postRequest(){
        String url= ConfigurationReader.get("hrapp.url")+"/regions/";
       // String requestJson="{\"region_id\" : 6,\"region_name\" : \"anna's region\"}";
        Map requestMap=new HashMap();
        requestMap.put("region_id",4524);
        requestMap.put("region_name","annas region");
        Response response=given().contentType(ContentType.JSON).and().body(requestMap)
                .when().post(url);

        System.out.println(response.statusLine());
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),201);
        Map responseMap=response.body().as(Map.class);

       // Assert.assertEquals(requestMap,responseMap); did not work

        Assert.assertEquals(responseMap.get("region_id"),requestMap.get("region_id"));
        Assert.assertEquals(responseMap.get("region_name"),requestMap.get("region_name"));

    }

    @Test
    public void postUsingPOGO(){
        String url= ConfigurationReader.get("hrapp.url")+"/regions/";
        Region region=new Region();
        region.setRegion_id(new Random().nextInt(22222222));
        region.setRegion_name("anna cher region");

        Response response=given().log().all().accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body(region).
                        when().post(url);

//Assert.assertEquals(response.statusCode(),201);
RegionResponse region1=response.body().as(RegionResponse.class);

        Assert.assertEquals(region1.getRegion_id(),region.getRegion_id());
        Assert.assertEquals(region1.getRegion_name(),region.getRegion_name());

    }
    /*
    given content type is Json
And Accept type is Json
When I send POST request to
http://34.223.219.142:1212/ords/hr/countries/
with request body :
{
"country_id": "AR",
"country_name": "Argentina",
"region_id": 2
}
Then status code should be 200
And response body should match request body

     */

    @Test
    public void testCoutryWithJson(){
        String url= ConfigurationReader.get("hrapp.url")+"/countries/";

        Country country=new Country();
        country.setCountry_id("ll");
        country.setCountry_name("Ukraine");
        country.setRegion_id(444);

        Response response=given().log().all().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
                and().body(country).when().post(url);

        Assert.assertEquals(response.getStatusCode(),201);

        CountryResponse countryResponse=response.body().as(CountryResponse.class);


       // Assert.assertEquals(country.getRegion_id(),countryResponse.getRegion_id());
        Assert.assertEquals(country.getCountry_name(),countryResponse.getCountry_name());
        Assert.assertEquals(country.getCountry_id(),countryResponse.getCountry_id());

    }
}
