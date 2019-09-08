package com.app.tests;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersDemo {
//Hamcrest library provides matchers (assertions method)
    //Rest Assured uses hancrest assertions (matchers)
    //works with both junit nd testng

    @Test
    public void test1() {
        assertThat(1, equalTo(1));
        String str1 = "Kunkka";
        String str2 = "Kunkka";

        //verify if str1 is equal to the second
        assertThat(str1, is("Kunkka"));
        assertThat(str2, is("Kunkka"));
        //verify if first argument is not equal to the second
        assertThat(str1, is(not("Tidehunter")));

        //compare ignoring case
        assertThat(str1, equalToIgnoringCase("kunkka"));

        //compare ignoring space

        assertThat(str1, equalToIgnoringWhiteSpace("  Kunkka"));


        //compare numbers

        assertThat(10, greaterThan(9));
        assertThat(10, lessThan(12));
        //CMD+D REMOVES  LINES

        //verify not null
        assertThat(str1,notNullValue());


        List<String> list= Arrays.asList("one","two", "three");
        assertThat(list, Matchers.<String>hasSize(3));


        assertThat(list, containsInAnyOrder("two","one","three"));
        assertThat(list, hasItems("one","two"));

        List<Integer> numbers=Arrays.asList(11,12,13);
        assertThat(numbers, everyItem(greaterThan(9)));
        assertThat(numbers,everyItem(lessThan(16)));

    }

}