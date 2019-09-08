package com.app.tests;

import com.app.pojos.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;

public class PojoExample {

    @Test
    public void testGson(){

        //TODO serialization
        Person person=new Person();


        person.setName("Anna");
        person.setSurname("Jackson");
        person.setGender("female");
        person.setRegion("Earth");

        System.out.println("printing the person object "+person);
//gson is a class used to convert objects to json
        Gson gson=new Gson();

        //toJson()---> method which converts its parameter to json
        String jsonString=gson.toJson(person);

        System.out.println("printing the json: "+jsonString);

        //TODO deserialization

        String myJsonString="{\"name\":\"Anna\"," +
                "\"surname\":\"Jackson\"," +
                "\"gender\":\"female\"," +
                "\"region\":\"Earth\"}";
        //fromJson()--> converts the input to given type
        Person myPerson=gson.fromJson(myJsonString,Person.class);
        System.out.println("printing the new person object:" +myPerson);
    }

    @Test
    public void testJakson() throws IOException {

        //TODO serialization
        Person person=new Person();


        person.setName("Anna");
        person.setSurname("Jackson");
        person.setGender("female");
        person.setRegion("Earth");

        System.out.println("printing the person object "+person);
//ObjectMapper--> class which converts fron and to pojos
        ObjectMapper objectMapper=new ObjectMapper();

        //writeValueAsString()--> takes the input and converts to json
String json=objectMapper.writeValueAsString(person);
        System.out.println("printing the json string: "+json);

        //TODO deserialization
        String myJsonString="{\"name\":\"Anna\"," +
                "\"surname\":\"Jackson\"," +
                "\"gender\":\"female\"," +
                "\"region\":\"Earth\"}";

        //readValue()---> converts input to given type
        Person myPerson=objectMapper.readValue(myJsonString,Person.class);
        System.out.println("printing the new object= "+myPerson);

    }

    @Test
    public void jacksonVsGson() throws JsonProcessingException {
        Person person=new Person();
        person.setName("Anna");
        person.setSurname("Jackson");

        Gson gson=new Gson();
        String j1=gson.toJson(person);

        ObjectMapper objectMapper=new ObjectMapper();
        String j2=objectMapper.writeValueAsString(person);

        System.out.println(j1);
        System.out.println(j2);




    }
}
