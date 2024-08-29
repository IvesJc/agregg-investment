package ives.agregginvestment.controller;

import ives.agregginvestment.controller.dto.accountStock.AccountStockResponseDTO;
import ives.agregginvestment.controller.dto.accountStock.AssociateAccountStockDTO;
import ives.agregginvestment.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStocks(@PathVariable("accountId")UUID accountId,
                                                @RequestBody AssociateAccountStockDTO dto){
        accountService.associateStock(accountId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> getAllAccountStocks(@PathVariable("accountId") String accountId){
        List<AccountStockResponseDTO> accountStockResponseDTOS = accountService.getAllAccountStocks(accountId);
        return ResponseEntity.ok(accountStockResponseDTOS);
    }
}
