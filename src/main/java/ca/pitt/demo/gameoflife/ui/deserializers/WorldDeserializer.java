package ca.pitt.demo.gameoflife.ui.deserializers;

import ca.pitt.demo.gameoflife.ui.model.World;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class WorldDeserializer extends JsonbDeserializer<World> {

	public WorldDeserializer() {
		super(World.class);
	}
	
}
