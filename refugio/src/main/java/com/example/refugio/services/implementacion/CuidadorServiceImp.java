package com.example.refugio.services.implementacion;

import com.example.refugio.model.Animal;
import com.example.refugio.model.Cuidador;
import com.example.refugio.repository.CuidadorRepository;
import com.example.refugio.services.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service

public class CuidadorServiceImp implements CuidadorService {
    @Autowired
    private CuidadorRepository cuidadorRepository;
    @Override
    public Optional<Cuidador> buscarCuidadorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Cuidador> obternerTodosCuidadores() {
        return cuidadorRepository.findAll();
        //Cuidador repository tiene el método ya hecho
    }

    @Override
    public Cuidador añadirCuidador(Cuidador cuidador) {
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
}
