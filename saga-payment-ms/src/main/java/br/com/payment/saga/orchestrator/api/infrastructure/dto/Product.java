package br.com.payment.saga.orchestrator.api.infrastructure.dto;

public record Product(
    String code,
    String unitValue
) { }
