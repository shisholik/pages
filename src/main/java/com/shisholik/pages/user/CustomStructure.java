package com.shisholik.pages.user;

import org.codehaus.jackson.map.annotate.JsonFilter;

@JsonFilter("myFilter")
public class CustomStructure {
    private UserEntity user;
    private String test;

    public CustomStructure() {
    }

    public CustomStructure(UserEntity user, String test) {
        this.user = user;
        this.test = test;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
