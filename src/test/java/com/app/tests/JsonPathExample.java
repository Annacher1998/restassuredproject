package com.app.tests;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;


import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JsonPathExample {



    Logger log=Logger.getLogger(JsonPathExample.class);


    @Test
    public void test(){
log.info(BookITRestUtility.getTeacherToken());
        String token=BookITRestUtility.getTeacherToken();

        Response response=given().header("Authorization",token).get("/api/clusters");

        JsonPath jsonPath=response.jsonPath();

        Object id=jsonPath.get("id");
        System.out.println(id);

        //get the list of ids in the top level
        List<String> isString=jsonPath.get("id");
        System.out.println("id as String= "+isString);

        String idOfFirstObject=jsonPath.getString("id[0]");
        System.out.println(idOfFirstObject);

        Integer idOfTheFirstObjectNumber=jsonPath.getInt("id[0]");
        log.info(idOfTheFirstObjectNumber);

        List<String> isListSt=jsonPath.getList("id");
        log.info(isListSt);

        String harvard=jsonPath.getString("rooms[0].name[0]");
        log.info("Harvard= "+harvard);

        //get all rooms names
        List<String> allRooms=jsonPath.getList("rooms.name");
        log.info("Size of the rooms = "+allRooms.size());
        log.info(allRooms);


//get the first cluster name
        Map<String,Object> cluster1=jsonPath.getMap("[0]");
        log.info(cluster1);

        for(Object s:cluster1.keySet()){
            log.info(s);
            log.info(cluster1.get(s));
        }

        Map<String,String> cluster1String=jsonPath.getMap("[0]");
        log.info(cluster1String);

        for(String s:cluster1String.keySet()){
            log.info(s);
            log.info(cluster1String.get(s));
        }

        Map<String,String> harv=jsonPath.getMap("[0].rooms[0]");
        System.out.println(harv);


        for(String s: harv.keySet()){
log.info(s);
log.info(harv.get(s));
        }

        //CASTING

        Map<String,String> harv2=jsonPath.getMap("[0].rooms[0]",String.class,String.class);
        System.out.println(harv2);


        for(String s: harv2.keySet()){
            log.info(s);
            log.info(harv2.get(s));
        }

        //get id as double

        List<Double> id2=jsonPath.getList("id",Double.class);
        System.out.println(id2);
    }
}
