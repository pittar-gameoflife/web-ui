package ca.pitt.demo.gameoflife.ui.streaming;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import ca.pitt.demo.gameoflife.ui.model.World;
import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
public class StreamForSSE {
	

	@Incoming("display-grids")
	@Outgoing("world-stream")
	@Broadcast
	public World toStream(World grid) {	
		return grid;
	}
	
}
