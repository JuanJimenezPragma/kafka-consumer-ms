package co.com.nequi.kafka.consumer.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.RawMessage;
import org.reactivecommons.async.impl.config.annotations.EnableTopicListeners;
import org.reactivecommons.async.kafka.KafkaMessage;
import reactor.core.publisher.Mono;


@Log
@RequiredArgsConstructor
@EnableTopicListeners
public class TopicsHandler {

    // private final SampleUseCase sampleUseCase;

    // See more at: https://bancolombia.github.io/reactive-commons-java/docs/reactive-commons/handling-queues-and-topics/kafka
    public Mono<Void> handleMessage(RawMessage message) {
        KafkaMessage rawMessage = (KafkaMessage) message;
        log.info("Message received from topic: " + new String(rawMessage.getBody()));
        log.info("Headers: " + rawMessage.getProperties().getHeaders());
        // Process the message
        return Mono.empty();
    }
}
