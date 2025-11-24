package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.request.PasswordResetTokenRequestDTO;
import com.empresa.api_level_up.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/passd/auth")
@CrossOrigin("*")
public class PasswordResetTokenController {

    @Autowired
    PasswordResetTokenService passwordResetTokenService;

    @PostMapping("/solicitar-password")
    public ResponseEntity<String> solicitarPassword(@RequestBody PasswordResetTokenRequestDTO.Solicitar req) {
        String mensaje = passwordResetTokenService.SolicitarPassword(req);
        if (mensaje != null) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetTokenRequestDTO.Reestablecer req) {
        String mensaje = passwordResetTokenService.ResetPassword(req);
        if (mensaje != null) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
