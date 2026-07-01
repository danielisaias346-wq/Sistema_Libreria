package com.sistema.Libreria.Modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema.Libreria.Enumeradores.PrestamoEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestamo",nullable = false)
    private Integer idPrestamo;

    @CreatedDate
    @Column(name = "fechaPrestamo", nullable = false, updatable = false)
    private LocalDate fechaPrestamo;

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fechaDevolucion", nullable = false, updatable = false)
    private LocalDate fechaDevolucion;

    //Anotacion para el estado del prestamo
    @Enumerated(EnumType.STRING)
    @Column(name = "estadoActual")
    private PrestamoEstado estadoActual;

    //indica relaciones a esta tabla
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario idUsuario;//Debe coincidir con el @Table(name = "usuario") de usuario

    @ManyToOne
    @JoinColumn(name = "idLibro", nullable = false)
    private Libro idLibro; //Debe coincidir con el @Table(name = "libro") de Libro


}
