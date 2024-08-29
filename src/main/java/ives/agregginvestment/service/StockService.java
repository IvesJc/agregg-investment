package ives.agregginvestment.service;

import ives.agregginvestment.controller.dto.Stock.CreateStockDTO;
import ives.agregginvestment.entity.Stock;

public interface StockService {

    void createStock(CreateStockDTO stockDTO);
    Stock updateStock(CreateStockDTO stockDTO, String stockId);
    void deleteStock(String stockId);
}
