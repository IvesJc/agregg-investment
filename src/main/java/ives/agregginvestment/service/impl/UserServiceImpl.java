package ives.agregginvestment.service.impl;

import ives.agregginvestment.controller.dto.AccountResponseDTO;
import ives.agregginvestment.controller.dto.CreateAccountDTO;
import ives.agregginvestment.controller.dto.CreateUserDTO;
import ives.agregginvestment.controller.dto.UpdateUserDTO;
import ives.agregginvestment.entity.Account;
import ives.agregginvestment.entity.BillingAddress;
import ives.agregginvestment.entity.User;
import ives.agregginvestment.repository.AccountRepository;
import ives.agregginvestment.repository.BillingAddressRepository;
import ives.agregginvestment.repository.UserRepository;
import ives.agregginvestment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final BillingAddressRepository billingAddressRepository;


    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID userId){
        return userRepository.findById(userId);
    }


    public UUID createUser(CreateUserDTO userDTO){
        User user = new User(
                UUID.randomUUID(),
                userDTO.username(),
                userDTO.email(),
                userDTO.password(),
                Instant.now(),
                null,
                new ArrayList<>());
        User userSaved = userRepository.save(user);
        return userSaved.getUserId();
    }

    @Override
    public User updateUser(UUID uuid, UpdateUserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(uuid);

        if (optionalUser.isPresent()){
               User newUser = optionalUser.get();
               newUser.setUsername(userDTO.username());
               newUser.setPassword(userDTO.password());
               newUser.setUpdateTimestamp(Instant.now());

               userRepository.save(newUser);

               return newUser;
        }
        return null;
    }

    @Override
    public void deleteUserById(UUID uuid) {
        Optional<User> optionalUser = userRepository.findById(uuid);
        if (optionalUser.isPresent()){
            userRepository.deleteById(uuid);
        }
    }

    @Override
    public void createAccount(CreateAccountDTO accountDTO, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Account account = new Account(
                UUID.randomUUID(),
                accountDTO.description(),
                user,
                null,
                new ArrayList<>()
        );
        Account accountCreated = accountRepository.save(account);

        BillingAddress billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                accountDTO.street(),
                accountDTO.number()
        );
        BillingAddress billingAddressCreated = billingAddressRepository.save(billingAddress);
    }

    @Override
    public List<AccountResponseDTO> getAllAccountsByUserId(UUID userId) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user.
                getAccounts().
                stream().
                map(account -> new AccountResponseDTO(
                        account.getAccountId().toString(),
                        account.getDescription())).
                toList();
    }
}
