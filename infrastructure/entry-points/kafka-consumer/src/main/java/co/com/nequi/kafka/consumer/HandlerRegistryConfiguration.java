package co.com.nequi.kafka.consumer;

import co.com.nequi.kafka.consumer.handlers.TopicsHandler;
import co.com.nequi.usecase.pedido.PedidoUseCase;
import org.reactivecommons.async.api.HandlerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class HandlerRegistryConfiguration {

    @Value("${reactive.commons.kafka.app.topics.pedidos}")
    private String topicName;

    @Bean
    public TopicsHandler topicsHandler(PedidoUseCase pedidoUseCase, ObjectMapper objectMapper) {
        return new TopicsHandler(pedidoUseCase, objectMapper);
    }

    @Bean
    public HandlerRegistry handlerRegistry(TopicsHandler topicsHandler) {
        return HandlerRegistry.register()
                .listenTopic(topicName, topicsHandler::handleMessage);
    }
}
