package br.com.inventory.saga.orchestrator.api.infrastructure.config.kafka;

import br.com.inventory.saga.orchestrator.api.application.utils.PropsUtil;
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
  public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
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
  public NewTopic orchestratorTopic() {
    return buildTopic(props.kafka().topic().orchestrator());
  }

  @Bean
  public NewTopic inventorySuccessTopic() {
    return buildTopic(props.kafka().topic().inventorySuccess());
  }

  @Bean
  public NewTopic inventoryFailTopic() {
    return buildTopic(props.kafka().topic().inventoryFail());
  }
}
