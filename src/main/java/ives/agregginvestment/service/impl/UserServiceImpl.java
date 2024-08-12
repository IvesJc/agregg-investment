package ives.agregginvestment.service.impl;

import ives.agregginvestment.dto.CreateUserDTO;
import ives.agregginvestment.dto.UpdateUserDTO;
import ives.agregginvestment.entity.User;
import ives.agregginvestment.repository.UserRepository;
import ives.agregginvestment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findById(UUID uuid){
        Optional<User> userDTO = userRepository.findById(uuid);

        return userDTO.orElse(null);
    }


    public UUID createUser(CreateUserDTO userDTO){
        User user = new User(
                UUID.randomUUID(),
                userDTO.username(),
                userDTO.email(),
                userDTO.password(),
                Instant.now(),
                null);
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
}
