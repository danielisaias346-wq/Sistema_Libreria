package com.sistema.Libreria.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private Integer idLibro;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "autor")
    private String autor;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "stock")
    private Integer stock;

    //Establece relacion de m-m
    @OneToMany(mappedBy = "idLibro")
    private List<Prestamo> prestamosLista;
}
