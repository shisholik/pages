package com.shisholik.pages.api;

import com.shisholik.pages.user.CustomStructure;
import com.shisholik.pages.user.UserEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.Arrays;

@Path("/")
@Produces("application/json")
@WebService
public class AuthService {

    @POST
    @Path("/register")
    public UserEntity registration(@QueryParam("login") String login, @QueryParam("password") String password, @QueryParam("name") String name) {
        System.out.println("Login:" + login);
        System.out.println("password:" + password);
        System.out.println("name:" + name);
        Arrays.asList(login, password, name).stream().forEach(System.out::println);
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setName(name);
        userEntity.setPassword_md5(password);
        return userEntity;
    }

    @POST
    @Path("/register1")
    public String registration1(UserEntity userEntity) {
        System.out.println(userEntity);

        return "Ok";
    }

    @Path("/123")
    @GET
    public CustomStructure getUser(@QueryParam("login") String login) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("awdawd");
        userEntity.setName("name");
        userEntity.setPassword_md5("password");

        return new CustomStructure(userEntity,"wdwadawd");
    }
}
