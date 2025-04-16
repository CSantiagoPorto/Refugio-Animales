package com.example.refugio.services.implementacion;

import com.example.refugio.model.Cuidador;
import com.example.refugio.repository.CuidadorRepository;
import com.example.refugio.services.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class CuidadorServiceImp implements CuidadorService {
    @Autowired
    private CuidadorRepository cuidadorRepository;
    @Override
    public Optional<Cuidador> buscarCuidadorId(int id) {
        return Optional.empty();
    }
}
