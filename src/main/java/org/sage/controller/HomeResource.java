package org.sage.controller;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sage.object.response.LandingPageResponse;
import org.sage.service.HomeService;

import java.util.List;

@Path("/home")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeResource {

    @Inject
    HomeService homeService;

    @GET
    public Response homepage() {
        LandingPageResponse landingPageResponse = homeService.getTopicsForHomepage();
        return Response
                .ok(landingPageResponse)
                .build();
    }
}
