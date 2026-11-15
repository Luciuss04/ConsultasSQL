package com.luciuss4.tema02.boletin1;

import com.luciuss4.tema02.boletin1.Ejercicio1.EquipoDAO;
import com.luciuss4.tema02.boletin1.Ejercicio2.Ciclista;
import com.luciuss4.tema02.boletin1.Ejercicio2.CiclistaDAO;
import com.luciuss4.tema02.boletin1.Ejercicio3.EtapaDAO;

import java.sql.SQLException;
import java.util.List;

public class Controlador {

    private final Consola consola = new Consola();
    private final EquipoDAO equipoDAO = new EquipoDAO();
    private final CiclistaDAO ciclistaDAO = new CiclistaDAO();
    private final EtapaDAO etapaDAO = new EtapaDAO();

    public void iniciar() {
        int opcion;
        do {
            opcion = consola.mostrarMenuPrincipal();
            try {
                switch (opcion) {
                    case 1 -> listarEquipos();
                    case 2 -> ciclistasPorEquipo();
                    case 3 -> listarEtapas();
                    case 0 -> consola.mostrarMensaje("¡Hasta pronto!");
                    default -> consola.mostrarMensaje("Opción no válida.");
                }
            } catch (SQLException e) {
                consola.mostrarMensaje("Error en la base de datos: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void listarEquipos() throws SQLException {
        consola.mostrarEquipos(equipoDAO.listar());
    }

    private void ciclistasPorEquipo() throws SQLException {
        consola.mostrarEquipos(equipoDAO.listar());
        int idEquipo = consola.leerEntero("ID del equipo (0 = todos): ");

        if (idEquipo == 0) {
            consola.mostrarCiclistasAgrupados(ciclistaDAO.listarTodos());
        } else {
            List<Ciclista> ciclistas = ciclistaDAO.listarPorEquipo(idEquipo);
            if (ciclistas.isEmpty()) {
                consola.mostrarMensaje("No hay ciclistas para el equipo " + idEquipo + ".");
            } else {
                consola.mostrarCiclistas(ciclistas);
            }
        }
    }

    private void listarEtapas() throws SQLException {
        consola.mostrarEtapas(etapaDAO.listar());
        consola.mostrarResumenEtapas(etapaDAO.resumenPorTipo());
    }
}