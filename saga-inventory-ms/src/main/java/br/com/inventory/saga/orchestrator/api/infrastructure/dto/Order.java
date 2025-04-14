package br.com.inventory.saga.orchestrator.api.infrastructure.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record Order(
    String id,
    List<OrderProducts> products,
    LocalDateTime createdAt,
    String transactionId,
    BigDecimal totalAmount,
    Integer totalTimes
) {
  public record OrderProducts(
      Product product,
      Integer quantity
  ) { }
}
