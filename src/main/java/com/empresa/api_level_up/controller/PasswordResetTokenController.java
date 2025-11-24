package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.request.PasswordResetTokenRequestDTO;
import com.empresa.api_level_up.service.PasswordResetTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/passd/auth")
@Tag(name = "Reset Password", description = "Operaciones relacionadas con Password")
public class PasswordResetTokenController {

    @Autowired
    PasswordResetTokenService passwordResetTokenService;

    @PostMapping("/solicitar-password")
    @Operation(summary = "Solicita una contraseña", description = "Solicita una password al email ingresado por parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Email no encontrado")
    })
    public ResponseEntity<String> solicitarPassword(@RequestBody PasswordResetTokenRequestDTO.Solicitar req) {
        String mensaje = passwordResetTokenService.SolicitarPassword(req);
        if (mensaje != null) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Registra una contraseña", description = "Reestablece la contraseña con el token entregado que se le envia por link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Token no encontrado")
    })
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetTokenRequestDTO.Reestablecer req) {
        String mensaje = passwordResetTokenService.ResetPassword(req);
        if (mensaje != null) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
