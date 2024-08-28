package ives.agregginvestment.service;

import ives.agregginvestment.controller.dto.AccountStockResponseDTO;
import ives.agregginvestment.controller.dto.AssociateAccountStockDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AccountService {

    void associateStock(UUID accountId, AssociateAccountStockDTO dto);
    List<AccountStockResponseDTO> getAllAccountStocks(String accountId);

}
