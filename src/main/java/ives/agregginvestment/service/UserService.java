package ives.agregginvestment.service;

import ives.agregginvestment.dto.CreateUserDTO;
import ives.agregginvestment.dto.UpdateUserDTO;
import ives.agregginvestment.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UUID createUser(CreateUserDTO userDTO);

    List<User> findAllUsers();

    User findById(UUID uuid);
    User updateUser(UUID uuid, UpdateUserDTO user);
    void deleteUserById(UUID uuid);
}
