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
    private String descProducto;
    private Double precioProducto;
    private String posterProducto;
    private String catProducto;
    private int stockProducto;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class ProductoDTO {
        private String nomProducto;
        private double precioProducto;
        private String catProducto;
    }


}
