package org.sage.controller;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sage.object.domain.Topic;
import org.sage.service.TopicService;
import org.sage.util.ResourceUtils;

import java.util.Optional;

@Path("/topic")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicResource {

     @Inject
     TopicService topicService;

     @GET
     @Path("/slug/{slug}")
     public Response getTopicBySlug(@PathParam("slug") String slug) {
         Optional<Topic> topicOpt = topicService.fetchTopicBySlug(slug);
         return ResourceUtils.requireOrBadRequest(topicOpt, "Topic not found");
     }

    @GET
    @Path("/{id}")
    public Response getTopicById(@PathParam("id") String id) {
        Optional<Topic> topicOpt = topicService.fetchTopicById(id);
        return ResourceUtils.requireOrBadRequest(topicOpt, "Topic not found");
    }

    @POST
    @Path("/create")
    public Response createTopic(@Valid Topic topic) {
        Optional<Topic> topicOpt = topicService.saveTopic(topic);
        return ResourceUtils.requireOrBadRequest(topicOpt, "Topic could not be created");
    }
}
