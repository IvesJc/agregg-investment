package ives.agregginvestment.client.dto;

import java.util.List;

public record BrapiResponseDTO(
        List<StockDTO> results
) {
}
