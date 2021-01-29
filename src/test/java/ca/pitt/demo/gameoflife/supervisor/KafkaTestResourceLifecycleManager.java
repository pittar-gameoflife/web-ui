package ca.pitt.demo.gameoflife.supervisor;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;

public class KafkaTestResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        Map<String, String> props1 = InMemoryConnector.switchIncomingChannelsToInMemory("display-grids");  
        Map<String, String> props2 = InMemoryConnector.switchOutgoingChannelsToInMemory("work-grids");   
        env.putAll(props1);
        env.putAll(props2);
        return env;  
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();  
    }
}
