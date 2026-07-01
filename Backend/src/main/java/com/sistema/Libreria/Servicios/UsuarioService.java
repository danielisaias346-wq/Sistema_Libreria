package com.sistema.Libreria.Servicios;

import com.sistema.Libreria.Modelo.Usuario;
import com.sistema.Libreria.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public ResponseEntity<Void> delete(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
