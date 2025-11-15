package com.empresa.api_level_up.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prod;

    @Column(nullable = false)
    private String nom_prod;

    @Column(nullable = false)
    private String desc_prod;

    @Column(nullable = false)
    private Double prec_prod;

    @Column(nullable = false)
    private String poster_prod;

    @Column(nullable = false)
    private String cat_prod;

    @Column()
    private int stock_prod;

    @OneToMany(mappedBy = "producto")
    @JsonBackReference("detalle-producto")
    private List<DetallePedido> detalle_pedidos;

}
