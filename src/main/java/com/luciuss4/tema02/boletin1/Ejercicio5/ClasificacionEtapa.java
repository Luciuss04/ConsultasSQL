package com.luciuss4.tema02.boletin1.Ejercicio5;

import java.time.Duration;

public class ClasificacionEtapa {

    private final int posicion;
    private final String ciclista;
    private final String equipo;
    private final Duration tiempo;

    public ClasificacionEtapa(int posicion, String ciclista, String equipo, Duration tiempo) {
        this.posicion = posicion;
        this.ciclista = ciclista;
        this.equipo = equipo;
        this.tiempo = tiempo;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getCiclista() {
        return ciclista;
    }

    public String getEquipo() {
        return equipo;
    }

    public Duration getTiempo() {
        return tiempo;
    }
}