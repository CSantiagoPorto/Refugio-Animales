package com.example.refugio.repository;

import com.example.refugio.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//El objetivo de esta clase es acceder a la base de datos sin escribir las consultas SQL
public interface AnimalRepository  extends JpaRepository <Animal,Integer> {
//Esta interfaz extiende de JpaRepository, lo que me va a permitir hacer CRUD sin picar el código correspondiente
//Le indico que las entidades que va a manejar son de tipo Animal y que su ID correspondiente es un Integer
    //Nos da un montón de métodos ya hechos, así que aquí sólo creamos los métodos que se salen un poco de lo normal y vayamos a utilizar
}
