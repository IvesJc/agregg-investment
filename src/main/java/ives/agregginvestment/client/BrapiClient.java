package ives.agregginvestment.client;

import ives.agregginvestment.client.dto.BrapiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "BrapiClient",
        url = "https://brapi.dev")

public interface BrapiClient {

    @GetMapping("/api/quote/{stockId}")
    BrapiResponseDTO getQuote(@PathVariable("stockId") String stockId,
                                                     @RequestParam("token") String token);
}
