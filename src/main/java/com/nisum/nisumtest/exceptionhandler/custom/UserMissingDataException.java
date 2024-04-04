package com.nisum.nisumtest.exceptionhandler.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserMissingDataException extends Exception {

    private static final Map<String, String> EXCEPTION_MESSAGES;

    private final String message;

    public static UserMissingDataException create(String exceptionType) {
        return new UserMissingDataException(EXCEPTION_MESSAGES.get(exceptionType));
    }

    static {
        Map<String, String> exceptionMessages = new HashMap<String, String>();
        exceptionMessages.put("NULL", "Los datos de usuario no pueden ser procesados como nulos");
        EXCEPTION_MESSAGES = exceptionMessages;
    }

}
