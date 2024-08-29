package ives.agregginvestment.controller.dto.AccountStock;

public record AccountStockResponseDTO(
        String stockId,
        Integer quantity,
        Double total
) {
}
