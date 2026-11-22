package com.luciuss4.tema02.boletin1.Ejercicio6;

public class ClasificacionPuntos {

    private final int posicion;
    private final String ciclista;
    private final String equipo;
    private final int puntos;

    public ClasificacionPuntos(int posicion, String ciclista, String equipo, int puntos) {
        this.posicion = posicion;
        this.ciclista = ciclista;
        this.equipo = equipo;
        this.puntos = puntos;
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

    public int getPuntos() {
        return puntos;
    }
}