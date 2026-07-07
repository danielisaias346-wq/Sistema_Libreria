package com.sistema.Libreria.Modelo;

import com.sistema.Libreria.Enumeradores.PrestamoEstado;
import lombok.Data;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Getter
public class PrestamoDto  {
    //Al momento de probar los endpoints en postman, en el JSON tienen que estar los atributos con el mismo nombre que esta clase
    private Integer idUsuario;
    private Integer idLibro = getIdLibro();
    private LocalDate fechaDevolucion ;
    private String estado;


}

