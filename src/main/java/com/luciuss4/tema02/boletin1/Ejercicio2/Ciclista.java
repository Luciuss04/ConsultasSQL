package com.luciuss4.tema02.boletin1.Ejercicio2;

import java.time.LocalDate;

public class Ciclista {

    private final int id;
    private final String nombre;
    private final String pais;
    private final LocalDate fechaNac;
    private final String equipo;

    public Ciclista(int id, String nombre, String pais, LocalDate fechaNac, String equipo) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaNac = fechaNac;
        this.equipo = equipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public String getEquipo() {
        return equipo;
    }
}