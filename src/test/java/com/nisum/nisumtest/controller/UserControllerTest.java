package com.nisum.nisumtest.controller;

import com.nisum.nisumtest.model.User;
import com.nisum.nisumtest.service.UserService;
import com.nisum.nisumtest.utils.JSONUtils;
import com.nisum.nisumtest.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private final UserService userService = mock(UserService.class);
    private final Utils utils = mock(Utils.class);
    UserController userController = new UserController(userService);

    @Test
    public void shouldBeASuccessfulCall() {
        ResponseEntity<Object> successResponse = new ResponseEntity<>(Utils.toJSONFromObject(null), HttpStatus.CREATED);
        User user = JSONUtils.readJson("/user-register-payload.json", User.class);

        when(userService.saveUser(any())).thenReturn(isA(User.class));
        ResponseEntity<String> controllerResponse = userController.addUser(user);

        verify(userService, times(1)).saveUser(isA(User.class));
        assertEquals(successResponse, controllerResponse);
    }
}
