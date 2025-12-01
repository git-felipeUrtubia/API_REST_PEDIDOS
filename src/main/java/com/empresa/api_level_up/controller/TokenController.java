package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.response.TokenResponseDTO;
import com.empresa.api_level_up.model.Token;
import com.empresa.api_level_up.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Token", description = "Operaciones relacionadas con Tokens")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping
    @Operation(summary = "Obtener tokens", description = "Se obtiene una lista de tokens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Token no encontrados")
    })
    public ResponseEntity<List<TokenResponseDTO>> findAll() {
        List<TokenResponseDTO> tokens = tokenService.findAll();
        if (tokens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    @GetMapping("{email}/{pass}")
    @Operation(summary = "Obtener Token con User", description = "Se obtiene un Token con un usuario al que esta vinculado ese token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Tokens no encontrados")
    })
    public ResponseEntity<?> findUserByEmailAndPassword(@PathVariable String email, @PathVariable String pass) {

        try {
            TokenResponseDTO.Login body = tokenService.findUserByEmailAndPassword(email, pass);
            if (body == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GetMapping("soloID")
    @Operation(summary = "Obtener ID token", description = "Solo visualiza el ID de los Tokens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Tokens no encontrados")
    })
    public ResponseEntity<List<TokenResponseDTO.SoloId>> SoloId() {
        List<TokenResponseDTO.SoloId> tokens = tokenService.SoloId();
        if (tokens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

}
