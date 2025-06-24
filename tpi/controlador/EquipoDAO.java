package com.mycompany.tpi.controlador;

import com.mycompany.tpi.modelo.Equipo;
import java.sql.*;
import java.util.*;

//Clase para gestionar los datos de los equipos en la base de datos
public class EquipoDAO {

    public List<Equipo> obtenerTodos() {
        List<Equipo> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM equipos ORDER BY posicion_liga_anterior ASC")) {
          //Consultamos los datos de los equipos

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int posicion = rs.getInt("posicion_liga_anterior");
                lista.add(new Equipo(nombre, posicion));
              //Guardamos los datos de los equipos en una lista para luego trabajar con ellos
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener equipos: " + e.getMessage());
        }
        return lista;
    }
    //Metodo para guardar los datos de los equipos en la base de datos
    public void guardar(Equipo equipo) {
        String sql = "INSERT INTO equipos (nombre, posicion_liga_anterior) VALUES (?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, equipo.getNombre());
            ps.setInt(2, equipo.getPosicionLigaAnterior());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar equipo: " + e.getMessage());
        }
    }
    //Metodo para borrar los datos de la base de datos
    public void eliminarTodos() {
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM equipos");
        } catch (SQLException e) {
            System.out.println("Error al eliminar equipos: " + e.getMessage());
        }
    }
}
