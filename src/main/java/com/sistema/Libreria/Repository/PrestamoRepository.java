package com.sistema.Libreria.Repository;

import com.sistema.Libreria.Modelo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Integer> {
}
