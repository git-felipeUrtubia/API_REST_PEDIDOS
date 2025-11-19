package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.response.TokenResponseDTO;
import com.empresa.api_level_up.model.Token;
import com.empresa.api_level_up.model.User;
import com.empresa.api_level_up.repository.TokenRepository;
import com.empresa.api_level_up.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepo;

    UserRepository userRepo;

    public List<TokenResponseDTO> findAll() {

        List<Token> tokens = tokenRepo.findAll();
        List<TokenResponseDTO> tokensDTOs = new ArrayList<>();

        for (Token token : tokens) {

            TokenResponseDTO tokenDTO = new TokenResponseDTO();
            tokenDTO.setToken(token.getToken());
            tokenDTO.setExpired_token(token.getExpired_token());
            tokenDTO.setEstado_token(token.getEstado_token());
            tokenDTO.setId_user(token.getUser().getId_user());

            tokensDTOs.add(tokenDTO);
        }
        return tokensDTOs;
    }



}
