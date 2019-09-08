package com.app.tests;

import io.restassured.RestAssured;
import org.junit.Test;

public class RelaxedHTTPSValidation {

    @Test
    public void relaxYo(){

        //we''e going to trust connections with bad certificates
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.get("https://untrusted-root.badssl.com/").then().log().all().statusCode(200);


    }
}
