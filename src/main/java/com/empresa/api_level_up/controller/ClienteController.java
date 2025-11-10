package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> guardarCliente(@RequestBody Cliente cliente) {
        String message = clienteService.saveCliente(cliente);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>("Cliente guardado", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> lista = clienteService.findAll();
        if (lista != null) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
