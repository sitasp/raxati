package org.sage.controller;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sage.object.domain.Post;
import org.sage.object.domain.Topic;
import org.sage.service.PostService;
import org.sage.service.TopicService;
import org.sage.util.ResourceUtils;

import java.util.List;
import java.util.Optional;


@Path("/posts")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    PostService postService;

    @Inject
    TopicService topicService;;

    @POST
    @Path("/create")
    public Response createPost(Post post) {
        Optional<Post> postOptional = postService.createPost(post);
        return ResourceUtils.requireOrBadRequest(postOptional, "Post could not be created");
    }

    @GET
    @Path("/{id}")
    public Response getPostById(@PathParam("id") String id) {
        Optional<Post> postOpt = postService.fetchPostById(id);
        return ResourceUtils.requireOrBadRequest(postOpt, "Post not found");
    }

    @GET
    @Path("/user/{userId}")
    public Response getPostsByUserId(@PathParam("userId") String userId) {
        return Response.ok(postService.fetchPostsByUserId(userId)).build();
    }

    @GET
    @Path("/topic/{topicId}")
    public Response getPostsByTopicId(@PathParam("topicId") String topicId) {
        List<Post> topicPosts = topicService.fetchPostsUsingTopicId(topicId);
        return Response.ok(topicPosts).build();
    }
}
