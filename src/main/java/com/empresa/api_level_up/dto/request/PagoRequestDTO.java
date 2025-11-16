package com.empresa.api_level_up.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDTO {

    private Double monto_total;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fecha_pago;
    private String metodo_pago;

}
