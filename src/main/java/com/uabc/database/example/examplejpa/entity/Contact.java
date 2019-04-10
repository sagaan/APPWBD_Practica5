package com.uabc.database.example.examplejpa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "city")
    private String city;

    public Contact(){

    }

    public Contact(String firstName, String lastName, String telephone, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.city = city;
    }

}
