package com.github.brunomb.stackovergol.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

/**
 * Created by brunomb on 2/11/2017
 */
@IgnoreExtraProperties
public class User {

    @PropertyName("id")
    public Long id;

    @PropertyName("first_name")
    public String firstName;

    @PropertyName("last_name")
    public String lastName;

    @PropertyName("type")
    public String type;

    @PropertyName("username")
    public String username;

    public User() {}

    public User(Long id, String firstName, String lastName, String type, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
