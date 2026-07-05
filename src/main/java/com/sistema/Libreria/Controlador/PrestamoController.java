package com.sistema.Libreria.Controlador;

import com.sistema.Libreria.Modelo.Prestamo;
import com.sistema.Libreria.Servicios.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoService service;

    @PostMapping("/crear")
    public Prestamo create(@RequestBody Prestamo prestamo){
        return  service.create(prestamo);
    }

    @GetMapping("/lista")
    public List<Prestamo> read(){
        return service.read();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Prestamo> updateEstado(@PathVariable Integer id,@RequestBody Prestamo prestamo ){
        return service.updateEstado(id,prestamo);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
