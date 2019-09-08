package com.app.pojos;

public class Instructor {
    private int id;
    private int batch;
    private String firstName;
    private  String lastName;
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", batch=" + batch +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
