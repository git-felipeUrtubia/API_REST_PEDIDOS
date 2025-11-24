package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.request.PedidoRequestDTO;
import com.empresa.api_level_up.dto.response.PedidoResponseDTO;
import com.empresa.api_level_up.model.Pedido;
import com.empresa.api_level_up.service.PedidoService;
import com.empresa.api_level_up.service.TokenService;
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
@RequestMapping("api/v1/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con el pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    TokenService tokenService;

    @PostMapping
    @Operation(summary = "Crea un pedido", description = "Guarda un solo pedido donde el usuario no esta logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos guardados con exito!!"),
            @ApiResponse(responseCode = "500", description = "Producto no guardado")
    })
    public PedidoResponseDTO crear(@RequestBody PedidoRequestDTO body) {

        return pedidoService.crearPedido(body);


//        {
//            "estado": "string",
//                "cliente": {
//            "first_name_cli": "string",
//                    "last_name_cli": "string"
//        },
//            "detalle_pedidos": [
//            {
//                "id_prod": 1,
//                    "cant": 1
//            }
//  ],
//            "pago": [
//            {
//                "monto_total": 0,
//                    "fecha_pago": "24-11-2025",
//                    "metodo_pago": "string"
//            }
//  ]
//        }

    }

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Obtiene una lista con todos los pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
    })
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        List<PedidoResponseDTO> pedidos = pedidoService.listarPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Elimina por ID", description = "Elimina por ID un solo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
    })
    public PedidoResponseDTO deletePedidoById(@PathVariable Long id) {
        return pedidoService.deletePedidoById(id);
    }

    @DeleteMapping
    @Operation(summary = "Eliminar todo", description = "Elimina todos los pedidos de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
    })
    public String deleteAllPedidos() {
        return pedidoService.DeleteAllPedidos();
    }

    @GetMapping("/{token}")
    @Operation(summary = "Buscar por token", description = "Obtiene detalles de un pedido por token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
    })
    public ResponseEntity<List<PedidoResponseDTO.PedidosDTOs>> getDetallePedidos(@PathVariable String token) {

        Long id_cli = tokenService.findIdClientByToken(token);
        List<PedidoResponseDTO.PedidosDTOs> ped = pedidoService.findPedidoByCliente(id_cli);
        if(ped.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ped);
    }

    @PostMapping("/pedConLogin")
    @Operation(summary = "Crear pedido logueado", description = "Un usuario registrado en la plataforma realiza un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados")
    })
    public ResponseEntity<String> crearPedidoConLogin(@RequestBody PedidoRequestDTO.PedidoConLogin body) {
        String mensaje = pedidoService.crearPedidoConLogin(body);
        if (mensaje.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);

//        {
//            "estado": "pendiente",
//
//                "clienteEmail": "fel.urtubia@duocuc.cl",
//
//                "detalle_pedidos": [
//            {"id_prod": 1, "cant": 1},
//            {"id_prod": 2, "cant": 1}
//    ],
//            "pago": [
//            {
//                "monto_total": 3000,
//                    "fecha_pago": "23-11-2025",
//                    "metodo_pago": "debito"
//            }
//    ]
//        }

    }

}
