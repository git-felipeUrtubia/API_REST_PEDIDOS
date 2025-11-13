package com.empresa.api_level_up.dto.response;

import com.empresa.api_level_up.dto.request.PedidoRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class PedidoResponseDTO {

    public Long pedido_id;
    public Long cliente_id;
    public int items;
    public List<ItemDTO> detalle = new ArrayList<>();
    public List<PagoResponseDTO> pagos = new ArrayList<>();

    public static class ItemDTO {
        public Long id_detalle;
        public Long producto_id;
        public int cant;
        public double precio; // precio unitario del producto
    }

}
