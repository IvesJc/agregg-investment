package ives.agregginvestment.service.impl;

import ives.agregginvestment.controller.dto.account.AccountResponseDTO;
import ives.agregginvestment.controller.dto.account.CreateAccountDTO;
import ives.agregginvestment.controller.dto.user.*;
import ives.agregginvestment.entity.Account;
import ives.agregginvestment.entity.BillingAddress;
import ives.agregginvestment.entity.User;
import ives.agregginvestment.repository.AccountRepository;
import ives.agregginvestment.repository.BillingAddressRepository;
import ives.agregginvestment.repository.UserRepository;
import ives.agregginvestment.service.UserService;
import lombok.RequiredArgsConstructor;
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


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public ResponseFindByIdUserDTO findById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User newUser = user.get();
            return new ResponseFindByIdUserDTO(
                    newUser.getUsername(),
                    newUser.getEmail(),
                    newUser.getCreationTimestamp(),
                    newUser.getUpdateTimestamp(),
                    newUser.getAccounts()
            );
        }
        throw new RuntimeException();
    }


    public RespondCreateUserDTO createUser(RequestCreateUserDTO userDTO) {
        User user = new User(
                UUID.randomUUID(),
                userDTO.username(),
                userDTO.email(),
                userDTO.password(),
                Instant.now(),
                null,
                new ArrayList<>());
        RespondCreateUserDTO createUserDTO = new RespondCreateUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreationTimestamp()
        );
        userRepository.save(user);
        return createUserDTO;
    }

    @Override
    public ResponseUpdateUserDTO updateUser(UUID uuid, RequestUpdateUserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(uuid);

        if (optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            newUser.setUsername(userDTO.username());
            newUser.setEmail(userDTO.email());
            newUser.setPassword(userDTO.password());
            newUser.setUpdateTimestamp(Instant.now());

            userRepository.save(newUser);

            return new ResponseUpdateUserDTO(
                    newUser.getUsername(),
                    newUser.getEmail(),
                    newUser.getUpdateTimestamp()
            );
        }
        return null;
    }

    @Override
    public void deleteUserById(UUID uuid) {
        Optional<User> optionalUser = userRepository.findById(uuid);
        if (optionalUser.isPresent()) {
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
