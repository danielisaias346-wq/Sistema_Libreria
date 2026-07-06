package com.sistema.Libreria.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)//Agregado de fecha automatico
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    @Column(name = "nombre")
    private String nombre;

    //Columna sin valores nulos, es unico y tiene una longitud maxima de 150 caracteres
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    //Columna donde no se puede modificar ni dejar espacios nulos
    @CreatedDate
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    public String getFechaFormateada() {
        if(fechaRegistro == null){
            return "";
        }
        DateTimeFormatter fecha_Formateada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaRegistro.format(fecha_Formateada);
    }

    //Establece relacion de m-m
    @OneToMany(mappedBy = "idUsuario")
    private List<Prestamo> prestamosLista;

}
