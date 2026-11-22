package com.luciuss4.tema02.boletin1.Ejercicio8;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;
import com.luciuss4.tema02.boletin1.Ejercicio5.ClasificacionEtapa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionGeneralDAO {

    public List<ClasificacionEtapa> clasificacion() throws SQLException {
        String sql = "SELECT RANK() OVER (ORDER BY SUM(r.tiempo)) AS posicion, "
                + "c.nombre AS ciclista, "
                + "e.nombre AS equipo, "
                + "EXTRACT(EPOCH FROM SUM(r.tiempo)) AS segundos "
                + "FROM resultados_etapa r "
                + "JOIN ciclistas c ON c.id_ciclista = r.id_ciclista "
                + "JOIN equipos e ON e.id_equipo = c.id_equipo "
                + "WHERE r.estado = 'FINALIZADO' "
                + "GROUP BY c.id_ciclista, c.nombre, e.nombre "
                + "HAVING COUNT(*) = (SELECT COUNT(*) FROM etapas) "
                + "ORDER BY segundos, c.nombre";
        List<ClasificacionEtapa> clasificacion = new ArrayList<>();

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clasificacion.add(new ClasificacionEtapa(
                        rs.getInt("posicion"),
                        rs.getString("ciclista"),
                        rs.getString("equipo"),
                        Duration.ofSeconds(rs.getLong("segundos"))));
            }
        }
        return clasificacion;
    }
}