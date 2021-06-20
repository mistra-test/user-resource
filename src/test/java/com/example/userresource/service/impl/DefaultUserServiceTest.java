package com.example.userresource.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import com.example.userresource.model.User;
import com.example.userresource.repository.UserRepository;
import com.example.userresource.service.UserService;
import com.example.userresource.validation.UserValidator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootTest
class DefaultUserServiceTest {

    private UserRepository userRepositoryMock;

    private UserService userService;

    @Autowired
    DefaultUserServiceTest(PasswordEncoder passwordEncoder) {
        userRepositoryMock = Mockito.mock(UserRepository.class);

        DefaultUserService defaultUserService = new DefaultUserService();
        defaultUserService.setPasswordEncoder(passwordEncoder);
        defaultUserService.setUserRepository(userRepositoryMock);
        defaultUserService.setValidator(new UserValidator());
        userService = defaultUserService;
    }

    @Test
    void save() {
        String password = "password";
        User user = new User("FirstName", "LastName", "my@email.it", password);
        Mockito.when(userRepositoryMock.save(user)).thenAnswer(returnsFirstArg());

        User result = userService.save(user);
        assertNotEquals(password, result.getPassword());
    }

    @Test
    void findByEmailAndPassword() {
        Optional<User> answer =
                Optional.of(
                        new User(
                                "FirstName",
                                "LastName",
                                "my@email.it",
                                "$2a$10$n/mqMyFgg18yR5tajgfc4.djCv5IXQcDsp/I4nF75y.EuWcg24trG"));
        Mockito.when(userRepositoryMock.findByEmail("my@email.it")).thenReturn(answer);

        Optional<User> result = userService.findByEmailAndPassword("my@email.it", "password");
        assertTrue(result.isPresent());
    }
}
