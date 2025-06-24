package com.mycompany.tpi.modelo;

public class Partido {

    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    private boolean jugado;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.jugado = false;

    }

    //logica para jugar un partido unico a la vez
    public void jugar(int golesLocal, int golesVisitante) {
        if (!jugado) {
            this.golesLocal = golesLocal;
            this.golesVisitante = golesVisitante;
            this.jugado = true;

            // actualizamos las estadisticas de los equipos que juegan
            equipoLocal.actualizarEstadistica(golesLocal, golesVisitante);
            equipoVisitante.actualizarEstadistica(golesVisitante, golesLocal);

            //registramos al historial
            equipoLocal.agregarPartidoAlHistorial(this);
            equipoVisitante.agregarPartidoAlHistorial(this);
        }
    }

    public String obtenerResultado() {
        if (!jugado) {
            return "El partido aun no se ha jugado";
        }
        return equipoLocal.getNombre() + " " + golesLocal + "" + golesVisitante + "" + equipoVisitante.getNombre();
    }

    public boolean fueJugado() {
        return jugado;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    //sirve para saber si se repiten los equipos y para validar los fixtures
    public boolean involucraA(Equipo equipo) {
        return equipoLocal == equipo || equipoVisitante == equipo;
    }

    @Override
    public String toString() {
        return obtenerResultado();
    }

}
