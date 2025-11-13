package com.empresa.api_level_up.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {

    private Long id_pago;
    private Double monto_total;
    private LocalDate fecha_pago;
    private String metodo_pago;

}
