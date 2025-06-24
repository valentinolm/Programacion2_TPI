package com.mycompany.tpi.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class FaseLiga extends Fase {

    public FaseLiga() {
        super("Fase de Liga", new ArrayList<>());
    }

    @Override
    public void generarCruces(List<Equipo> equipos) {
        HashSet<String> encuentros = new HashSet<>();// hash set para no repetir equipos 

        
        for (Equipo equipo : equipos) {
            int localCount = 0;
            int visitanteCount = 0;
            List<Equipo> rivalesDisponibles = new ArrayList<>(equipos);
            rivalesDisponibles.remove(equipo);
            Collections.shuffle(rivalesDisponibles);// mesclamos aleatoreamente los equipos

            for (Equipo rival : rivalesDisponibles) {
                if (localCount + visitanteCount >= 8) break;
                String clave1 = equipo.getNombre() + "-" + rival.getNombre();
                String clave2 = rival.getNombre() + "-" + equipo.getNombre();

                if (!encuentros.contains(clave1) && !encuentros.contains(clave2)) {
                    if (localCount < 4) {
                        partidos.add(new Partido(equipo, rival));
                        localCount++;
                    } else if (visitanteCount < 4) {
                        partidos.add(new Partido(rival, equipo));
                        visitanteCount++;
                    }
                    encuentros.add(clave1);
                }
            }
        }
    }
}

