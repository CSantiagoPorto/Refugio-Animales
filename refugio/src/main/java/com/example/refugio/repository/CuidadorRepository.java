package com.example.refugio.repository;

import com.example.refugio.model.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadorRepository extends JpaRepository<Cuidador, Integer> {
    Cuidador findByEmail(String email); // Necesito hacer este método específico



}
