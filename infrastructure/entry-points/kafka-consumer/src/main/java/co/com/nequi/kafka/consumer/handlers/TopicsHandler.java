package co.com.nequi.kafka.consumer.handlers;

import co.com.nequi.model.pedido.Pedido;
import co.com.nequi.usecase.pedido.PedidoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.RawMessage;
import org.reactivecommons.async.impl.config.annotations.EnableTopicListeners;
import org.reactivecommons.async.kafka.KafkaMessage;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Log
@RequiredArgsConstructor
@EnableTopicListeners
public class TopicsHandler {

    private final PedidoUseCase pedidoUseCase;

    private final ObjectMapper objectMapper;

    public Mono<Void> handleMessage(RawMessage message) {
        KafkaMessage rawMessage = (KafkaMessage) message;
        log.info("Message received: " + new String(rawMessage.getBody()));
        return Mono.fromCallable(() -> objectMapper.readValue(rawMessage.getBody(), Pedido.class))
                .flatMap(pedidoUseCase::printPedido);
    }
}
