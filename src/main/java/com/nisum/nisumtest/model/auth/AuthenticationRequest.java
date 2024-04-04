package com.nisum.nisumtest.model.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;

}
