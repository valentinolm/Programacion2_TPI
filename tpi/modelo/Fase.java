package com.mycompany.tpi.modelo;

import java.util.List;
import java.util.Scanner;

public abstract class Fase { // clase padre de Fase eliminatoria

    protected String nombreFase;
    protected List<Partido> partidos;

    public Fase(String nombreFase, List<Partido> partidos) {
        this.nombreFase = nombreFase;
        this.partidos = partidos;
    }

    public abstract void generarCruces(List<Equipo> equipos); // reutilizamos el metodo (polimorfismo)

    public void avanzar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***Resultados dela" + nombreFase + "***");

        for (Partido partido : partidos) {
            if (!partido.fueJugado()) {
                System.out.println("ingrese resultado para: " + partido.getEquipoLocal().getNombre() + "vs " + partido.getEquipoVisitante().getNombre());

                System.out.print("Goles " + partido.getEquipoLocal().getNombre() + ": ");
                int golesLocal = scanner.nextInt();

                System.out.print("Goles " + partido.getEquipoVisitante().getNombre() + ": ");
                int golesVisitante = scanner.nextInt();
                partido.jugar(golesLocal, golesVisitante);
            }
        }
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void mostrarResultados() {
        System.out.println("Resulados de la fase: " + nombreFase);
        for (Partido p : partidos) {
            System.out.println(p.obtenerResultado());
        }
    }

}

