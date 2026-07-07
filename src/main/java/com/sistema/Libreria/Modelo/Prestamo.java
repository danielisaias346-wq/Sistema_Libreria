package com.sistema.Libreria.Modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sistema.Libreria.Enumeradores.PrestamoEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestamo", nullable = false)
    private Integer idPrestamo;

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fechaPrestamo", nullable = false, updatable = false)
    private LocalDate fechaPrestamo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fechaDevolucion", nullable = false, updatable = true)
    private LocalDate fechaDevolucion;

    //Anotacion para el estado del prestamo
    @Enumerated(EnumType.STRING)
    @Column(name = "estadoActual")
    private PrestamoEstado estadoActual;

    //indica relaciones a esta tabla
    @ManyToOne
    @JsonIgnoreProperties({"prestamosLista"})
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario idUsuario;//Debe coincidir con el @Table(name = "usuario") de usuario

    @ManyToOne
    @JsonIgnoreProperties({"prestamosLista"})
    @JoinColumn(name = "idLibro", nullable = false)
    private Libro idLibro; //Debe coincidir con el @Table(name = "libro") de Libro


}
