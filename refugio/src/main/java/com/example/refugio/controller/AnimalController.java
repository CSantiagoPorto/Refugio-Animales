package com.example.refugio.controller;


import com.example.refugio.model.Animal;
import com.example.refugio.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Animal>obtenerAnimalId(@PathVariable int id){

     //AQUÍ HAY QUE TENER EN CUENTA QUE PUEDE DEVOLVER EL ANIMAL O UN NULO
     //QUÉ HACER SI DEVUELVE NULO?
     //HAY QUE TENERLO EN CUENTA
     
     return null;//PROVISIONAL
 }

 @PutMapping("/{id}")
    public ResponseEntity<Animal>actualizarAnimal(@PathVariable int id, @RequestBody Animal animal) {


     return ResponseEntity.ok(animalService.actualizarAnimal(animal));
 }


}
