package com.example.refugio.controller;


import com.example.refugio.model.Animal;
import com.example.refugio.repository.AnimalRepository;
import com.example.refugio.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animales")

public class AnimalController {
    @Autowired
    public AnimalService animalService;

 @PostMapping//Esto va a recibir peticiones HTTP de tipo POST (porque necesitamos añadir)
 //ResponseEntity es un tipo de dato que se usa para envolver una entidad concreta como respuesta a una petición HTTP
  //ResponseEntity incluye el objeto, el código de estado HTTP (404) y las cabecera HTTP
 //@RequestBody toma el cuerpo de la petición y lo convierte en un objeto (va a devolver un animal no un JSON ni un campo)
    public ResponseEntity<Animal> añadirAnimal(@RequestBody Animal animal){
     //Llama al servicio animalService y guarda el objeto
     Animal nuevoAnimal= animalService.añadirAnimal(animal);
     return ResponseEntity.ok(nuevoAnimal);
     //Devuelve el ok y el nuevo animal
 }

 @GetMapping
    public ResponseEntity<List>verAnimales(){//No le pongo ni @RequestBody ni @RequestParam porque quiero todos lo animales, sin filtro
     return ResponseEntity.ok(animalService.getAllAnimal());
 }

 @GetMapping("/{id}") //Buscamos por el id correspondiente
    public ResponseEntity<Animal> obtenerAnimalId(@PathVariable int id){

     return animalService.buscarAnimalId(id)
             .map(animal -> ResponseEntity.ok(animal))
             .orElseGet(()->ResponseEntity.notFound().build());
//BuscarAnimalId es un Optional y por tanto tenemos el método map()
     //Le estamos diciendo que si hay un valor del animal emita una respuesta de ok y devuelva el animal
     //Si no existe, que devuelva un 404
     

 }

 @PutMapping("/{id}")
    public ResponseEntity<Animal>actualizarAnimal(@PathVariable int id, @RequestBody Animal animal) {
     return ResponseEntity.ok(animalService.actualizarAnimal(animal));
 }
 @DeleteMapping("/{id}")
        public ResponseEntity<Void>borrarAnimal(@PathVariable int id){
        animalService.buscarAnimalId(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Animal no encontrado"));
        //esta excepción genera un código de estado y un mensaje y me ahorro el .isPresent()
        //En este caso va a devolver un 404
        animalService.borrarAnimal(id);

        return ResponseEntity.noContent().build();
        //build es necesario porque construye la respuesta y la devuelve . Sin él noContent no funciona



 }

}
