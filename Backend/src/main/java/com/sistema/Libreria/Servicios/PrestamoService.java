package com.sistema.Libreria.Servicios;

import com.sistema.Libreria.Modelo.Prestamo;
import com.sistema.Libreria.Repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository repo;

    //Metodos CRUD
    public Prestamo create(Prestamo prestamo){
    return repo.save(prestamo);
    }

    public List<Prestamo> read(){
        return repo.findAll();
    }
 //Metodo para actualizar el estado del prestamo
    public ResponseEntity<Prestamo> updateEstado(Integer id,Prestamo prestamo){
        return repo.findById(id).map(prestamoExistente -> {
            prestamoExistente.setEstadoActual(prestamo.getEstadoActual());
            return ResponseEntity.ok(prestamoExistente);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> delete(Integer id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
