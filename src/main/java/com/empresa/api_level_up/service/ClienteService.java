package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.ClienteDTO;
import com.empresa.api_level_up.dto.DetallePedidoDTO;
import com.empresa.api_level_up.dto.PedidoDTO;
import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
