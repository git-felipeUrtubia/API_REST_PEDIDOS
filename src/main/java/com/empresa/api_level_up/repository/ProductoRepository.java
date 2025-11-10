package com.empresa.api_level_up.repository;

import com.empresa.api_level_up.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
