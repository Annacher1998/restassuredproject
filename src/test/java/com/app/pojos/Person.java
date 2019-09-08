package com.app.pojos;

public class Person {

    private String name;
    private String surname;
    private String gender;
    private String region;




    public String getGender() {
        return gender;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
