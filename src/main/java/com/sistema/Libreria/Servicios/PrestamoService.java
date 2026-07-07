package com.sistema.Libreria.Servicios;

import com.sistema.Libreria.Enumeradores.PrestamoEstado;
import com.sistema.Libreria.Modelo.Libro;
import com.sistema.Libreria.Modelo.Prestamo;
import com.sistema.Libreria.Modelo.PrestamoDto;
import com.sistema.Libreria.Modelo.Usuario;
import com.sistema.Libreria.Repository.LibroRepository;
import com.sistema.Libreria.Repository.PrestamoRepository;
import com.sistema.Libreria.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository repo;

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private LibroRepository libRepo;

    //Metodos CRUD
    public Prestamo create(PrestamoDto dto) throws RuntimeException {

        Usuario userDto = userRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Error no existe o no se encontro el usuario"));

        Libro libDto = libRepo.findById(dto.getIdLibro())
                .orElseThrow(() -> new RuntimeException("Error no existe o no se encontro el libro"));


        Prestamo prestamo = new Prestamo();
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        prestamo.setIdLibro(libDto);
        prestamo.setIdUsuario(userDto);
        if (dto.getEstado() == null || dto.getEstado().isBlank()) {
            prestamo.setEstadoActual(PrestamoEstado.ACTIVO);
        } else {
            try {
                prestamo.setEstadoActual(PrestamoEstado.valueOf(dto.getEstado().toUpperCase()));
            } catch (IllegalArgumentException e) {

            }
        }

        return repo.save(prestamo);

    }

    public List<Prestamo> read() {
        return repo.findAll();
    }

    //Metodo para actualizar el estado del prestamo
    @Transactional//Anotacion que soluciona el error de no modificar el estado del prestamo
    public Prestamo update(Integer id, PrestamoDto dto) throws RuntimeException {
        System.out.println(dto.getEstado());
        return repo.findById(id).map(pres -> {
            Usuario userReal = userRepo.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Error: No existe el usuario con ID: " + dto.getIdUsuario()));

            Libro libReal = libRepo.findById(dto.getIdLibro())
                    .orElseThrow(() -> new RuntimeException("Error: No existe el libro con ID: " + dto.getIdLibro()));

            pres.setIdUsuario(userReal);
            pres.setIdLibro(libReal);
            pres.setFechaDevolucion(dto.getFechaDevolucion());

            if (dto.getEstado() != null) {
                pres.setEstadoActual(PrestamoEstado.valueOf(dto.getEstado().toUpperCase()));
            }

            // 4. Guardas los cambios
            return repo.save(pres);
        }).orElseThrow(() -> new RuntimeException("Error al actualizar el prestamo, No existe el Id del prestamo: " + id));

    }

    public void delete(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }


}
