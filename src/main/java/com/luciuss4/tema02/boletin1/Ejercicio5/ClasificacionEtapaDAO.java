package com.luciuss4.tema02.boletin1.Ejercicio5;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionEtapaDAO {

    public List<ClasificacionEtapa> clasificacion(int idEtapa) throws SQLException {
        String sql = "SELECT RANK() OVER (ORDER BY r.tiempo) AS posicion, "
                + "c.nombre AS ciclista, "
                + "e.nombre AS equipo, "
                + "EXTRACT(EPOCH FROM r.tiempo) AS segundos "
                + "FROM resultados_etapa r "
                + "JOIN ciclistas c ON c.id_ciclista = r.id_ciclista "
                + "JOIN equipos e ON e.id_equipo = c.id_equipo "
                + "WHERE r.id_etapa = ? AND r.estado = 'FINALIZADO' "
                + "ORDER BY r.tiempo, c.nombre";
        List<ClasificacionEtapa> clasificacion = new ArrayList<>();

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEtapa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    clasificacion.add(new ClasificacionEtapa(
                            rs.getInt("posicion"),
                            rs.getString("ciclista"),
                            rs.getString("equipo"),
                            Duration.ofSeconds(rs.getLong("segundos"))));
                }
            }
        }
        return clasificacion;
    }
}