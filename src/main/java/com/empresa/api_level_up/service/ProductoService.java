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
        producto.setDesc_prod(req.getDescProducto());
        producto.setPrec_prod(req.getPrecioProducto());
        producto.setPoster_prod(req.getPosterProducto());
        producto.setCat_prod(req.getCatProducto());
        producto.setStock_prod(req.getStockProducto());
        productoRepository.save(producto);

        ProductoResponseDTO res = new ProductoResponseDTO();
        res.setNomProducto(req.getNomProducto());
        res.setDescProducto(req.getDescProducto());
        res.setPrecioProducto(req.getPrecioProducto());
        res.setPosterProducto(req.getPosterProducto());
        res.setCatProducto(req.getCatProducto());
        res.setStockProducto(req.getStockProducto());
        return res;
    }

    public List<ProductoResponseDTO> getAllProductos() {

        List<Producto> productos = productoRepository.findAll();

        List<ProductoResponseDTO> res = new ArrayList<>();

        for (Producto prod : productos) {

            ProductoResponseDTO resDTO = new ProductoResponseDTO();

            resDTO.setId_producto(prod.getId_prod());
            resDTO.setNomProducto(prod.getNom_prod());
            resDTO.setDescProducto(prod.getDesc_prod());
            resDTO.setPrecioProducto(prod.getPrec_prod());
            resDTO.setPosterProducto(prod.getPoster_prod());
            resDTO.setCatProducto(prod.getCat_prod());
            resDTO.setStockProducto(prod.getStock_prod());

            res.add(resDTO);
        }
        return res;
    }

    public String saveManyProduct(List<ProductoRequestDTO> req) {

        for (ProductoRequestDTO reqDTO : req) {

            Producto producto = new Producto();
            producto.setNom_prod(reqDTO.getNomProducto());
            producto.setDesc_prod(reqDTO.getDescProducto());
            producto.setPrec_prod(reqDTO.getPrecioProducto());
            producto.setPoster_prod(reqDTO.getPosterProducto());
            producto.setCat_prod(reqDTO.getCatProducto());
            producto.setStock_prod(reqDTO.getStockProducto());
            productoRepository.save(producto);

        }
        return "Productos guardados!!";

    }


}
