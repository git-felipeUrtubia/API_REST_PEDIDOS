package com.empresa.api_level_up.repository;

import com.empresa.api_level_up.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
