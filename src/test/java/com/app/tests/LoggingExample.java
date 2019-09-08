package com.app.tests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.apache.log4j.Logger;
import org.junit.Test;



public class LoggingExample {

    Logger log=Logger.getLogger(LoggingExample.class);
    @Before
    public  void before(){

        log.info(" this is the before method");

    }

    @Test
    public void test(){

        log.trace("this is test method: TRACE");
        log.info("this is test method: INFO");
        log.warn("this is test method: WARN");

        RestAssured.get("https://uinames.com/api/");
    }
    @After
    public  void after(){

        log.info("this is the after method");
    }


}
