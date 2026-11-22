package com.luciuss4.tema02.boletin1;

import com.luciuss4.tema02.boletin1.Ejercicio1.EquipoDAO;
import com.luciuss4.tema02.boletin1.Ejercicio2.Ciclista;
import com.luciuss4.tema02.boletin1.Ejercicio2.CiclistaDAO;
import com.luciuss4.tema02.boletin1.Ejercicio3.EtapaDAO;
import com.luciuss4.tema02.boletin1.Ejercicio4.VelocidadCiclista;
import com.luciuss4.tema02.boletin1.Ejercicio4.VelocidadDAO;
import com.luciuss4.tema02.boletin1.Ejercicio5.ClasificacionEtapa;
import com.luciuss4.tema02.boletin1.Ejercicio5.ClasificacionEtapaDAO;
import com.luciuss4.tema02.boletin1.Ejercicio6.ClasificacionMontanaDAO;
import com.luciuss4.tema02.boletin1.Ejercicio7.ClasificacionRegularidadDAO;

import java.sql.SQLException;
import java.util.List;

public class Controlador {

    private final Consola consola = new Consola();
    private final EquipoDAO equipoDAO = new EquipoDAO();
    private final CiclistaDAO ciclistaDAO = new CiclistaDAO();
    private final EtapaDAO etapaDAO = new EtapaDAO();
    private final VelocidadDAO velocidadDAO = new VelocidadDAO();
    private final ClasificacionEtapaDAO clasificacionEtapaDAO = new ClasificacionEtapaDAO();
    private final ClasificacionMontanaDAO clasificacionMontanaDAO = new ClasificacionMontanaDAO();
    private final ClasificacionRegularidadDAO clasificacionRegularidadDAO = new ClasificacionRegularidadDAO();

    public void iniciar() {
        int opcion;
        do {
            opcion = consola.mostrarMenuPrincipal();
            try {
                switch (opcion) {
                    case 1 -> listarEquipos();
                    case 2 -> ciclistasPorEquipo();
                    case 3 -> listarEtapas();
                    case 4 -> velocidadMedia();
                    case 5 -> clasificacionEtapa();
                    case 6 -> clasificacionMontana();
                    case 7 -> clasificacionRegularidad();
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

    private void velocidadMedia() throws SQLException {
        consola.mostrarEquipos(equipoDAO.listar());
        int idEquipo = consola.leerEntero("ID del equipo: ");

        List<Ciclista> ciclistas = ciclistaDAO.listarPorEquipo(idEquipo);
        if (ciclistas.isEmpty()) {
            consola.mostrarMensaje("No hay ciclistas para el equipo " + idEquipo + ".");
            return;
        }
        consola.mostrarCiclistas(ciclistas);
        int idCiclista = consola.leerEntero("ID del ciclista: ");

        VelocidadCiclista v = velocidadDAO.velocidadMedia(idEquipo, idCiclista);
        if (v == null) {
            consola.mostrarMensaje("El ciclista " + idCiclista + " no pertenece al equipo " + idEquipo + ".");
        } else {
            consola.mostrarVelocidad(v);
        }
    }

    private void clasificacionEtapa() throws SQLException {
        consola.mostrarEtapas(etapaDAO.listar());
        int idEtapa = consola.leerEntero("ID de la etapa: ");

        List<ClasificacionEtapa> clasificacion = clasificacionEtapaDAO.clasificacion(idEtapa);
        if (clasificacion.isEmpty()) {
            consola.mostrarMensaje("No hay resultados para la etapa " + idEtapa + ".");
        } else {
            consola.mostrarClasificacionEtapa(idEtapa, clasificacion);
        }
    }

    private void clasificacionMontana() throws SQLException {
        consola.mostrarClasificacionPuntos(
                "Clasificación de la montaña (maillot de lunares)",
                clasificacionMontanaDAO.clasificacion());
    }

    private void clasificacionRegularidad() throws SQLException {
        consola.mostrarClasificacionPuntos(
                "Clasificación de la regularidad (maillot verde)",
                clasificacionRegularidadDAO.clasificacion());
    }
}