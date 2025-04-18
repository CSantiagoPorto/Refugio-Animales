package com.example.refugio.services.implementacion;

import com.example.refugio.model.Animal;
import com.example.refugio.model.Cuidador;
import com.example.refugio.repository.AnimalRepository;
import com.example.refugio.repository.CuidadorRepository;
import com.example.refugio.services.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service

public class CuidadorServiceImp implements CuidadorService {
    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private AnimalRepository animalRepository;



    @Override
    public Optional<Cuidador> buscarCuidadorId(int id) {
        return cuidadorRepository.findById(id);
    }

    @Override
    public List<Cuidador> obternerTodosCuidadores() {
        return cuidadorRepository.findAll();
        //Cuidador repository tiene el método ya hecho
    }
    @Override
    public Cuidador añadirCuidador(Cuidador cuidador) {
        if (cuidador.getEmail() == null || cuidador.getEmail().isBlank() ||
                cuidador.getNombre() == null || cuidador.getNombre().isBlank() ||
                cuidador.getTelefono() == null || cuidador.getTelefono().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos son obligatorios");
        }

        Cuidador existente = cuidadorRepository.findByEmail(cuidador.getEmail());
        if (existente != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuidador con ese email");
        }

        return cuidadorRepository.save(cuidador);
    }



    @Override
    public List<Animal> obtenerAnimalesDeCuidador(int id) {
        //Creo un objeto Cuidador, este tiene como atributo los animales asignados.
        //Al igualar el objeto a cuidadorRepository, cuidador pueded acceder a los métodos de JPARepository
        //Si falla, porque ese id no existe, lanza un 404
        //Si no devuelve una lista con los animales asignados
        Cuidador cuidador=cuidadorRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuidador no encontrado"));
        return cuidador.getAnimales();
    }

    @Override
    public Cuidador actualizarCuidador(Cuidador cuidador) {
        //Valido que lo campos estén cubiertos
        if (cuidador.getEmail() == null || cuidador.getEmail().isBlank() ||
                cuidador.getNombre() == null || cuidador.getNombre().isBlank() ||
                cuidador.getTelefono() == null || cuidador.getTelefono().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos deben estar completos");
        }
        //Valido que existe
        Cuidador existe= cuidadorRepository.findById(cuidador.getId())
                .orElseThrow(()-> new ResponseStatusException (HttpStatus.NOT_FOUND, "El cuidador que busca no existe"));
        //Valido que nadie me pueda editar el Cuidador para repetir email
        Cuidador conEseEmail = cuidadorRepository.findByEmail(cuidador.getEmail());
        if (conEseEmail != null && conEseEmail.getId() != cuidador.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuidador con ese email");
        }
        existe.setNombre(cuidador.getNombre());
        existe.setEmail(cuidador.getEmail());
        existe.setTelefono(cuidador.getTelefono());
        return cuidadorRepository.save(existe);
    }



    @Override
    public void borrarCuidador(int id) {
        Cuidador cuidador = cuidadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuidador no encontrado"));

        // Desasignamos el cuidador de los animales
        if (cuidador.getAnimales() != null) {
            for (Animal animal : cuidador.getAnimales()) {
                animal.setCuidador(null);
                animalRepository.save(animal);
            }
        }

        cuidadorRepository.deleteById(id);
    }

}
