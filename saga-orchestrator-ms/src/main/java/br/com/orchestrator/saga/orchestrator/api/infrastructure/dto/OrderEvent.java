package br.com.orchestrator.saga.orchestrator.api.infrastructure.dto;

import br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.EEventSource;
import br.com.orchestrator.saga.orchestrator.api.infrastructure.enums.ESagaStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record OrderEvent(
    String id,
    String transactionId,
    String orderId,
    Order payload,
    EEventSource source,
    ESagaStatus status,
    List<OrderHistory> eventOrderHistories,
    LocalDateTime createdAt
) {
  public record OrderHistory(
      EEventSource source,
      ESagaStatus status,
      String message,
      LocalDateTime createdAt
  ) { }
}
