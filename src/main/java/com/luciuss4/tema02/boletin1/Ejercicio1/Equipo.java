package com.luciuss4.tema02.boletin1.Ejercicio1;

public class Equipo {

    private final int id;
    private final String nombre;
    private final String pais;

    public Equipo(int id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
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

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + pais + ")";
    }
}