package com.luciuss4.tema02.boletin1.Ejercicio6;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionMontanaDAO {

    public List<ClasificacionPuntos> clasificacion() throws SQLException {
        String sql = "SELECT RANK() OVER (ORDER BY SUM(rp.puntos) DESC) AS posicion, "
                + "c.nombre AS ciclista, "
                + "e.nombre AS equipo, "
                + "SUM(rp.puntos) AS puntos "
                + "FROM resultados_puerto rp "
                + "JOIN ciclistas c ON c.id_ciclista = rp.id_ciclista "
                + "JOIN equipos e ON e.id_equipo = c.id_equipo "
                + "GROUP BY c.id_ciclista, c.nombre, e.nombre "
                + "ORDER BY puntos DESC, c.nombre";
        List<ClasificacionPuntos> clasificacion = new ArrayList<>();

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clasificacion.add(new ClasificacionPuntos(
                        rs.getInt("posicion"),
                        rs.getString("ciclista"),
                        rs.getString("equipo"),
                        rs.getInt("puntos")));
            }
        }
        return clasificacion;
    }
}