package com.mycompany.tpi.controlador;

import com.mycompany.tpi.modelo.Equipo;
import com.mycompany.tpi.modelo.Torneo;
import com.mycompany.tpi.vista.VistaConsola;
import java.util.List;

public class ControladorTorneo {

    private VistaConsola vista;
    private Torneo torneo;
    private boolean faseLigaFinalizada;

    public ControladorTorneo() {
        this.vista = new VistaConsola();
        this.faseLigaFinalizada = false;
    }

    public void iniciar() { // Metodo principal que inicia el programa
        EquipoDAO dao = new EquipoDAO();
        List<Equipo> equipos;

        if (vista.deseaCargarDesdeBD()) {
            equipos = dao.obtenerTodos();
            System.out.println("Se cargaron " + equipos.size() + " equipos desde la base de datos.");
        } else {
            equipos = vista.cargarEquipos();

            dao.eliminarTodos();
            for (Equipo eq : equipos) {
                dao.guardar(eq);
            }
            System.out.println("Equipos guardados en base de datos.");
        }

        this.torneo = new Torneo(equipos);

        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    private void procesarOpcion(int opcion) { // Metodo para gestionar la opcion que ingrese el usuario
        switch (opcion) {
            case 1:
                torneo.iniciarFaseLiga();
                System.out.println("Fase de liga iniciada.");
                break;

            case 2:
                torneo.jugarFaseActual();
                System.out.println("Fase jugada con exito.");
                if (!faseLigaFinalizada && torneo.getNombreFaseActual().equals("Fase de Liga")) {
                    faseLigaFinalizada = true;
                }
                break;

            case 3:
                torneo.mostrarResultadosFase();
                break;

            case 4:
                if (faseLigaFinalizada) {
                    torneo.mostrarTablaDePosiciones();
                } else {
                    System.out.println("La fase de liga aun no se ha completado.");
                }
                break;

            case 5:
                if (faseLigaFinalizada) {
                    torneo.mostrarTablaDePosiciones(); 
                    torneo.iniciarFaseEliminatoria();
                    torneo.jugarFaseActual();
                    torneo.mostrarResultadosFase();
                    torneo.actualizarEquiposPostEliminacion();

                    if (torneo.torneoFinalizado()) {
                        vista.mostrarCampeon(torneo.obtenerCampeon());
                    }
                } else {
                    System.out.println("Debe completar la fase de liga antes.");
                }
                break;

            case 6:
                vista.mostrarCampeon(torneo.obtenerCampeon());
                break;

            case 0:
                vista.despedida();
                break;

            default:
                System.out.println("Opcion no valida.");
        }
    }
}
