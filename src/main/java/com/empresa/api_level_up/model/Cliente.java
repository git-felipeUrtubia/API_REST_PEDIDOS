package com.empresa.api_level_up.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cli;

    @Column(nullable = false)
    private String first_name_cli;

    @Column(nullable = false)
    private String last_name_cli;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

}
