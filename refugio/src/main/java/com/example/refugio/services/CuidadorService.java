package com.example.refugio.services;

import com.example.refugio.model.Animal;
import com.example.refugio.model.Cuidador;
import com.example.refugio.repository.CuidadorRepository;

import java.util.List;
import java.util.Optional;

public interface CuidadorService {
    Optional<Cuidador>buscarCuidadorId(int id);//GET
    List<Cuidador>obternerTodosCuidadores();//GET
    Cuidador añadirCuidador(Cuidador cuidador);//POST
    List<Animal>obtenerAnimalesDeCuidador(int id);//GET;
    Cuidador actualizarCuidador(Cuidador cuidador);
    void borrarCuidador(int id);

}
