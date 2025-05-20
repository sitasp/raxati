package org.sage.controller;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;



@Path("/posts")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @POST
    @Path("/create")
    public String createPost() {
        return "Post created successfully!";
    }

    @GET
    @Path("/{id}")
    public String getPostById(@PathParam("id") String id) {
        return "Post details for ID: " + id;
    }
}
