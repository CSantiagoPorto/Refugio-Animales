package com.example.refugio.controller;

import com.example.refugio.model.Animal;
import com.example.refugio.model.Cuidador;
import com.example.refugio.services.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuidadores")
public class CuidadorController {
    @Autowired
    public CuidadorService cuidadorService;


//Usamos @RequesBody porque vamos a recoger un objeto entero que se manda en el cuerpo
@PostMapping
public ResponseEntity<Cuidador> añadirCuidador(@RequestBody Cuidador cuidador) {
    Cuidador nuevo = cuidadorService.añadirCuidador(cuidador);
    return ResponseEntity.ok(nuevo);
}



    @GetMapping

    public ResponseEntity<List>obtenerTodosCuidadores(){
        return ResponseEntity.ok(cuidadorService.obternerTodosCuidadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuidador>buscarCuidadorId(@PathVariable int id){
        return cuidadorService.buscarCuidadorId(id)
                .map(cuidador -> ResponseEntity.ok(cuidador))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}/animales")
    public ResponseEntity<List<Animal>>obtenerAnimalesDeCuidador(@PathVariable int id){
        List<Animal>animal=cuidadorService.obtenerAnimalesDeCuidador(id);
        return ResponseEntity.ok(animal);

        //Aquí no es como conOptional que dedvuelvo unobjeto. Tengo que especificarle que es un List con objetos de
        //la clase Animal
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuidador>actualizarCuidador(@PathVariable int id, @RequestBody Cuidador cuidador){
        return ResponseEntity.ok(cuidadorService.actualizarCuidador(cuidador) );
        //Tengo el método en imp, así que aquí sólo llamo a la interfaz
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>borrarCuidador(@PathVariable int id){
        cuidadorService.buscarCuidadorId(id)//Primero buscamos
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuidador no encontrado"));
        cuidadorService.borrarCuidador(id);
        return ResponseEntity.noContent().build();
        //Esot dice que la operación se ha realizado con éxito pero que no tiene nada que devolver
        //build() es imprescindible para construir el ResponseEntity sin cuerpo

    }
    @PostMapping("/login")
    public ResponseEntity<Cuidador> login(@RequestBody Map<String, String> datosLogin) {
        String email = datosLogin.get("email");
        String contrasena = datosLogin.get("contrasena");
        Cuidador cuidador = cuidadorService.loginCuidador(email, contrasena);
        return ResponseEntity.ok(cuidador);
    }


}
