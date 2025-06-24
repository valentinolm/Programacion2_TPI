/*
 *  Manejar la lista de equipos participantes.

    Controlar la fase actual (liga o eliminatoria).

    Generar los partidos por fase.

    Avanzar de fase según corresponda.

    Mostrar la tabla de posiciones (al final de la fase liga).

    Administrar el progreso del torneo.
 * 
 */
package com.mycompany.tpi.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Torneo {

    private List<Equipo> equipos;
    private Fase faseActual;
    private int numeroFaseEliminatoria; // para nombrar: 16avos, 8vos, etc.

    public Torneo(List<Equipo> equipos) {
        this.equipos = equipos;
        this.numeroFaseEliminatoria = 0;
    }

    public void iniciarFaseLiga() {
        faseActual = new FaseLiga();
        faseActual.generarCruces(equipos);
    }

    public void jugarFaseActual() {
        if (faseActual != null) {
            faseActual.avanzar();
        }
    }

    public void mostrarResultadosFase() {
        if (faseActual != null) {
            faseActual.mostrarResultados();
        }
    }

    public void mostrarTablaDePosiciones() {
        // Ordenar por puntos, diferencia de goles, goles a favor
        equipos.sort(Comparator
                .comparingInt(Equipo::getPuntos).reversed()
                .thenComparingInt(Equipo::getDiferenciaGoles).reversed()
                .thenComparingInt(Equipo::getGolesAFavor).reversed());

        System.out.println("\n=== TABLA DE POSICIONES ===");
        int pos = 1;
        for (Equipo equipo : equipos) {
            System.out.println(pos + ". " + equipo);
            pos++;
        }
    }

    public String getNombreFaseActual() {
        return faseActual != null ? faseActual.getNombreFase() : "Sin fase";
    }

    public void iniciarFaseEliminatoria() {
        numeroFaseEliminatoria++;

        String nombreFase;
        switch (equipos.size()) {
            case 36:
                nombreFase = "Dieciseisavos de Final";
                break;
            case 18:
                nombreFase = "Octavos de Final";
                break;
            case 9:
                nombreFase = "Cuartos de Final";
                break;
            case 5:
                nombreFase = "Semifinales";
                break;
            case 3:
                nombreFase = "Final";
                break;
            default:
                nombreFase = "Eliminatoria";
        }

        faseActual = new FaseEliminatoria(nombreFase);
        // Para la primera eliminatoria usamos tabla final
        faseActual.generarCruces(new ArrayList<>(equipos));
    }

    public void actualizarEquiposPostEliminacion() {
        List<Equipo> ganadores = new ArrayList<>();
        for (Partido partido : faseActual.getPartidos()) {
            if (partido.getGolesLocal() > partido.getGolesVisitante()) {
                ganadores.add(partido.getEquipoLocal());
            } else {
                ganadores.add(partido.getEquipoVisitante());
            }
        }
        equipos = ganadores;
    }

    public boolean torneoFinalizado() {
        return equipos.size() <= 1;
    }

    public Equipo obtenerCampeon() {
        return equipos.isEmpty() ? null : equipos.get(0);
    }

}
