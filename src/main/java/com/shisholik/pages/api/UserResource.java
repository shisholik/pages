package com.shisholik.pages.api;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.shisholik.pages.annotations.IgnoreNested;
import com.shisholik.pages.types.ArrayWrapper;
import com.shisholik.pages.user.UserEntity;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "inventoryBean", namespace = "bean")
@JsonFilter("myFilter")
public class UserResource extends BaseResource<UserEntity> {

    public UserResource(UserEntity baseEntity) {
        super(baseEntity);
    }

    @JsonView(Views.Public.class)
    public String getName() {
        return getType().getName();
    }

    @XmlElement
    public String getLogin() {
        return getType().getLogin();
    }

    @JsonView(Views.ExtendedPublic.class)
    public String getPassword_md5() {
        return getType().getPassword_md5();
    }

    @JsonView(Views.Internal.class)
    public String getInternalValue() {
        return "1212412412";
    }


    @GET
    @Path("/friends/")
    public ArrayWrapper<UserResource> getFriends() {
        List<UserResource> userResources = new ArrayList<>();
        userResources.add(getUserResource("Log1", "Name1"));
        userResources.add(getUserResource("Log2", "Name2"));
        userResources.add(getUserResource("Log3", "Name3"));
        userResources.add(getUserResource("Log4", "Name4"));

        return new ArrayWrapper<UserResource>(userResources);
    }

    private UserResource getUserResource() {
        return getUserResource("awdawd", "acawcaw");
    }

    private UserResource getUserResource(String login, String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setName(name);
        userEntity.setPassword_md5("password");
        return new UserResource(userEntity);
    }

    @POST
    public String doSomething(@QueryParam("test") String test) {
        return test;
    }
}
