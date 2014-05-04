package com.shisholik.pages;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
@Produces("application/json")
public class CustomService {
    @GET
    public String getCustomer() {
        return "Hello";
    }
}
