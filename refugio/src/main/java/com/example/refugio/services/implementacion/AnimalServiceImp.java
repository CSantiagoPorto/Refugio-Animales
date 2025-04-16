package com.example.refugio.services.implementacion;

import com.example.refugio.model.Animal;
import com.example.refugio.repository.AnimalRepository;
import com.example.refugio.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Parte lógica con todo ya escrito
@Service
public class AnimalServiceImp implements AnimalService {

    @Autowired//Autoiniciallizado
    private AnimalRepository animalRepository;
    @Override
    public List<Animal> getAllAnimal() {
        return animalRepository.findAll();//Implemento el método definido en AnimalService
        //llamo a animalRepository que es quien tiene los método de JpaRepository
        //Ahora delegamos en Controller
    }

    @Override
    public Optional<Animal> buscarAnimalId(int id) {
        //Esot podría ser null, una de las soluciones que ofrece Intellij es envolver la clase en
        //un tipo de dato Optional que se usa cuando los valores pueden ser null
        //sería como cuando en Kotlin hacemos  return animalRepository?.findById(id) ;
        return animalRepository.findById(id) ;
    }

    @Override
    public Animal añadirAnimal(Animal animal) {
        return animalRepository.save(animal);//Esto se traduce a SQL de forma oculta
    }

    @Override
    public Animal actualizarAnimal(Animal animal) {
        //instancio y busco por id el animal
        //findById me devuelve un optional
        //Si no hay animal lanza la excepción
        //Si lo encuentra lo guarda en existe
        Animal existe= animalRepository.findById(animal.getId()).orElseThrow(()-> new RuntimeException("La mascota que busca no existe"));
        existe.setNombre(animal.getNombre());
        existe.setEspecie(animal.getEspecie());
        existe.setRaza(animal.getRaza());
        existe.setEdad(animal.getEdad());
        existe.setVacunado(animal.isVacunado());
        existe.setFechaIngreso(animal.getFechaIngreso());
        return animalRepository.save(existe);
    }

    @Override
    public void borrarAnimal(int id) {
      animalRepository.deleteById(id);

    }


}
