package com.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.proyecto.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}