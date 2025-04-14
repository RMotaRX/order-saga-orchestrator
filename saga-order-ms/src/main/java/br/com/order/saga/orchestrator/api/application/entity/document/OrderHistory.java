package br.com.order.saga.orchestrator.api.application.entity.document;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {

  private String source;
  private String status;
  private String message;
  private LocalDateTime createdAt;
}
