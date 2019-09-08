package com.app.tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class StudentResourceEndtoEnd {

    @Test
    public void postStudent() throws InterruptedException {
        //create test data

        Faker faker=new Faker();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
String email=firstname.toLowerCase()+lastname.toLowerCase()+"@"+faker.internet().domainName();
String password=firstname.toLowerCase()+lastname.toLowerCase();
String role="student-team-member";
String campusLocation="VA";
String batchNumber="8";
String teamName="CodeHunters";

String expectedMessage="user "+firstname+ " "+lastname+" has been added to database.";
String token=BookITRestUtility.getTeacherToken();
given().log().all().header("Authorization",token).
        queryParam("first-name",firstname).
        queryParam("last-name",lastname).
        queryParam("password",password).
        queryParam("email",email).
        queryParam("role",role).
        queryParam("campus-location",campusLocation).
        queryParam("batch-number",batchNumber).
        queryParam("team-name",teamName).when().post("api/students/student").
        then().log().all().assertThat().statusCode(201).body(is(expectedMessage));
        //baseURL         resource              ?--> query param
      //{{qa1_url}}       /api/students/student ?first-name&last-name&email&password&role&campus-location&batch-number&team-name

//VERIFY USING UI
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://cybertek-reservation-qa.herokuapp.com/sign-in");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password+ Keys.ENTER);
        Thread.sleep(3000);
        assertThat(driver.getCurrentUrl(),endsWith("map"));


    }
}
