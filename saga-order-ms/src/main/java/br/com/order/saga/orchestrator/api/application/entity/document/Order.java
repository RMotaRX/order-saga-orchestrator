package br.com.order.saga.orchestrator.api.application.entity.document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  private String id;
  private List<OrderProducts> products;
  private LocalDateTime createdAt;
  private String transactionId;
  private BigDecimal totalAmount;
  private Integer totalTimes;
}
