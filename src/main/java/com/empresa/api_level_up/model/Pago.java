package com.empresa.api_level_up.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    @Column(nullable = false)
    private Double monto_total;

    @Column(nullable = false)
    private LocalDate fecha_pago;

    @Column(nullable = false)
    private String metodo_pago;

    @ManyToOne
    @JsonBackReference("pedido_pago")
    private Pedido pedido;

}
