package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.request.ProductoRequestDTO;
import com.empresa.api_level_up.dto.response.ProductoResponseDTO;
import com.empresa.api_level_up.model.Producto;
import com.empresa.api_level_up.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoResponseDTO saveProducto(ProductoRequestDTO req) {
        Producto producto = new Producto();
        producto.setNom_prod(req.getNomProducto());
        producto.setPrec_prod(req.getPrecioProducto());
        productoRepository.save(producto);

        ProductoResponseDTO res = new ProductoResponseDTO();
        res.setNomProducto(req.getNomProducto());
        res.setPrecioProducto(req.getPrecioProducto());
        return res;
    }

    public List<ProductoResponseDTO> getAllProductos() {

        List<Producto> productos = productoRepository.findAll();

        List<ProductoResponseDTO> res = new ArrayList<>();

        for (Producto prod : productos) {

            ProductoResponseDTO resDTO = new ProductoResponseDTO();

            resDTO.setId_producto(prod.getId_prod());
            resDTO.setNomProducto(prod.getNom_prod());
            resDTO.setPrecioProducto(prod.getPrec_prod());

            res.add(resDTO);
        }
        return res;
    }

}
