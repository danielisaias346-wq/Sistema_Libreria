package com.sistema.Libreria.Repository;

import com.sistema.Libreria.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Integer> {

    //Devuelve true si isbn existe
    boolean existsByIsbn(String isbn);}
