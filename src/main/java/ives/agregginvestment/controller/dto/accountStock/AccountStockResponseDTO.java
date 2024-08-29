package ives.agregginvestment.controller.dto.accountStock;

public record AccountStockResponseDTO(
        String stockId,
        Integer quantity,
        Double total
) {
}
