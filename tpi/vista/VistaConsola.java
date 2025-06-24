package com.mycompany.tpi.vista;

import com.mycompany.tpi.modelo.Equipo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    // Metodo para la carga de los equipos
    
    public List<Equipo> cargarEquipos() {
        List<Equipo> equipos = new ArrayList<>();

        System.out.println("*** Carga de Equipos ***");
        System.out.print("Cuantos equipos desea ingresar? (ej: 36): ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nombre del equipo #" + i + ": ");
            String nombre = scanner.nextLine();
            System.out.print("Posicion en la liga anterior (1 a " + cantidad + "): ");
            int posicion = scanner.nextInt();
            scanner.nextLine();
            equipos.add(new Equipo(nombre, posicion));
        }

        return equipos;
    }

    // Visualizacion del Menu
    
    public void mostrarMenu() {
        System.out.println("\n*** 🏆Champions League🏆 ***");  
        System.out.println("1. Iniciar fase de liga");
        System.out.println("2. Jugar fase actual");
        System.out.println("3. Ver resultados de fase actual");
        System.out.println("4. Ver tabla de posiciones");
        System.out.println("5. Iniciar fase eliminatoria");
        System.out.println("6. Ver campeon");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    public int leerOpcion() {
        return scanner.nextInt();
    }

    // Visualizacion del campeon del Torneo 
    
    public void mostrarCampeon(Equipo campeon) {
        if (campeon != null) {
            System.out.println("\n ¡El campeon del torneo es: " + campeon.getNombre() + "!");
        } else {
            System.out.println("No hay campeon aun.");
        }
    }

    // Opcion para cargar los equipos desde la Base de Datos
    
    public boolean deseaCargarDesdeBD() {
        System.out.print("Desea cargar los equipos desde la base de datos? (s/n): ");
        String resp = scanner.nextLine();
        return resp.equalsIgnoreCase("s");
    }

    // Mensaje de despedida 
    
    public void despedida() {
        System.out.println("Saliendo...");
    }
}
