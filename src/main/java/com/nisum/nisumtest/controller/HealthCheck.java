package com.nisum.nisumtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Nisum Test Resource")
public class HealthCheck {

    @Operation(summary = "Informa si la app esta corriendo")
    @GetMapping("healthCheck")
    @ResponseBody
    public String healthCheck() {
        return "NISUM APP IS UP";
    }
}
