package co.com.nequi.config.kafka;

//import co.com.nequi.kafka.consumer.config.KafkaConfigProperties;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.async.kafka.config.KafkaProperties;
import org.reactivecommons.async.kafka.config.props.AsyncKafkaPropsDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Hybrid configuration (YAML + KafkaPropsCustomizer) for the Reactive Commons Kafka domains.
 * <p>
 * Values bound from application.yaml are kept; only the fields set below are overridden.
 *
 * @see <a href="https://bancolombia.github.io/reactive-commons-java/docs/reactive-commons/configuration_properties/kafka">Kafka configuration properties</a>
 */
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private static final String DEFAULT_DOMAIN = "app";
    private static final String SASL_PROTOCOL = "SASL_SSL";
    private static final String SASL_MECHANISM_KEY = "sasl.mechanism";
    private static final String SASL_JAAS_CONFIG_KEY = "sasl.jaas.config";
    private static final String SCRAM_MECHANISM = "SCRAM-SHA-512";
    private static final String SCRAM_LOGIN_MODULE =
            "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";

    // Expose a KafkaConfigProperties bean reading the broker address and the SASL/SCRAM
    // credentials from your secrets manager, then uncomment the field below to inject it.
//    private final KafkaConfigProperties configProperties;

    @Bean
    public AsyncKafkaPropsDomain.KafkaPropsCustomizer kafkaPropsCustomizer() {
        return domainProperties -> domainProperties.customize(DEFAULT_DOMAIN, domain -> {
                    KafkaProperties connection = domain.getConnectionProperties();
                    connection.setBootstrapServers(List.of("localhost:9092"));
//                  connection.setBootstrapServers(List.of(configProperties.bootstrapserver()));

                    // Uncomment to apply SASL/SCRAM authentication
//                    connection.getSecurity().setProtocol(SASL_PROTOCOL);
//                    Map<String, String> properties = connection.getProperties();
//                    properties.put(SASL_MECHANISM_KEY, SCRAM_MECHANISM);
//                    properties.put(SASL_JAAS_CONFIG_KEY, String.format(
//                            SCRAM_LOGIN_MODULE, configProperties.username(), configProperties.password()
//                    ));
                }
        );
    }
}
