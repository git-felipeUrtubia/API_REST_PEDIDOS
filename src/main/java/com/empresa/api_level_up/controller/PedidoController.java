package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.request.PedidoRequestDTO;
import com.empresa.api_level_up.dto.response.PedidoResponseDTO;
import com.empresa.api_level_up.model.Pedido;
import com.empresa.api_level_up.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;



    @PostMapping
    public PedidoResponseDTO crear(@RequestBody PedidoRequestDTO body) {
        return pedidoService.crearPedido(body);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        List<PedidoResponseDTO> pedidos = pedidoService.listarPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @DeleteMapping("{id}")
    public PedidoResponseDTO deletePedidoById(@PathVariable Long id) {
        return pedidoService.deletePedidoById(id);
    }

    @DeleteMapping
    public String deleteAllPedidos() {
        return pedidoService.DeleteAllPedidos();
    }

}
