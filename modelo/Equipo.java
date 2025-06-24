package com.mycompany.tpi.modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private String nombre;
    private int puntos;
    private int golesAFavor;
    private int golesEnContra;
    private int diferenciaGoles;
    private int posicionLigaAnterior; 
    private List<Partido> historialPartidos;  

    public Equipo(String nombre, int posicionLigaAnterior) {
        this.nombre = nombre;
        this.posicionLigaAnterior = posicionLigaAnterior;
        this.historialPartidos = new ArrayList<>();
        resetearEstadisticas();   //reseteamos las estaisticas de antemano
    }

    
    public void actualizarEstadistica(int golesMarcados, int golesRecibidos) {
        golesAFavor += golesMarcados;
        golesEnContra += golesRecibidos;
        diferenciaGoles = golesAFavor - golesEnContra;

        if (golesMarcados > golesRecibidos) {
            puntos += +3;                                // no hace falta el empate
        } else if (golesMarcados == golesRecibidos) {
            puntos += 1;
        }

    }

    public void agregarPartidoAlHistorial(Partido partido) {
        historialPartidos.add(partido);

    }

    public void resetearEstadisticas() {
        puntos = 0;
        golesAFavor = 0;
        golesEnContra = 0;
        diferenciaGoles = 0;
        historialPartidos.clear();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public int getPosicionLigaAnterior() {
        return posicionLigaAnterior;
    }

    public List<Partido> getHistorialPartidos() {
        return historialPartidos;
    }

    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", puntos=" + puntos + ", golesAFavor=" + golesAFavor + ", golesEnContra=" + golesEnContra + ", diferenciaGoles=" + diferenciaGoles + ", posicionLigaAnterior=" + posicionLigaAnterior + ", historialPartidos=" + historialPartidos + '}';
    }
    

}
