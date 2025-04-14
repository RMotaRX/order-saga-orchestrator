package br.com.inventory.saga.orchestrator.api.infrastructure.dto;

public record Product(
    String code,
    String unitValue
) { }
