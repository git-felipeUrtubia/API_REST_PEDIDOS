package com.empresa.api_level_up.dto.response;

import com.empresa.api_level_up.dto.request.PedidoRequestDTO;
import com.empresa.api_level_up.model.DetallePedido;
import com.empresa.api_level_up.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class PedidoResponseDTO {

    public Long pedido_id;
    public Long cliente_id;
    public String estado;
    public int items;
    public List<ItemDTO> detalle = new ArrayList<>();
    public List<PagoResponseDTO> pagos = new ArrayList<>();

    public static class ItemDTO {
        public Long producto_id;
        public int cant;
        public double precio; // precio unitario del producto
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PedidosDTOs {

        private Long id_pedido;
        private String estado;
        private DetalleResponseDTO detalle;

    }

}
