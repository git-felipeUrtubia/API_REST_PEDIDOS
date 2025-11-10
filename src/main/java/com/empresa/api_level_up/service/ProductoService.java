package com.empresa.api_level_up.service;

import com.empresa.api_level_up.model.Producto;
import com.empresa.api_level_up.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public String saveProducto(Producto producto) {
        productoRepository.save(producto);
        return "Producto guardado con exito!";
    }

}
