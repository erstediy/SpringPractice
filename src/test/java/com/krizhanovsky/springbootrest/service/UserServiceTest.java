package com.krizhanovsky.springbootrest.service;

import com.krizhanovsky.springbootrest.Entity.User;
import com.krizhanovsky.springbootrest.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private AutoCloseable autoCloseable;
    private UserService userService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void getUserById() {
    }

    @Test
    void canDeleteUser() {
        // given
        given(userRepository.existsById(anyInt())).willReturn(true);

        // when
        userService.deleteUser(anyInt());

        // then
        verify(userRepository).deleteUserById(anyInt());
    }


    @Test
    void willThrowWhenUserNotFound(){
        // given
        given(userRepository.existsById(anyInt())).willReturn(false);

        // then
        assertThatThrownBy(() -> userService.deleteUser(anyInt()))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("User with id=").hasMessageContaining("not found");

        verify(userRepository,never()).deleteUserById(anyInt());
    }

    @Test
    void canGetAllUsers() {
        // when
        userService.getAllUsers();

        // then
        verify(userRepository).findAll();
    }

    @Test
    void canUpdateUser() {

    }

    @Test
    void canAddUser() {
        // given
        User user = new User();
        user.setName("Jenny");
        user.setEmail("jennysis@sis.com");

        // when
        userService.addUser(user);

        // then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User capturedUser = captor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThrowWhenEmailExists(){
        // given
        User user = new User();
        user.setName("Jenny");
        user.setEmail("jennysis@sis.com");

        given(userRepository.existsByEmail(user.getEmail())).willReturn(true);

        // then
        assertThatThrownBy(() -> userService.addUser(user))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("User with email: "  + user.getEmail() + " already exists!");

        verify(userRepository,never()).save(any());
    }
}