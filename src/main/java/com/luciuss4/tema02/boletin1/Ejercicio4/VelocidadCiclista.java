package com.luciuss4.tema02.boletin1.Ejercicio4;

public class VelocidadCiclista {

    private final String nombre;
    private final int etapas;
    private final double km;
    private final double velocidadMedia;

    public VelocidadCiclista(String nombre, int etapas, double km, double velocidadMedia) {
        this.nombre = nombre;
        this.etapas = etapas;
        this.km = km;
        this.velocidadMedia = velocidadMedia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEtapas() {
        return etapas;
    }

    public double getKm() {
        return km;
    }

    public double getVelocidadMedia() {
        return velocidadMedia;
    }
}