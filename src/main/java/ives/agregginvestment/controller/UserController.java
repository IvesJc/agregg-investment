package ives.agregginvestment.controller;

import ives.agregginvestment.controller.dto.Account.AccountResponseDTO;
import ives.agregginvestment.controller.dto.Account.CreateAccountDTO;
import ives.agregginvestment.controller.dto.User.*;
import ives.agregginvestment.entity.User;
import ives.agregginvestment.service.impl.AccountServiceImpl;
import ives.agregginvestment.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userServiceImpl.findAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFindByIdUserDTO> findById(@PathVariable UUID id) {
        ResponseFindByIdUserDTO userDTO = userServiceImpl.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<RespondCreateUserDTO> createUser(@RequestBody RequestCreateUserDTO userDTO) {
        RespondCreateUserDTO newUser = userServiceImpl.createUser(userDTO);
        return ResponseEntity.created(URI.create("/users/" +newUser.id())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUpdateUserDTO> updateUser(@RequestBody RequestUpdateUserDTO user,
                                                            @PathVariable UUID id) {
        ResponseUpdateUserDTO newUser = userServiceImpl.updateUser(id, user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // =========================== ACCOUNTS ==================================

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountDTO account,
                                                 @PathVariable("userId") UUID userId){
        userServiceImpl.createAccount(account, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccountsByUserId(@PathVariable("userId") UUID userId){
        List<AccountResponseDTO> accountList = userServiceImpl.getAllAccountsByUserId(userId);
        return ResponseEntity.ok(accountList);
    }


}
