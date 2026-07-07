package com.sistema.Libreria.Controlador;

import com.sistema.Libreria.Modelo.Prestamo;
import com.sistema.Libreria.Modelo.PrestamoDto;
import com.sistema.Libreria.Servicios.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoService service;

    @PostMapping("/crear")
    public Prestamo create(@RequestBody PrestamoDto prestamo){
        return  service.create(prestamo);
    }

    @GetMapping("/lista")
    public List<Prestamo> read(){
        return service.read();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Prestamo> update(@PathVariable Integer id, @RequestBody PrestamoDto dto ){
        Prestamo prestamoActualizado = service.update(id,dto);
        return ResponseEntity.ok(prestamoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public void delete(@PathVariable Integer id){
         service.delete(id);
    }
}
