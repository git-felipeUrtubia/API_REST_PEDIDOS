package com.empresa.api_level_up.service;

import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String saveCliente(Cliente cliente) {

        clienteRepository.save(cliente);
        return "guardado con exito";

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


}
