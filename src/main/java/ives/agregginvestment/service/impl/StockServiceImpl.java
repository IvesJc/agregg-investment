package ives.agregginvestment.service.impl;

import ives.agregginvestment.controller.dto.CreateStockDTO;
import ives.agregginvestment.entity.Stock;
import ives.agregginvestment.repository.StockRepository;
import ives.agregginvestment.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;


    @Override
    public void createStock(CreateStockDTO stockDTO) {

        Stock stock = new Stock(
                stockDTO.stockId(),
                stockDTO.description()
        );
        stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(CreateStockDTO stockDTO, String stockId) {
        Optional<Stock> optionalStock = stockRepository.findById(stockId);

        if (optionalStock.isPresent()){
            Stock stock = optionalStock.get();
            stock.setStockId(stockDTO.stockId());
            stock.setDescription(stockDTO.description());

            stockRepository.save(stock);
            return stock;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteStock(String stockId) {
        Stock stock =
                stockRepository.findById(stockId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        stockRepository.deleteById(stock.getStockId());
    }
}
