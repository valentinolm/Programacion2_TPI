package com.mycompany.tpi.modelo;

import java.util.ArrayList;
import java.util.List;

public class FaseEliminatoria extends Fase {

    public FaseEliminatoria(String nombreFase) {
        super(nombreFase, new ArrayList<>());
    }

    @Override
    public void generarCruces(List<Equipo> equiposOrdenados) { 
        int n = equiposOrdenados.size();
        for (int i = 0; i < n / 2; i++) {
            Equipo local = equiposOrdenados.get(i); // mejor posicionado
            Equipo visitante = equiposOrdenados.get(n - 1 - i);
            partidos.add(new Partido(local, visitante));
        }
    }
}

