package br.com.order.saga.orchestrator.api.application.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring")
public record PropsUtil(
    Kafka kafka
) {
  public record Kafka(
      String bootstrapServers,
      Topic topic,
      Consumer consumer,
      Partitions partitions,
      Replica replica
  ) {
    public record Topic(
        String startSaga,
        String notifyEnding
    ) { }

    public record Consumer(
        String groupId,
        String autoOffsetReset
    ) { }

    public record Partitions(
        Integer partitionCount
    ) { }

    public record Replica(
        Integer replicaCount
    ) { }
  }
}
