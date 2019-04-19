package com.messagingplatform.rest.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String passportNumber;

    public Student() {

    }

//    public Student(Long id, String name, String passportNumber) {
//        this.id = id;
//        this.name = name;
//        this.passportNumber = passportNumber;
//    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
}
