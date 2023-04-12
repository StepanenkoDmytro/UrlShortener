package com.urlshortener.controller;

import com.urlshortener.dto.AuthRequestDto;
import com.urlshortener.entity.User;
import com.urlshortener.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private AuthController authController;

    @Test
    void registrationReturnCreated() {
        AuthRequestDto requestDto = new AuthRequestDto();
        requestDto.setUsername("testUser");
        requestDto.setPassword("password");
        when(userService.isExist(requestDto.getUsername())).thenReturn(false);

        ResponseEntity<String> response = authController.registration(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        verify(userService).isExist(requestDto.getUsername());
        verify(userService, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    void registrationReturnIsExist(){
        AuthRequestDto requestDto = new AuthRequestDto();
        requestDto.setUsername("testUser");
        requestDto.setPassword("password");
        when(userService.isExist(requestDto.getUsername())).thenReturn(true);

        ResponseEntity<String> response = authController.registration(requestDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User is already exist", response.getBody());

        verify(userService).isExist(requestDto.getUsername());
        verifyNoMoreInteractions(userService);

    }
}