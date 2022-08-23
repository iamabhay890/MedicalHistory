package com.MedicalHistory.services.impl;

import com.MedicalHistory.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserService userService;

    @Test
    void getUserById() {

//        UserDto userDto = this.userService.getUserById(-1);
//        org.assertj.core.api.Assertions.assertThat(userDto.getEmail()).isEqualTo("admin");
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void getAdmin() {
    }
}