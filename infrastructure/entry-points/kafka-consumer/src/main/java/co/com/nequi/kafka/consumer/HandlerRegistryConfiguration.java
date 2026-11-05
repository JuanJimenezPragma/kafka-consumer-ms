package co.com.nequi.kafka.consumer;

import co.com.nequi.kafka.consumer.handlers.TopicsHandler;
import org.reactivecommons.async.api.HandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerRegistryConfiguration {

    @Bean
    public HandlerRegistry handlerRegistry(TopicsHandler topicsHandler) {
        return HandlerRegistry.register()
                .listenTopic("event.test", topicsHandler::handleMessage);
    }
}
