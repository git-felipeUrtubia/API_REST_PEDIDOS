package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.request.ClienteRequestDTO;
import com.empresa.api_level_up.dto.request.UserRequestDTO;
import com.empresa.api_level_up.dto.response.UserResponseDTO;
import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.model.User;
import com.empresa.api_level_up.repository.ClienteRepository;
import com.empresa.api_level_up.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ClienteRepository clienteRepo;


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
            userRepo.save(user); // actualizar vínculo
        }

        UserResponseDTO res = new UserResponseDTO();
        res.setEmailUser( user.getEmail_user() );
        res.setRolUser( user.getRol_user() );

        if(user.getRol_user().equals("user")) {
            UserResponseDTO.ClienteDTO cliDTO = new UserResponseDTO.ClienteDTO();
            cliDTO.setFirstName( req.getCliente().getFirstName() );
            cliDTO.setLastName( req.getCliente().getLastName() );
            res.setCliente( cliDTO );
        }

        return res;

    }


}
