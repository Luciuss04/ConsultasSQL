package com.luciuss4.tema02.boletin1.Ejercicio9;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionEquiposDAO {

    public List<ClasificacionEquipo> clasificacion() throws SQLException {
        String sql = "SELECT RANK() OVER (ORDER BY SUM(t.tiempo)) AS posicion, "
                + "e.nombre AS equipo, "
                + "EXTRACT(EPOCH FROM SUM(t.tiempo)) AS segundos "
                + "FROM ("
                + "SELECT c.id_equipo, r.tiempo, "
                + "ROW_NUMBER() OVER (PARTITION BY r.id_etapa, c.id_equipo ORDER BY r.tiempo) AS puesto "
                + "FROM resultados_etapa r "
                + "JOIN ciclistas c ON c.id_ciclista = r.id_ciclista "
                + "WHERE r.estado = 'FINALIZADO'"
                + ") t "
                + "JOIN equipos e ON e.id_equipo = t.id_equipo "
                + "WHERE t.puesto <= 3 "
                + "GROUP BY e.id_equipo, e.nombre "
                + "ORDER BY segundos, e.nombre";
        List<ClasificacionEquipo> clasificacion = new ArrayList<>();

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clasificacion.add(new ClasificacionEquipo(
                        rs.getInt("posicion"),
                        rs.getString("equipo"),
                        Duration.ofSeconds(rs.getLong("segundos"))));
            }
        }
        return clasificacion;
    }
}
