package ca.pitt.demo.gameoflife.supervisor;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ca.pitt.demo.gameoflife.ui.model.World;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.connectors.InMemorySink;

@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
public class NextGenerationResourceTest {

    @Inject @Any
    InMemoryConnector connector;
	
    // 2. Don't forget to reset the channel after the tests:
    @AfterEach
    public void revertMyChannels() {
        InMemorySink<World> grids = connector.sink("work-grids");    
        grids.clear();
//    	InMemoryConnector.clear();
    }
    
//    // 1. Switch the channels to the in-memory connector:
//    @BeforeAll
//    public static void switchMyChannels() {
//        InMemoryConnector.switchIncomingChannelsToInMemory("display-girds");
//        InMemoryConnector.switchOutgoingChannelsToInMemory("work-grids");
//    }
//
//    // 2. Don't forget to reset the channel after the tests:
//    @AfterAll
//    public static void revertMyChannels() {
//        InMemoryConnector.clear();
//    }
	
    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/grid.html")
          .then()
             .statusCode(200)
             .body(containsString("Game of Life"));
    }
    
    @Test
    public void testResetGrid() {
        given()
          .when().get("/step/reset")
          .then()
             .statusCode(200)
             .body(is("Reset world."));
    }

    @Test
    public void testNextGeneration() {
        InMemorySink<World> grids = connector.sink("work-grids");    
    	
    	given().contentType(ContentType.JSON).body("[[0,0],[0,1]]")
    		.when().post("/step/next")
			.then()
				.statusCode(200);    	

    	World sentWorld = grids.received().get(0).getPayload();
    	Assertions.assertEquals(1, grids.received().size());
    	Assertions.assertEquals(0, sentWorld.getState()[0][0]);
    	Assertions.assertEquals(1, sentWorld.getState()[1][1]);
    }
    
}