package com.nisum.nisumtest.exceptionhandler.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MailException extends Exception{

    private static final Map<String, String> EXCEPTION_MESSAGES;

    private final String message;

    public static MailException create(String exceptionType) {
        return new MailException(EXCEPTION_MESSAGES.get(exceptionType));
    }

    static {
        Map<String, String> exceptionMessages = new HashMap<String, String>();
        exceptionMessages.put("INACTIVE", "Este eMail esta temporalmente INACTIVO. Por favor gestione su activacion!");
        exceptionMessages.put("EXISTS", "Este eMail YA existe en nuestros registros!");
        exceptionMessages.put("NOT_FOUND", "El eMail ingresado NO existe en nuestros registros!");
        exceptionMessages.put("INVALID", "El eMail ingresado NO cumple con el formato correcto! Por favor verificar!");

        EXCEPTION_MESSAGES = exceptionMessages;
    }
}
