package com.sistema.Libreria.Servicios;

import com.sistema.Libreria.Modelo.Usuario;
import com.sistema.Libreria.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    //Metodos crud

    public Usuario create(Usuario usuario) {

        return repo.save(usuario);
    }

    public List<Usuario> read() {
        return repo.findAll();
    }

    public ResponseEntity<Usuario> update(Integer id, Usuario usuario) {
        return repo.findById(id).map(
                usuarioExistente -> {
                    usuarioExistente.setNombre(usuario.getNombre());
                    usuarioExistente.setEmail(usuario.getEmail());
                    return ResponseEntity.ok(usuarioExistente);
                }
        ).orElse(ResponseEntity.notFound().build());


    }

    public Usuario buscarUserId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Error no se encontró el usuario " + id));
    }

    public void delete(Integer id) {
           this.buscarUserId(id);
           repo.deleteById(id);

    }




}
