package com.example.refugio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.io.Serializable;

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
    @ManyToOne
    @JoinColumn(name="cuidador_id")

    private Cuidador cuidador;



    public Animal() {
    }

    public Animal(String nombre, String especie, String raza, int edad, boolean vacunado, Date fechaIngreso) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.vacunado = vacunado;
        this.fechaIngreso = fechaIngreso;
    }


    public Animal(String raza, String especie, String nombre) {
        this.raza = raza;
        this.especie = especie;
        this.nombre = nombre;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isVacunado() {
        return vacunado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
