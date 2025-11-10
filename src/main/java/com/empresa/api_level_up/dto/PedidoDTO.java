package com.empresa.api_level_up.dto;

import com.empresa.api_level_up.model.DetallePedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;
    private List<DetallePedido> detallePedidoDTO;

}
