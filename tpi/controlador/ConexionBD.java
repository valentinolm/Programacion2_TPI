package com.mycompany.tpi.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Creamos una clase exclusiva para conectar el programa a la base de datos
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/torneo_champions";
    private static final String USER = "root";
    private static final String PASSWORD = "contrasena"; 

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
