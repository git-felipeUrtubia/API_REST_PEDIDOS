package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.response.TokenResponseDTO;
import com.empresa.api_level_up.dto.response.UserResponseDTO;
import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.model.Pedido;
import com.empresa.api_level_up.model.Token;
import com.empresa.api_level_up.model.User;
import com.empresa.api_level_up.repository.ClienteRepository;
import com.empresa.api_level_up.repository.PedidoRepository;
import com.empresa.api_level_up.repository.TokenRepository;
import com.empresa.api_level_up.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ClienteRepository clienteRepo;

    @Autowired
    PedidoRepository pedidoRepo;

    public List<TokenResponseDTO> findAll() {

        List<Token> tokens = tokenRepo.findAll();
        List<TokenResponseDTO> tokensDTOs = new ArrayList<>();

        for (Token token : tokens) {

            TokenResponseDTO tokenDTO = new TokenResponseDTO();
            tokenDTO.setId_token(token.getId_token());
            tokenDTO.setToken(token.getToken());
            tokenDTO.setExpired_token(token.getExpired_token());
            tokenDTO.setEstado_token(token.getEstado_token());
            tokenDTO.setId_user(token.getUser().getId_user());

            tokensDTOs.add(tokenDTO);
        }
        return tokensDTOs;
    }


    public TokenResponseDTO.Login findUserByEmailAndPassword(String email, String password) {

        List<Token> tokens = new ArrayList<>();
        TokenResponseDTO.Login resLoginDTO = new TokenResponseDTO.Login();

        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.getEmail_user().equals(email) && user.getPassword_user().equals(password)) {
                Token token = new Token();
                token.setToken( UUID.randomUUID().toString() );
                token.setExpired_token(LocalDate.now().plusDays(2));
                token.setEstado_token("ACTIVE");
                token.setUser( user );
                tokens.add(token);

                tokenRepo.save(token);
                user.setTokens(tokens);
                userRepo.save(user);

                resLoginDTO.setToken( token.getToken() );
                resLoginDTO.setExpired_token( token.getExpired_token() );
                resLoginDTO.setEstado_token( token.getEstado_token() );

                UserResponseDTO.userTokenLogin userLogin = new UserResponseDTO.userTokenLogin();
                userLogin.setFirstNameUser( token.getUser().getFirstNameUser() );
                userLogin.setLastNameUser( token.getUser().getLastNameUser() );
                userLogin.setEmailUser( token.getUser().getEmail_user() );
                userLogin.setRolUser( token.getUser().getRol_user() );

                resLoginDTO.setUser( userLogin );

                return resLoginDTO;
            }
        }
        return null;
    }



    public List<TokenResponseDTO.SoloId> SoloId() {
        List<TokenResponseDTO.SoloId> list = new ArrayList<>();
        List<Token> tokens = tokenRepo.findAll();
        for (Token token : tokens) {
            TokenResponseDTO.SoloId soloId = new TokenResponseDTO.SoloId();
            soloId.setId_token(token.getId_token());
            list.add(soloId);
        }
        return list;
    }

    public Long findIdClientByToken(String token) {
        List<Token> tokens = tokenRepo.findAll();
        for (Token t : tokens) {
            if (t.getToken().equals(token)) {

                List<User> users = userRepo.findAll();
                for (User u : users) {
                    List<Token> userTokens = u.getTokens();
                    for (Token t2 : userTokens) {

                        if(t2.getToken().equals(token)) {

                            List<Cliente> clientes = clienteRepo.findAll();
                            for (Cliente c : clientes) {
                                if (u.getCliente().equals(c)) {

                                    return c.getId_cli();

                                }
                            }
                        }
                    }

                }

            }
        }
        return null;
    }


}
