package br.com.orchestrator.saga.orchestrator.api.application.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring")
public record PropsUtil(
    Kafka kafka
) {
  public record Kafka(
      String bootstrapServers,
      Consumer consumer,
      Partitions partitions,
      Replica replica
  ) {
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
