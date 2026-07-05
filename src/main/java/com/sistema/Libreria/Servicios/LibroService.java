package com.sistema.Libreria.Servicios;

import com.sistema.Libreria.Modelo.Libro;
import com.sistema.Libreria.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repo;

    public String generarIsbn() {
        String nuevoIsbn;
        boolean existe;
        do {
// Rango válido de 13 dígitos: entre 1,000,000,000,000 y 9,999,999,999,999
            Long numero = ThreadLocalRandom.current().
                    nextLong(1000000000000L, 10000000000000L);
            nuevoIsbn = String.valueOf(numero);
            existe = repo.existsByIsbn(nuevoIsbn);
        } while (existe);
        return nuevoIsbn;
    }

    //Metodos crud
    public Libro create(Libro libro) {
        String isbn = generarIsbn();
        libro.setIsbn(String.valueOf(isbn));
        return repo.save(libro);
    }

    public List<Libro> read() {
        return repo.findAll();
    }

    public ResponseEntity<Libro> updateAll(Integer id, Libro libro) {
        return repo.findById(id).
                map(productoExistente -> {
                    productoExistente.setTitulo(libro.getTitulo());
                    productoExistente.setAutor(libro.getAutor());
                    productoExistente.setStock(libro.getStock());
                    repo.save(productoExistente);
                    return ResponseEntity.ok(productoExistente);
                }).orElse(ResponseEntity.notFound().build());
    }

    //Metodo para modificar el estado
   /* public ResponseEntity<Libro> updateState(Integer id, Libro libro){
        return repo.findById(id).map(
                libroExistente -> {
                    libroExistente.setPrestamosLista(libro.getPrestamosLista());
                    return ResponseEntity.ok(libroExistente);
                }
        ).orElse(ResponseEntity.notFound().build());
    }*/

    public ResponseEntity<Void> delete(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();//Emite confirmacion 204
        }
        return ResponseEntity.notFound().build();//Emite error 404

    }

    public Libro buscarId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Error al buscar el libro con el id " + id));

    }
}
