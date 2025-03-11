package br.com.orchestrator.saga.orchestrator.api.infrastructure.config.kafka;

import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.BASE_ORCHESTRATOR;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.FINISH_FAIL;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.FINISH_SUCCESS;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.INVENTORY_FAIL;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.INVENTORY_SUCCESS;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.NOTIFY_ENDING;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.PAYMENT_FAIL;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.PAYMENT_SUCCESS;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.PRODUCT_VALIDATION_FAIL;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.PRODUCT_VALIDATION_SUCCESS;
import static br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics.START_SAGA;

import br.com.orchestrator.saga.orchestrator.api.application.utils.PropsUtil;
import br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ETopics;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

  private final PropsUtil props;

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerProps());
  }

  private Map<String, Object> consumerProps() {
    var config = new HashMap<String, Object>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.kafka().bootstrapServers());
    config.put(ConsumerConfig.GROUP_ID_CONFIG, props.kafka().consumer().groupId());
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, props.kafka().consumer().autoOffsetReset());

    return config;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerProps());
  }

  private Map<String, Object> producerProps() {
    var config = new HashMap<String, Object>();

    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.kafka().bootstrapServers());
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return config;
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String,String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

  private NewTopic buildTopic(String name) {
    return TopicBuilder
        .name(name)
        .replicas(props.kafka().replica().replicaCount())
        .partitions(props.kafka().partitions().partitionCount())
        .build();
  }

  @Bean
  public NewTopic startSagaTopic() {
    return buildTopic(START_SAGA.getTopic());
  }

  @Bean
  public NewTopic orchestratorTopic() {
    return buildTopic(BASE_ORCHESTRATOR.getTopic());
  }

  @Bean
  public NewTopic finishSuccessTopic() {
    return buildTopic(FINISH_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic finishFailTopic() {
    return buildTopic(FINISH_FAIL.getTopic());
  }

  @Bean
  public NewTopic productValidationSuccessTopic() {
    return buildTopic(PRODUCT_VALIDATION_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic productValidationFailTopic() {
    return buildTopic(PRODUCT_VALIDATION_FAIL.getTopic());
  }

  @Bean
  public NewTopic paymentSuccessTopic() {
    return buildTopic(PAYMENT_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic paymentFailTopic() {
    return buildTopic(PAYMENT_FAIL.getTopic());
  }

  @Bean
  public NewTopic inventorySuccessTopic() {
    return buildTopic(INVENTORY_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic inventoryFailTopic() {
    return buildTopic(INVENTORY_FAIL.getTopic());
  }

  @Bean
  public NewTopic notifyEndingTopic() {
    return buildTopic(NOTIFY_ENDING.getTopic());
  }
}
