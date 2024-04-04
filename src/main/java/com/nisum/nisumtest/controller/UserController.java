package com.nisum.nisumtest.controller;

import com.nisum.nisumtest.model.User;
import com.nisum.nisumtest.service.UserService;
import com.nisum.nisumtest.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@Tag(name = "Nisum Test Resource")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Guarda Usuarios en la base de datos")
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        logger.info(user.toString());
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(Utils.toJSONFromObject(savedUser), HttpStatus.CREATED);
    }
}
