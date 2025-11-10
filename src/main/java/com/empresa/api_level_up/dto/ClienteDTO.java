package com.empresa.api_level_up.dto;

import com.empresa.api_level_up.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private List<PedidoDTO> pedidosDTOs;

}
