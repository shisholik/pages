package com.shisholik.pages.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.shisholik.pages.types.ArrayWrapper;
import com.shisholik.pages.types.CustomStructure;
import com.shisholik.pages.user.UserEntity;
import com.wordnik.swagger.annotations.Api;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;
import org.apache.cxf.jaxrs.model.wadl.ElementClass;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("/t")
@Produces("application/json")
@Api(value = "/t", description = "auth api")
public class AuthService {

    @JsonView(Views.ExtendedPublic.class)
    @Path("/user")
    @Descriptions({@Description(value = "Get user structure", target = DocTarget.METHOD),
            @Description(value = "It's a CustomStructure", target = DocTarget.RESPONSE),
            @Description(value = "Get user structure", target = DocTarget.RETURN),
            @Description(value = "Some users data. Login for example", target = DocTarget.REQUEST)})

    public UserResource getUser(@Description("User login") @QueryParam("login") String login) {
        return getUserResource();
    }

    @Path("/users")
    @GET
    public ArrayWrapper<UserResource> getTemplateUser(@QueryParam("limit") int limit, @QueryParam("offset") int offset) {
        List<UserResource> userResources = new ArrayList<>();
        userResources.add(getUserResource("Log1", "Name1"));
        userResources.add(getUserResource("Log2", "Name2"));
        userResources.add(getUserResource("Log3", "Name3"));
        userResources.add(getUserResource("Log4", "Name4"));

        return new ArrayWrapper<UserResource>(userResources, limit, offset);
    }

    @Path("/users")
    @POST
    public Response createUser(@QueryParam("limit") int limit, @QueryParam("offset") int offset) throws URISyntaxException {
        return Response.created(new URI("/users/123")).build();
    }

    @JsonView(Views.ExtendedPublic.class)
    @Path("/user1/{template:[0-9]*}")
    @GET
    @Descriptions({@Description(value = "Get user structure with strange enum param", target = DocTarget.METHOD),
            @Description(value = "It's a CustomStructure", target = DocTarget.RESPONSE),
            @Description(value = "Get user structure", target = DocTarget.RETURN),})

    public CustomStructure getTemplateUser(@PathParam("template") String template) {
        return getCustomStructure();
    }

    @Path("/user2")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @ElementClass(request = UserResource.class, response = CustomStructure.class)
    @Consumes({MediaType.APPLICATION_XML})

    public CustomStructure getUser2(UserResource resource) {
        return getCustomStructure();
    }

    private CustomStructure getCustomStructure() {

        return new CustomStructure(getUserResource(), "wdwadawd");
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
}
