package com.example.refugio.services.implementacion;

import com.example.refugio.model.Animal;
import com.example.refugio.model.Cuidador;
import com.example.refugio.repository.CuidadorRepository;
import com.example.refugio.services.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CuidadorServiceImp implements CuidadorService {
    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
                cuidador.getTelefono() == null || cuidador.getTelefono().isBlank() ||
                cuidador.getContrasena() == null || cuidador.getContrasena().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos son obligatorios");
        }

        Cuidador existente = cuidadorRepository.findByEmail(cuidador.getEmail());
        if (existente != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuidador con ese email");
        }

        cuidador.setContrasena(passwordEncoder.encode(cuidador.getContrasena()));

        return cuidadorRepository.save(cuidador);
    }

    @Override
    public List<Animal> obtenerAnimalesDeCuidador(int id) {
        Cuidador cuidador=cuidadorRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuidador no encontrado"));
        return cuidador.getAnimales();
    }

    @Override
    public Cuidador actualizarCuidador(Cuidador cuidador) {
        if (cuidador.getEmail() == null || cuidador.getEmail().isBlank() ||
                cuidador.getNombre() == null || cuidador.getNombre().isBlank() ||
                cuidador.getTelefono() == null || cuidador.getTelefono().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos deben estar completos");
        }
        Cuidador existe= cuidadorRepository.findById(cuidador.getId())
                .orElseThrow(()-> new ResponseStatusException (HttpStatus.NOT_FOUND, "El cuidador que busca no existe"));
        Cuidador conEseEmail = cuidadorRepository.findByEmail(cuidador.getEmail());
        if (conEseEmail != null && conEseEmail.getId() != cuidador.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuidador con ese email");
        }
        existe.setNombre(cuidador.getNombre());
        existe.setEmail(cuidador.getEmail());
        existe.setTelefono(cuidador.getTelefono());
        //Sólo se actualiza si hay nueva contraseña pero no deja dejarla en blanco y se codifica con BCrypt
        if (cuidador.getContrasena() != null && !cuidador.getContrasena().isBlank()) {
            existe.setContrasena(passwordEncoder.encode(cuidador.getContrasena()));
        }

        return cuidadorRepository.save(existe);
    }

    @Override
    public void borrarCuidador(int id) {
        cuidadorRepository.deleteById(id);
    }
    @Override
    public Cuidador loginCuidador(String email, String contrasena) {
        Cuidador cuidador = cuidadorRepository.findByEmail(email);
        if (cuidador == null || !passwordEncoder.matches(contrasena, cuidador.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
        return cuidador;
    }

}