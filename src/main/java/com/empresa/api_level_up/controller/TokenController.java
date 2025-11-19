package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.response.TokenResponseDTO;
import com.empresa.api_level_up.model.Token;
import com.empresa.api_level_up.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/tokens")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<TokenResponseDTO>> findAll() {
        List<TokenResponseDTO> tokens = tokenService.findAll();
        if (tokens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    @GetMapping("{email}/{pass}")
    public ResponseEntity<TokenResponseDTO.Login> findUserByEmailAndPassword(@PathVariable String email, @PathVariable String pass) {

        try {
            TokenResponseDTO.Login body = tokenService.findUserByEmailAndPassword(email, pass);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GetMapping("soloID")
    public ResponseEntity<List<TokenResponseDTO.SoloId>> SoloId() {
        List<TokenResponseDTO.SoloId> tokens = tokenService.SoloId();
        if (tokens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

}
