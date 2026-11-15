package com.luciuss4.tema02.boletin1;

import com.luciuss4.tema02.boletin1.Ejercicio1.Equipo;
import com.luciuss4.tema02.boletin1.Ejercicio2.Ciclista;
import com.luciuss4.tema02.boletin1.Ejercicio3.Etapa;
import com.luciuss4.tema02.boletin1.Ejercicio3.ResumenTipo;

import java.util.List;
import java.util.Scanner;

public class Consola {

    private final Scanner teclado = new Scanner(System.in);

    public int mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("===== VUELTA ATLÁNTICA =====");
        System.out.println("1. Listar equipos");
        System.out.println("2. Ciclistas por equipo");
        System.out.println("3. Listar etapas");
        System.out.println("0. Salir");
        return leerEntero("Opción: ");
    }

    public void mostrarEquipos(List<Equipo> equipos) {
        System.out.println();
        System.out.printf("%-4s %-22s %-4s%n", "ID", "EQUIPO", "PAÍS");
        System.out.println("-".repeat(32));
        for (Equipo e : equipos) {
            System.out.printf("%-4d %-22s %-4s%n", e.getId(), e.getNombre(), e.getPais());
        }
    }

    public void mostrarCiclistas(List<Ciclista> ciclistas) {
        System.out.println();
        mostrarCabeceraCiclistas();
        for (Ciclista c : ciclistas) {
            mostrarFilaCiclista(c);
        }
    }

    public void mostrarCiclistasAgrupados(List<Ciclista> ciclistas) {
        String equipoActual = "";
        for (Ciclista c : ciclistas) {
            if (!c.getEquipo().equals(equipoActual)) {
                equipoActual = c.getEquipo();
                System.out.println();
                System.out.println("== " + equipoActual + " ==");
                mostrarCabeceraCiclistas();
            }
            mostrarFilaCiclista(c);
        }
    }

    private void mostrarCabeceraCiclistas() {
        System.out.printf("%-4s %-22s %-4s %-10s%n", "ID", "CICLISTA", "PAÍS", "NACIMIENTO");
        System.out.println("-".repeat(43));
    }

    private void mostrarFilaCiclista(Ciclista c) {
        System.out.printf("%-4d %-22s %-4s %-10s%n",
                c.getId(), c.getNombre(), c.getPais(), c.getFechaNac());
    }

    public void mostrarEtapas(List<Etapa> etapas) {
        System.out.println();
        System.out.printf("%-4s %-9s %-10s %-15s %-18s %8s%n",
                "ID", "TIPO", "FECHA", "SALIDA", "LLEGADA", "KM");
        System.out.println("-".repeat(69));
        for (Etapa e : etapas) {
            System.out.printf("%-4d %-9s %-10s %-15s %-18s %8.2f%n",
                    e.getId(), e.getTipo(), e.getFecha(), e.getSalida(), e.getLlegada(), e.getKm());
        }
    }

    public void mostrarResumenEtapas(List<ResumenTipo> resumen) {
        System.out.println();
        System.out.println("Resumen por tipo de etapa");
        System.out.printf("%-9s %7s %10s%n", "TIPO", "ETAPAS", "KM");
        System.out.println("-".repeat(28));
        for (ResumenTipo r : resumen) {
            System.out.printf("%-9s %7d %10.2f%n", r.getTipo(), r.getNumEtapas(), r.getKmTotales());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número entero.");
            }
        }
    }
}