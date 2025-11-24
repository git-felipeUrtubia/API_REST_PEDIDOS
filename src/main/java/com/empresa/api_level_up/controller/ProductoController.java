package com.empresa.api_level_up.controller;


import com.empresa.api_level_up.dto.request.ProductoRequestDTO;
import com.empresa.api_level_up.dto.response.ProductoResponseDTO;
import com.empresa.api_level_up.model.Producto;
import com.empresa.api_level_up.service.ProductoService;
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
@RequestMapping("api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    @Operation(summary = "Guardar Producto", description = "Guarda un producto en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "500", description = "Producto no guardado")
    })
    public ProductoResponseDTO guardarProducto(@RequestBody ProductoRequestDTO req) {
        return productoService.saveProducto(req);
    }

    @PostMapping("/many")
    @Operation(summary = "Guardar muchos productos", description = "Guarda multiples productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos guardados con exito!!"),
            @ApiResponse(responseCode = "500", description = "Producto no guardado")
    })
    public ResponseEntity<String> saveManyProductos(@RequestBody List<ProductoRequestDTO> req) {
        String res = productoService.saveManyProduct(req);
        if(res == null) {
            return new ResponseEntity<>("No se pudo guardar los productos", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Obtiene una lista de todos los prductos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
    })
    public List<ProductoResponseDTO> obtenerProductos() {
        return productoService.getAllProductos();
    }

}
