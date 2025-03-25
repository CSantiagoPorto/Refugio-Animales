package com.example.refugio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;


import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "animales")

public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String especie;
    @Column
    private String raza;
    @Column
    private int edad;
    @Column
    private  boolean vacunado;
    @Column(name="fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public Animal(String raza, String especie, String nombre) {
        this.raza = raza;
        this.especie = especie;
        this.nombre = nombre;
    }
}
