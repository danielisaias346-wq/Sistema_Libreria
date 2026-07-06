package com.sistema.Libreria.Controlador;

import com.sistema.Libreria.Modelo.Usuario;
import com.sistema.Libreria.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/crear")
    public Usuario create(@RequestBody Usuario usuario) {
        return service.create(usuario);
    }

    @GetMapping("/lista")
    public List<Usuario> read() {
        return service.read();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return service.update(id,usuario);
    }

    @PatchMapping("/buscarId/{id}")
    public Usuario buscarId(@PathVariable Integer id){
        return service.buscarUserId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void delete(@PathVariable Integer id ){
        service.delete(id);
    }
}
