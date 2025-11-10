package com.empresa.api_level_up.controller;


import com.empresa.api_level_up.model.Producto;
import com.empresa.api_level_up.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
        String mensaje = productoService.saveProducto(producto);
        if (mensaje.equals("")) {
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

}
