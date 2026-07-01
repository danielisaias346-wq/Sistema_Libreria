package com.sistema.Libreria.Enumeradores;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;

@Getter
public enum PrestamoEstado {

    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private String estado;

    //Constructor
    PrestamoEstado(String estado){
        this.estado=estado;
    }

    //Indica que en el JSON coloque si esta ACTIVO o INACTIVO
    @JsonGetter
    public String getEstado() {
        return estado;
    }
}
