package com.empresa.api_level_up.controller;


import com.empresa.api_level_up.dto.request.ProductoRequestDTO;
import com.empresa.api_level_up.dto.response.ProductoResponseDTO;
import com.empresa.api_level_up.model.Producto;
import com.empresa.api_level_up.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ProductoResponseDTO guardarProducto(@RequestBody ProductoRequestDTO req) {
        return productoService.saveProducto(req);
    }

    @PostMapping("/many")
    public ResponseEntity<String> saveManyProductos(@RequestBody List<ProductoRequestDTO> req) {
        String res = productoService.saveManyProduct(req);
        if(res == null) {
            return new ResponseEntity<>("No se pudo guardar los productos", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    public List<ProductoResponseDTO> obtenerProductos() {
        return productoService.getAllProductos();
    }

}
