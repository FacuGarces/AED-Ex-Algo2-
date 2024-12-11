package aed;

import java.util.ArrayList;

public class BestEffort {
    Ciudades ciudades;
    Traslados translados;

    // Creacion del Sistema, Inicializacion de Clases
    public BestEffort(int cantCiudades, Traslado[] traslados) { // O(C + T)
        this.ciudades = new Ciudades(cantCiudades); // O(C)
        this.translados = new Traslados(traslados); // O(T)
    }

    // Registro de Traslados fuera de la Inilizacion
    public void registrarTraslados(Traslado[] traslados) { // O(traslados Log(T))
        translados.agregar(traslados); // O(traslados Log(T))
    }

    public int[] despacharMasRedituables(int n) { // O(n(Log(T) + Log(C)))
        return ciudades.despachar(translados.masRedituables(n));
        // O(n Log(T) + n Log(C)) = O(n(Log(T) + Log(C)))
    }

    public int[] despacharMasAntiguos(int n) { // O(n(Log(T) + Log(C)))
        return ciudades.despachar(translados.masAntiguos(n));
        // O(n Log(T) + n Log(C)) = O(n(Log(T) + Log(C)))
    }

    public int ciudadConMayorSuperavit() { // O(1)
        return ciudades.mayorSuperavit(); // O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() { // O(1)
        return ciudades.mayorGanancia(); // O(1)
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() { // O(1)
        return ciudades.mayorPerdida(); // O(1)
    }

    public int gananciaPromedioPorTraslado() { // O(1)
        return ciudades.gananciaPromedioPorTraslado(); // O(1)
    }

}
