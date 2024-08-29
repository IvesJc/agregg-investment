package ives.agregginvestment.service;

import ives.agregginvestment.controller.dto.Account.AccountResponseDTO;
import ives.agregginvestment.controller.dto.Account.CreateAccountDTO;
import ives.agregginvestment.controller.dto.User.*;
import ives.agregginvestment.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    RespondCreateUserDTO createUser(RequestCreateUserDTO userDTO);
    List<User> findAllUsers();
    ResponseFindByIdUserDTO findById(UUID uuid);
    ResponseUpdateUserDTO updateUser(UUID uuid, RequestUpdateUserDTO user);
    void deleteUserById(UUID uuid);


    void createAccount(CreateAccountDTO account, UUID userId);

    List<AccountResponseDTO> getAllAccountsByUserId(UUID userId);
}
