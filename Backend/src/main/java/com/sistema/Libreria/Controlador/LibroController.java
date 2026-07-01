package com.sistema.Libreria.Controlador;

import com.sistema.Libreria.Modelo.Libro;
import com.sistema.Libreria.Servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend
public class LibroController {

    //Importar metodos crud
    @Autowired
    private LibroService service;

    @PostMapping("/crear")
    public Libro create(@RequestBody Libro libro){
        return service.create(libro);
    }

    @GetMapping("/lista")
    public List<Libro> lista(){
        return service.read();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Libro> actualizarAll(@PathVariable int id,@RequestBody Libro libro){
        return service.updateAll(id,libro);
    }

 /*   @PutMapping("/actualizarEstado/{id}")
    public ResponseEntity<Libro> actualizarEstado(@PathVariable int id,@RequestBody Libro libro){
        return service.updateState(id,libro);
    }*/

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @GetMapping("/buscarId/{id}")
    public Libro buscarId(@PathVariable Integer id){
         return service.buscarId(id);
    }
}
