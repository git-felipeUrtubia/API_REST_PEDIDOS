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
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ped;

    @Column(nullable = false)
    private Integer num_ped;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalle_pedidos;

    @OneToMany(mappedBy = "pedido")
    private List<Pago> pagos;

}
