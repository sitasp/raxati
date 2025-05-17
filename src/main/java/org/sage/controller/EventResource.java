package org.sage.controller;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sage.object.domain.Event;
import org.sage.service.EventService;
import org.sage.util.ResourceUtils;

import java.util.List;
import java.util.Optional;

@Path("/event")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    @Inject
    EventService eventService;

    @GET
    @Path("/slug/{slug}")
    public Response getEventBySlug(@PathParam("slug") String slug) {
        Optional<Event> eventOpt = eventService.fetchEventBySlug(slug);
        return ResourceUtils.requireOrBadRequest(eventOpt, "Event not found");
    }

    @GET
    @Path("/{id}")
    public Response getEventById(@PathParam("id") String id) {
        Optional<Event> eventOpt = eventService.fetchEventById(id);
        return ResourceUtils.requireOrBadRequest(eventOpt, "Topic not found");
    }

    @POST
    @Path("/create")
    public Response createEvent(Event event) {
        Optional<Event> eventOpt = eventService.saveEvent(event);
        return ResourceUtils.requireOrBadRequest(eventOpt, "Event could not be created");
    }

    @GET
    @Path("/topic/{topicId}")
    public Response getEventByTopicId(@PathParam("topicId") String topicId) {
        List<Event> events = eventService.fetchEventsByTopicId(topicId);
        return Response.ok(events).build();
    }
}
