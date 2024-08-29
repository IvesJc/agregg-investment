package ives.agregginvestment.controller;

import ives.agregginvestment.controller.dto.Stock.CreateStockDTO;
import ives.agregginvestment.entity.Stock;
import ives.agregginvestment.service.impl.StockServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockServiceImpl stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO stockDTO){
        stockService.createStock(stockDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{stockId}")
    public ResponseEntity<Stock> updateStock(@RequestBody CreateStockDTO stockDTO,
                                             @PathVariable("stockId") String stockId){
        Stock stock = stockService.updateStock(stockDTO, stockId);
        return ResponseEntity.ok(stock);
    }

    @DeleteMapping("{stockId}")
    public ResponseEntity<Void> deleteStock(@PathVariable("stockId") String stockId){
        stockService.deleteStock(stockId);
        return ResponseEntity.noContent().build();
    }
}
