package com.luciuss4.tema02.boletin1.Ejercicio2;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CiclistaDAO {

    public List<Ciclista> listarPorEquipo(int idEquipo) throws SQLException {
        String sql = "SELECT c.id_ciclista, c.nombre, c.pais, c.fecha_nac, e.nombre AS equipo "
                + "FROM ciclistas c "
                + "JOIN equipos e ON e.id_equipo = c.id_equipo "
                + "WHERE c.id_equipo = ? "
                + "ORDER BY c.nombre";
        List<Ciclista> ciclistas = new ArrayList<>();

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEquipo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ciclistas.add(crearCiclista(rs));
                }
            }
        }
        return ciclistas;
    }

    public List<Ciclista> listarTodos() throws SQLException {
        String sql = "SELECT c.id_ciclista, c.nombre, c.pais, c.fecha_nac, e.nombre AS equipo "
                + "FROM ciclistas c "
                + "JOIN equipos e ON e.id_equipo = c.id_equipo "
                + "ORDER BY e.nombre, c.nombre";
        List<Ciclista> ciclistas = new ArrayList<>();

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ciclistas.add(crearCiclista(rs));
            }
        }
        return ciclistas;
    }

    private Ciclista crearCiclista(ResultSet rs) throws SQLException {
        return new Ciclista(
                rs.getInt("id_ciclista"),
                rs.getString("nombre"),
                rs.getString("pais"),
                rs.getObject("fecha_nac", LocalDate.class),
                rs.getString("equipo"));
    }
}