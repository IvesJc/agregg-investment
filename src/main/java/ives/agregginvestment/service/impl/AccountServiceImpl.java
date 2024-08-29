package ives.agregginvestment.service.impl;

import ives.agregginvestment.client.BrapiClient;
import ives.agregginvestment.controller.dto.accountStock.AccountStockResponseDTO;
import ives.agregginvestment.controller.dto.accountStock.AssociateAccountStockDTO;
import ives.agregginvestment.entity.Account;
import ives.agregginvestment.entity.AccountStock;
import ives.agregginvestment.entity.AccountStockId;
import ives.agregginvestment.entity.Stock;
import ives.agregginvestment.repository.AccountRepository;
import ives.agregginvestment.repository.AccountStockRepository;
import ives.agregginvestment.repository.StockRepository;
import ives.agregginvestment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    // Variavel de ambiente
    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final AccountStockRepository accountStockRepository;
    private final BrapiClient brapiClient;

    public void associateStock(UUID accountId, AssociateAccountStockDTO dto) {
        Account account =
                accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Stock stock =
                stockRepository.findById(dto.stockId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AccountStockId id = new AccountStockId(account.getAccountId(), stock.getStockId());
        AccountStock accountStockEntity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );
        accountStockRepository.save(accountStockEntity);
    }

    public List<AccountStockResponseDTO> getAllAccountStocks(String accountId) {
        Account account =
                accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.
                        getAccountStocks().
                        stream().
                        map(accountStock -> new AccountStockResponseDTO(
                                accountStock.getStock().getStockId(),
                                accountStock.getQuantity(),
                                getTotal(
                                        accountStock.getStock().getStockId(),
                                        accountStock.getQuantity()
                                ))).
                        toList();
    }

    private Double getTotal(String stockId, Integer quantity) {
        var response = brapiClient.getQuote(stockId, TOKEN);
        Double price = response.results().getFirst().regularMarketPrice();
        return quantity * price;
    }
}
