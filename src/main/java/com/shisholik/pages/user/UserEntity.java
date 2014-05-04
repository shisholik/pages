package com.shisholik.pages.user;

import com.shisholik.pages.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class UserEntity extends BaseEntity {


    private String name;


    private String login;

    private String password_md5;

    @Column(length = 60, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 30, nullable = false, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(length = 32, nullable = false)
    public String getPassword_md5() {
        return password_md5;
    }

    public void setPassword_md5(String password_md5) {
        this.password_md5 = password_md5;
    }

    @PrePersist
    protected void onCreate() {
        setCreatedAt(new Date());
    }


}
