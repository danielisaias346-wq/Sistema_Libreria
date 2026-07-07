package com.sistema.Libreria.Enumeradores;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;

@Getter
public enum PrestamoEstado {

    ACTIVO,
    DEVUELTO,
    RETRASADO;


    //Constructor
    @JsonCreator
     public static PrestamoEstado estado(String valor){
         if(valor == null){
             return null;
         }
         return PrestamoEstado.valueOf(valor.toUpperCase());
     }


}
