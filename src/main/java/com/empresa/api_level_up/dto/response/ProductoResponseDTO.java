package com.empresa.api_level_up.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {

    private Long id_producto;
    private String nomProducto;
    private Double precioProducto;

}
