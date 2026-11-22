package com.luciuss4.tema02.boletin1.Ejercicio4;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VelocidadDAO {

    public VelocidadCiclista velocidadMedia(int idEquipo, int idCiclista) throws SQLException {
        String sql = "SELECT c.nombre, "
                + "COUNT(r.id_etapa) AS etapas, "
                + "COALESCE(SUM(e.distancia_km), 0) AS km, "
                + "SUM(e.distancia_km) / (EXTRACT(EPOCH FROM SUM(r.tiempo)) / 3600) AS velocidad "
                + "FROM ciclistas c "
                + "LEFT JOIN resultados_etapa r ON r.id_ciclista = c.id_ciclista "
                + "AND r.estado = 'FINALIZADO' "
                + "LEFT JOIN etapas e ON e.id_etapa = r.id_etapa "
                + "WHERE c.id_ciclista = ? AND c.id_equipo = ? "
                + "GROUP BY c.id_ciclista, c.nombre";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCiclista);
            ps.setInt(2, idEquipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new VelocidadCiclista(
                            rs.getString("nombre"),
                            rs.getInt("etapas"),
                            rs.getDouble("km"),
                            rs.getDouble("velocidad"));
                }
            }
        }
        return null;
    }
}