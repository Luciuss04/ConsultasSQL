package com.luciuss4.tema02.boletin1.Ejercicio3;

public class ResumenTipo {

    private final String tipo;
    private final int numEtapas;
    private final double kmTotales;

    public ResumenTipo(String tipo, int numEtapas, double kmTotales) {
        this.tipo = tipo;
        this.numEtapas = numEtapas;
        this.kmTotales = kmTotales;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumEtapas() {
        return numEtapas;
    }

    public double getKmTotales() {
        return kmTotales;
    }
}