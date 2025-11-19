package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.request.ClienteRequestDTO;
import com.empresa.api_level_up.dto.request.UserRequestDTO;
import com.empresa.api_level_up.dto.response.TokenResponseDTO;
import com.empresa.api_level_up.dto.response.UserResponseDTO;
import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.model.Token;
import com.empresa.api_level_up.model.User;
import com.empresa.api_level_up.repository.ClienteRepository;
import com.empresa.api_level_up.repository.TokenRepository;
import com.empresa.api_level_up.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private TokenRepository tokenRepo;


    public UserResponseDTO saveUser(UserRequestDTO req) {

        User user = new User();
        user.setEmail_user( req.getEmailUser() );
        user.setPassword_user( req.getPasswordUser() );
        user.setRol_user( req.getRolUser() );
        userRepo.save( user );

        if (req.getRolUser().equalsIgnoreCase("user")) {

            Cliente cliente = new Cliente();
            cliente.setFirst_name_cli(req.getCliente().getFirstName());
            cliente.setLast_name_cli(req.getCliente().getLastName());
            cliente.setUser(user);

            clienteRepo.save(cliente);

            user.setCliente(cliente);
            userRepo.save(user);
        }

        UserResponseDTO res = new UserResponseDTO();
        res.setEmailUser( user.getEmail_user() );
        res.setPassword( user.getPassword_user() );
        res.setRolUser( user.getRol_user() );

        if(user.getRol_user().equals("user")) {
            UserResponseDTO.ClienteDTO cliDTO = new UserResponseDTO.ClienteDTO();
            cliDTO.setFirstName( req.getCliente().getFirstName() );
            cliDTO.setLastName( req.getCliente().getLastName() );
            res.setCliente( cliDTO );
        }

        return res;

    }

    public List<UserResponseDTO> findAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserResponseDTO> res = new ArrayList<>();
        for (User user : users) {
            UserResponseDTO resDTO = new UserResponseDTO();
            resDTO.setEmailUser( user.getEmail_user() );
            resDTO.setPassword( user.getPassword_user() );
            resDTO.setRolUser( user.getRol_user() );

            if(user.getRol_user().equals("user")) {
                UserResponseDTO.ClienteDTO cliDTO = new UserResponseDTO.ClienteDTO();
                cliDTO.setFirstName( user.getCliente().getFirst_name_cli() );
                cliDTO.setLastName( user.getCliente().getLast_name_cli() );
                resDTO.setCliente( cliDTO );
            }

            List<Token> tokens = tokenRepo.findAll();
            List<UserResponseDTO.TokenDTO> tokenDTOs = new ArrayList<>();
            for (Token token : tokens) {
                UserResponseDTO.TokenDTO tokenDTO = new UserResponseDTO.TokenDTO();
                tokenDTO.setId_token( token.getId_token() );
                tokenDTOs.add( tokenDTO );
            }
            resDTO.setTokens( tokenDTOs );

            res.add(resDTO);
        }
        return res;
    }

    public Token findUserByEmailAndPassword(String email, String password) {

        List<Token> tokens = new ArrayList<>();

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

                return token;
            }
        }
        return null;
    }


}
