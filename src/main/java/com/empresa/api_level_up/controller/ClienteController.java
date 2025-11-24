package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@Tag(name = "Cliente", description = "Operaciones relacionadas con el cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Crear cliente", description = "Se guarda un cliente en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
    })
    public ResponseEntity<String> guardarCliente(@RequestBody Cliente cliente) {
        String message = clienteService.saveCliente(cliente);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>("Cliente guardado", HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Obtener clientes", description = "Se obtiene una lista de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
    })
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> lista = clienteService.findAll();
        if (lista != null) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

//        {
//            "first_name_cli": "string",
//                "last_name_cli": "string"
//        }

    }

}
