package com.empresa.api_level_up.dto;

import com.empresa.api_level_up.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {

    private Long id;
    private List<Producto> productoDTO;

}
