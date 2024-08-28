package ives.agregginvestment.controller.dto;

public record AccountStockResponseDTO(
        String stockId,
        Integer quantity,
        Double total
) {
}
