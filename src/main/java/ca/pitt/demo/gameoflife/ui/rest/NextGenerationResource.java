package ca.pitt.demo.gameoflife.ui.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import ca.pitt.demo.gameoflife.ui.model.World;

@Path("/step")
public class NextGenerationResource {
	
	@Inject
	@Channel("work-grids")
	Emitter<World> workGrids;
	
	@POST
	@Path("/next")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String next(int[][] grid) {
		System.err.println("Hello!!");
		World world = new World();
		if (null != grid) {
			world = new World();
			for (int i=0; i<grid.length; i++) {
				for (int j=0; j<grid[i].length; j++) {
					world.setCell(i, j, grid[i][j]);
				}
			}
		}

		workGrids.send(world);
		return "Stepping through simulation.";
	}

	@GET
	@Path("/reset")
	@Produces(MediaType.TEXT_PLAIN)
	public String reset() {
		workGrids.send(new World());
		return "Reset world.";
	}
}