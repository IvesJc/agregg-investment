package ives.agregginvestment.service.impl;

import ives.agregginvestment.controller.dto.AccountStock.AccountStockResponseDTO;
import ives.agregginvestment.controller.dto.AccountStock.AssociateAccountStockDTO;
import ives.agregginvestment.entity.Account;
import ives.agregginvestment.entity.AccountStock;
import ives.agregginvestment.entity.AccountStockId;
import ives.agregginvestment.entity.Stock;
import ives.agregginvestment.repository.AccountRepository;
import ives.agregginvestment.repository.AccountStockRepository;
import ives.agregginvestment.repository.StockRepository;
import ives.agregginvestment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final AccountStockRepository accountStockRepository;

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
                                0.0)).
                        toList();
    }
}
