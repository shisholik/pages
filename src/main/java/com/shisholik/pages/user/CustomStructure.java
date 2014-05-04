package com.shisholik.pages.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.shisholik.pages.api.UserResource;


@JsonFilter("myFilter")
public class CustomStructure {
    private UserResource user;
    private String test;

    public CustomStructure() {
    }

    public CustomStructure(UserResource user, String test) {
        this.user = user;
        this.test = test;
    }

    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
