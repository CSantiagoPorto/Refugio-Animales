package com.example.refugio.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity //Marco la entidad
public class Cuidador implements Serializable {
    @Id//Marco que va a ser la pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    private String email;
    private String telefono;
    @OneToMany(mappedBy = "cuidador")
    private List<Animal> animales;

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public Cuidador(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Cuidador() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
