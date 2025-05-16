package org.sage.controller;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sage.object.domain.Topic;
import org.sage.service.TopicService;
import org.sage.utils.ResourceUtils;

import java.util.Optional;

@Path("/topic")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicResource {

    @Inject
    TopicService topicService;

     @GET
     public Response getTopic(@QueryParam("slug") String slug) {
         Optional<Topic> topicOpt = topicService.fetchTopicBySlug(slug);
         return ResourceUtils.requireOrBadRequest(topicOpt, "Topic not found");
     }
}
