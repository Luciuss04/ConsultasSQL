package com.luciuss4.tema02.boletin1.Ejercicio9;

import java.time.Duration;

public class ClasificacionEquipo {

    private final int posicion;
    private final String equipo;
    private final Duration tiempo;

    public ClasificacionEquipo(int posicion, String equipo, Duration tiempo) {
        this.posicion = posicion;
        this.equipo = equipo;
        this.tiempo = tiempo;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getEquipo() {
        return equipo;
    }

    public Duration getTiempo() {
        return tiempo;
    }
}