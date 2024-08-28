package ives.agregginvestment.service.impl;

import ives.agregginvestment.controller.dto.CreateUserDTO;
import ives.agregginvestment.controller.dto.UpdateUserDTO;
import ives.agregginvestment.entity.User;
import ives.agregginvestment.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

//     PADRÃO TRIPLE A:
//        - Arrange
//        - Act
//        - Assert


    // @Mock é usada para criar um "mock" (objeto simulado) de uma classe ou interface. Um mock é um objeto
    // que imita o comportamento de uma dependência real, mas sem executar o código real dessa dependência.
    @Mock
    private UserRepository userRepository;


    // @InjectMocks, você garante que as dependências do objeto de teste sejam controladas e manipuláveis,
    // permitindo focar exclusivamente no comportamento da classe sob teste (UserServiceImpl).
    @InjectMocks
    private UserServiceImpl userService;
    // Aqui, userService é a instância da classe UserServiceImpl que está sendo testada. O Mockito injeta automaticamente o mock userRepository no campo correspondente dentro de userService.


    // ArgumentCaptor é uma ferramenta do Mockito que permite capturar e inspecionar os argumentos passados para um método mockado.
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;


//    @BeforeAll

    @Nested
    class findAllUsers {

        @Test
        void shouldReturnAListOfUsersWithSuccess() {
            // Arrange
            User user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            List<User> userList = List.of(user);
            doReturn(userList).when(userRepository).findAll();

            // Act
            var output = userService.findAllUsers();

            // Assert
            assertNotNull(output);
            assertEquals(userList.size(), output.size());
        }
    }

    @Nested
    class findById {

        @Test
        void shouldFindAUserByIDWithSuccessWhenOptionalIsPresent() {
            // Arrange
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());

            // Act
            var output = userService.findById(user.getUserId());

            // Assert
            assertTrue(output.isPresent());
            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
        }

        @Test
        void shouldFindAUserByIDWithSuccessWhenOptionalIsEmpty() {
            // Arrange

            UUID userId = UUID.randomUUID();

            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());

            // Act
            var output = userService.findById(userId);

            // Assert
            assertTrue(output.isEmpty());
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }

        @Test
        void shouldThrowExceptionWhenErrorOccurs() {
            // Arrange
            UUID userId = UUID.randomUUID();
            doThrow(RuntimeException.class).when(userRepository).findById(uuidArgumentCaptor.capture());

            // Act & Assert
            assertThrows(RuntimeException.class, () -> userService.findById(userId));
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }
    }

    // @Nested: Capaz de definir uma sub-classe visando a organização da classe de Test
    @Nested
    class createUser {
        @Test
        void shouldCreateAUserWithSuccess() {
            // Arrange
            User user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",                       //Aqui, você está criando um objeto User com valores simulados.
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            //Essa linha configura o mock userRepository para retornar o objeto user
            // quando o método save() for chamado com qualquer argumento (any() significa
            // que ele aceitará qualquer instância do tipo User). Isso simula a operação de salvar o
            // usuário no banco de dados.

            CreateUserDTO input = new CreateUserDTO(
                    "username",
                    "email@email.com",
                    "email123");
            // Representa os dados que o serviço espera receber ao tentar criar um novo usuário.


            // Act
            UUID output = userService.createUser(input);

            // Assert
            assertNotNull(output);

            User userCaptured = userArgumentCaptor.getValue();

            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }


        @Test
        void shouldThrowExceptionWhenErrorOccurs() {
            // Arrange

            doThrow(new RuntimeException()).when(userRepository).save(any());

            CreateUserDTO input = new CreateUserDTO(
                    "username",
                    "email@email.com",
                    "email123");


            // Act & Assert
            assertThrows(RuntimeException.class, () -> userService.createUser(input));
        }
    }

    @Nested
    class updateUser {

        @Test
        void shouldUpdateUserByIdWithSuccessWhenUserExists() {
            // Arrange
            UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                    "newUsername",
                    "newPassword",
                    null
            );
            User user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            // Act
            var output = userService.updateUser(user.getUserId(), updateUserDTO);

            // Assert
            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
            assertEquals(updateUserDTO.username(), userArgumentCaptor.getValue().getUsername());
            assertEquals(updateUserDTO.password(), userArgumentCaptor.getValue().getPassword());

            verify(userRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(userRepository, times(1)).save(user);
        }
        @Test
        void shouldNotUpdateUserByIdWithSuccessWhenUserNotExists() {
            // Arrange
            UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                    "newUsername",
                    "newPassword",
                    null
            );
            UUID userId = UUID.randomUUID();

            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());

            // Act
            var output = userService.updateUser(userId, updateUserDTO);

            // Assert
            assertEquals(userId, uuidArgumentCaptor.getValue());

            verify(userRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(userRepository, times(0)).save(any());
        }

    }

    @Nested
    class deleteUserById {

        @Test
        void shouldDeleteUserByIdWithSuccess() {
            // Arrange
            User user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(uuidArgumentCaptor.capture());
            doNothing()
                    .when(userRepository)
                    .deleteById(uuidArgumentCaptor.capture());
            UUID userId = UUID.randomUUID();

            // Act
            userService.deleteUserById(userId);

            // Assert
            var idList = uuidArgumentCaptor.getAllValues();
            assertEquals(userId, idList.get(0));
            assertEquals(userId, idList.get(1));

            verify(userRepository, times(1)).findById(idList.get(0));
            verify(userRepository, times(1)).deleteById(idList.get(1));

        }

        @Test
        void shouldNotDeleteUserByIdWithSuccessWhenUserNotExists() {
            // Arrange
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(uuidArgumentCaptor.capture());

            UUID userId = UUID.randomUUID();

            // Act
            userService.deleteUserById(userId);

            // Assert
            assertEquals(userId, uuidArgumentCaptor.getValue());

            verify(userRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(userRepository, times(0)).deleteById(any());

        }
    }
}