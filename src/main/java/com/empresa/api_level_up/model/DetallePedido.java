package com.empresa.api_level_up.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_pedido;

    @Column
    private int cant;

    @ManyToOne
    @JsonBackReference("pedido-detalle")
    private Pedido pedido;

    @ManyToOne
    @JsonManagedReference("detalle-producto")
    private Producto producto;
}
