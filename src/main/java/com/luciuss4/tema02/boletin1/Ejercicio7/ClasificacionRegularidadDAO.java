package com.luciuss4.tema02.boletin1.Ejercicio7;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;
import com.luciuss4.tema02.boletin1.Ejercicio6.ClasificacionPuntos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionRegularidadDAO {

    public List<ClasificacionPuntos> clasificacion() throws SQLException {
        String sql = "SELECT RANK() OVER (ORDER BY SUM(p.puntos) DESC) AS posicion, "
                + "c.nombre AS ciclista, "
                + "e.nombre AS equipo, "
                + "SUM(p.puntos) AS puntos "
                + "FROM ("
                + "SELECT id_ciclista, puntos FROM puntos_meta "
                + "UNION ALL "
                + "SELECT id_ciclista, puntos FROM resultados_sprint"
                + ") p "
                + "JOIN ciclistas c ON c.id_ciclista = p.id_ciclista "
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