package com.empresa.api_level_up.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class PedidoRequestDTO {

    public String estado;
    public ClienteDTO cliente;
    public List<ItemDTO> detalle_pedidos;
    public List<PagoRequestDTO> pago;

    public static class ClienteDTO {
        public String first_name_cli;
        public String last_name_cli;
    }

    public static class ItemDTO {
        public Long id_prod;
        public int cant;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PedidoConLogin {

        public String estado;
        public String clienteEmail;
        public List<ItemDTO> detalle_pedidos;
        public List<PagoRequestDTO> pago;

    }


}
