package com.empresa.api_level_up.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonManagedReference("pedido-cliente")
    private List<Pedido> pedidos;

    @JsonBackReference("user-cliente")
    @OneToOne
    private User user;

}
