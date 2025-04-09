package com.example.refugio.services;

import com.example.refugio.model.Animal;

import java.util.List;
import java.util.Optional;

//Aquí van los elementos lógicos con los métodos que transaccionan contra base de datos
//Es una interface, así que aquí sólo van las firmas
//@Service
public interface AnimalService {
    List<Animal>getAllAnimal();
    Optional<Animal> buscarAnimalId(int id);
    Animal añadirAnimal(Animal animal);//Aquí solo se añade el método no se implementa
    Animal actualizarAnimal(Animal animal);
}
