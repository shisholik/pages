package com.shisholik.pages.user;

import com.shisholik.pages.BaseEntity;

import java.util.Date;

public class UserEntity extends BaseEntity {

    private String name;

    private String login;

    private String password_md5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword_md5() {
        return password_md5;
    }

    public void setPassword_md5(String password_md5) {
        this.password_md5 = password_md5;
    }

    protected void onCreate() {
        setCreatedAt(new Date());
    }
}
