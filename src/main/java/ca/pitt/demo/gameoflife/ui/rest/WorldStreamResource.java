package ca.pitt.demo.gameoflife.ui.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import ca.pitt.demo.gameoflife.ui.model.World;


@Path("/grid")
public class WorldStreamResource {

    @Inject
    @Channel("world-stream") Publisher<World> worlds; 
    
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    @SseElementType("text/plain") 
    public Publisher<World> stream() { 
        return worlds;
    }
	
    
}
