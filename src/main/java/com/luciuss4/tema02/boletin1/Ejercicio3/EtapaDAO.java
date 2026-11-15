package com.luciuss4.tema02.boletin1.Ejercicio3;

import com.luciuss4.tema02.boletin1.Ejercicio1.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EtapaDAO {

    public List<Etapa> listar() throws SQLException {
        String sql = "SELECT id_etapa, tipo, fecha, salida, llegada, distancia_km "
                + "FROM etapas "
                + "ORDER BY num_etapa";
        List<Etapa> etapas = new ArrayList<>();

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                etapas.add(new Etapa(
                        rs.getInt("id_etapa"),
                        rs.getString("tipo"),
                        rs.getObject("fecha", LocalDate.class),
                        rs.getString("salida"),
                        rs.getString("llegada"),
                        rs.getDouble("distancia_km")));
            }
        }
        return etapas;
    }

    public List<ResumenTipo> resumenPorTipo() throws SQLException {
        String sql = "SELECT tipo, COUNT(*) AS num_etapas, SUM(distancia_km) AS km_totales "
                + "FROM etapas "
                + "GROUP BY tipo "
                + "ORDER BY tipo";
        List<ResumenTipo> resumen = new ArrayList<>();

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                resumen.add(new ResumenTipo(
                        rs.getString("tipo"),
                        rs.getInt("num_etapas"),
                        rs.getDouble("km_totales")));
            }
        }
        return resumen;
    }
}