package com.nisum.nisumtest.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    private String token;

    public static TokenInfo getTokenInfoBuilder(String token) {
        return TokenInfo.builder()
                .token(token)
                .build();
    }
}
