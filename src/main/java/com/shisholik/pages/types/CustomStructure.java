package com.shisholik.pages.types;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shisholik.pages.api.UserResource;
import com.shisholik.pages.types.SomeEnum;
import com.wordnik.swagger.annotations.ApiModel;
import org.apache.cxf.jaxrs.model.wadl.XMLName;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonFilter("myFilter")
@ApiModel
@XmlRootElement(name = "CustomStructure")
@XMLName(value = "{CustomStructure}awdawdaw", prefix = "com.shisholik.pages.user")
public class CustomStructure {
    private UserResource user;
    private String test;
    private SomeEnum someEnum;

    public CustomStructure() {
    }

    public CustomStructure(UserResource user, String test) {
        this.user = user;
        this.test = test;
    }

    @XmlElement(name = "user")
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

    public SomeEnum getSomeEnum() {
        return someEnum;
    }

    public void setSomeEnum(SomeEnum someEnum) {
        this.someEnum = someEnum;
    }

    @JsonIgnore
    @Path("/userInformation")
    @GET
    public UserResource getUserObj() {
        return user;
    }
}
