package com.example.refugio.services;

import com.example.refugio.model.Cuidador;

import java.util.Optional;

public interface CuidadorService {
    Optional<Cuidador>buscarCuidadorId(int id);
}
