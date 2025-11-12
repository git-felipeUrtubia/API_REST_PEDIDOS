package com.empresa.api_level_up.dto.request;

import java.util.List;

public class PedidoRequestDTO {

    public ClienteDTO cliente;
    public List<ItemDTO> detalle_pedidos;

    public static class ClienteDTO {
        public String first_name_cli;
        public String last_name_cli;
    }

    public static class ItemDTO {
        // En el body envías SOLO el id del producto y la cantidad
        public Long id_prod;
        public int cant;
    }

}
