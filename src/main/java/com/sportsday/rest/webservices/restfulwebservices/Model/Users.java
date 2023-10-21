package com.sportsday.rest.webservices.restfulwebservices.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstname;
    private String lastname;
    private String username;
    private String email; // In a real application, this should be securely hashed and salted.

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<EventRegistration> registrations = new HashSet<>();

    public Users() {
    }

    public Users(Long id, String firstname, String lastname, String username, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<EventRegistration> registrations = new HashSet<>();

    // Constructors, getters, and setters
}
