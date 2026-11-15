package com.luciuss4.tema02.boletin1.Ejercicio3;

import java.time.LocalDate;

public class Etapa {

    private final int id;
    private final String tipo;
    private final LocalDate fecha;
    private final String salida;
    private final String llegada;
    private final double km;

    public Etapa(int id, String tipo, LocalDate fecha, String salida, String llegada, double km) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.salida = salida;
        this.llegada = llegada;
        this.km = km;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getSalida() {
        return salida;
    }

    public String getLlegada() {
        return llegada;
    }

    public double getKm() {
        return km;
    }
}