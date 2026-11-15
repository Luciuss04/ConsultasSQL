package com.luciuss4.tema02.boletin1.Ejercicio1;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    public List<Equipo> listar() throws SQLException {
        String sql = "SELECT id_equipo, nombre, pais FROM equipos ORDER BY id_equipo";
        List<Equipo> equipos = new ArrayList<>();

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                equipos.add(new Equipo(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais")));
            }
        }
        return equipos;
    }
}